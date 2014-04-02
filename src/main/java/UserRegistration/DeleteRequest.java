package UserRegistration;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class DeleteRequest {
	private WebResource webResource;
	private Client client;
	private static String BASE_URI = "http://virt2.iiar.pwr.edu.pl:8080/register/adminPanel";//deleteUser

	public int deleteUser(String value, String extend_uri) throws Exception {
		BASE_URI = BASE_URI + extend_uri;
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(BASE_URI + "/" + value);

		client.addFilter(new HTTPBasicAuthFilter("adm", "ini"));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);
		
		System.out.println(response.getStatus());
		return response.getStatus();
	}
}
