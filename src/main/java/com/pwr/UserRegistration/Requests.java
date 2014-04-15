package com.pwr.UserRegistration;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;

@Component
public class Requests implements Serializable {

	private WebResource webResource;
	private Client client;
	@Value("${address}")
	private String address;
	@Value("${login}")
	private String login;
	@Value("${pass}")
	private String pass;

	public Requests() {
	}

	public int deleteUser(String value, String extend_uri) throws Exception {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(address + "adminPanel" + extend_uri + "/" + value);

		client.addFilter(new HTTPBasicAuthFilter(login, pass));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);

		System.out.println(response.getStatus());
		return response.getStatus();
	}

	public List<UserDTO> getAllUsers() throws Exception {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		client.addFilter(new HTTPBasicAuthFilter(login, pass));

		return client.resource(address + "adminPanel/allUsers/")
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<UserDTO>>() {
				});
	}

	public int sendData(User user) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
		System.out.println(json);

		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(address + "adminPanel/createUser/" );

		client.addFilter(new HTTPBasicAuthFilter(login, pass));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json);

		return response.getStatus();
	}

	public void update(String userLogin, UserDTO userDTO) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(userDTO);
		System.out.println(json);

		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(address  +"adminPanel/updateUser/" + userLogin);
		
		client.addFilter(new HTTPBasicAuthFilter(login, pass));
		webResource.type(MediaType.APPLICATION_JSON).put(json);

	}

	public WebResource getWebResource() {
		return webResource;
	}

	public void setWebResource(WebResource webResource) {
		this.webResource = webResource;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getBase_uri() {
		return address;
	}

	public void setBase_uri(String base_uri) {
		this.address = base_uri;
	}

/*	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}*/

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

}