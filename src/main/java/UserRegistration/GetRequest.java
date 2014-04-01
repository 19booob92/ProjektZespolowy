package UserRegistration;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class GetRequest {
	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://virt2.iiar.pwr.edu.pl:8080/register/adminPanel/allUsers/";

	public List<UserDTO> getData() throws Exception {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);

		return client
		    .resource(BASE_URI)
		    .type(MediaType.APPLICATION_JSON)
		    .accept(MediaType.APPLICATION_JSON)
		    .get(new GenericType<List<UserDTO>>(){});
	}

}