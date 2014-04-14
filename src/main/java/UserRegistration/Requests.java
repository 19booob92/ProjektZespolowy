package UserRegistration;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
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
	private String base_uri;
	private Properties properties;
	private String login;
	private String password;

	public Requests() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("properties.properties"));
		} catch (Exception e) {
			System.err.println(e);
		}
		base_uri = properties.getProperty("address") + "adminPanel/allUsers/";
		login = properties.getProperty("login");
		password = properties.getProperty("pass");
	}

	public int deleteUser(String value, String extend_uri) throws Exception {
		base_uri = base_uri + "adminPanel" + extend_uri;
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(base_uri + "/" + value);

		client.addFilter(new HTTPBasicAuthFilter(login, password));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);

		System.out.println(response.getStatus());
		return response.getStatus();
	}

	public List<UserDTO> getAllUsers() throws Exception {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		client.addFilter(new HTTPBasicAuthFilter(login, password));

		return client.resource(base_uri + "adminPanel/allUsers/")
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
		webResource = client.resource(base_uri + "/adminPanel");

		client.addFilter(new HTTPBasicAuthFilter(login, password));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json);

		return response.getStatus();
	}

	public void update(String login, UserDTO userDTO) throws Exception {
		base_uri += "/adminPanel/updateUser/" + login;
		Gson gson = new Gson();
		String json = gson.toJson(userDTO);
		System.out.println(json);

		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(base_uri);

		client.addFilter(new HTTPBasicAuthFilter(login, password));
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
		return base_uri;
	}

	public void setBase_uri(String base_uri) {
		this.base_uri = base_uri;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}