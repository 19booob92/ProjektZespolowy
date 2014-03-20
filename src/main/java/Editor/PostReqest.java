package Editor;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class PostReqest {
	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/register/";
	private User user;

	public PostReqest(User user) {
		this.user = user;
	}

	public void sendData() throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
		System.out.println(json);

		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(BASE_URI);

		client.addFilter(new HTTPBasicAuthFilter("test2", "test2"));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json);
		System.out.println(response.getStatus());
	}

}
