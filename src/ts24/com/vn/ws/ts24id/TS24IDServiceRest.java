package ts24.com.vn.ws.ts24id;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

public class TS24IDServiceRest {

	
	public static String postMethodData(String url, String data) throws AuthenticationException, ClientHandlerException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, data);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}
	
	public static String postMethodAuthenData(String userAuth, String passAuth, String url, String data) throws AuthenticationException, ClientHandlerException {
		String auth = new String(Base64.encode(userAuth + ":"+ passAuth));
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Basic " + auth).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, data);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}

}
