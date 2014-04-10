package UserRegistration;

import java.io.FileInputStream;
import java.util.Properties;

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
	private String base_uri;
	private Properties properties;
	
	public DeleteRequest() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("properties.properties"));
		} catch (Exception e) {
			System.err.println(e);
		}
		base_uri = properties.getProperty("address") + "adminPanel";
	}
	
	public int deleteUser(String value, String extend_uri) throws Exception {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		client.addFilter(new LoggingFilter());
		webResource = client.resource(base_uri + extend_uri + "/" + value);

		client.addFilter(new HTTPBasicAuthFilter("adm", "ini"));
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);
		
		System.out.println(response.getStatus());
		return response.getStatus();
	}
}
