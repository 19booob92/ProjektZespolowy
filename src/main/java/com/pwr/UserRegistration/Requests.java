package com.pwr.UserRegistration;

import java.awt.EventQueue;
import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pwr.NewInterface.SplashWindow;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

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

	
	public void crossPoint(SplashWindow splashWindow) {}
	
	private void prepareConnection() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		client.addFilter(new HTTPBasicAuthFilter(login, pass));
	}

	public int delete(String value, String extend_uri) throws Exception {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(address + "adminPanel" + extend_uri + "/"
				+ value);

		client.addFilter(new HTTPBasicAuthFilter(login, pass));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);

		System.out.println(response.getStatus());
		return response.getStatus();
	}

	public List<UserDTO> getAllUsers()
			throws Exception {

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
	
	public <T> int sendData(T data, String typeURI) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(data);
		System.out.println(json);

		prepareConnection();

		webResource = client.resource(address + "adminPanel/" + typeURI);

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
		webResource = client.resource(address + "adminPanel/updateUser/"
				+ userLogin);

		client.addFilter(new HTTPBasicAuthFilter(login, pass));
		webResource.type(MediaType.APPLICATION_JSON).put(json);

	}

	// to jest wersja beta, póxniej na serwerze bedzie stowrzone odpowiednie
	// zapytanie ktore zrobi to wszystko
	// automatycznie
	public void deleteAll() {
		try {
			List<UserDTO> listaUserow = getAllUsers();
			List<QuestDTO> listaQuestow = getAllQuests();

			for (QuestDTO quest : listaQuestow) {
				delete(String.valueOf(quest.getId()), "/deleteQuest");
			}

			for (UserDTO user : listaUserow) {
				delete(user.getLogin(), "/deleteGame");
				delete(user.getLogin(), "/doneQuest");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<QuestDTO> getAllQuests() throws Exception {
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		client.addFilter(new HTTPBasicAuthFilter(login, pass));

		return client.resource(address + "adminPanel/allQuests/")
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<QuestDTO>>() {
				});
	}

	public int createNewGame(String name) throws Exception {

		prepareConnection();
		webResource = client.resource(address + "adminPanel/newGame/" + name);

		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, null);

		return response.getStatus();
	}

	public int sendFile() {
		prepareConnection();
		webResource = client.resource(address + "adminPanel/upload/");
		ClientResponse response = webResource.type(
				MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,
				createInstance());
		return response.getStatus();
	}

	private FormDataMultiPart createInstance() {
		File f = new File("paczka.zip");
		FileDataBodyPart fdp = new FileDataBodyPart("paczka", f,
				MediaType.APPLICATION_OCTET_STREAM_TYPE);

		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();

		formDataMultiPart.bodyPart(fdp);
		return formDataMultiPart;
	}
}