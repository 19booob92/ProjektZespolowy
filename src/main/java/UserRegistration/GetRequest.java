package UserRegistration;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class GetRequest {
	private WebResource webResource;
	private Client client;
	private String base_uri;
	private Properties properties;

	public GetRequest() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("properties.properties"));
		} catch (Exception e) {
			System.err.println(e);
		}
		base_uri = properties.getProperty("address") + "adminPanel/allUsers/";
	}

	public List<UserDTO> getData() throws Exception {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		client.addFilter(new HTTPBasicAuthFilter(properties
				.getProperty("login"), properties.getProperty("pass")));

		return client.resource(base_uri).type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<UserDTO>>() {
				});
	}

}