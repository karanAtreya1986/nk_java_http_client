package POSTCallAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POSTCallTest {

	public static void main(String[] args) {

//		String requestBody = "{\n"
//				+ "    \"name\": \"morpheus\",\n"
//				+ "    \"job\": \"leader\"\n"
//				+ "}";
		
		//pojo
		User user = new User("Madhuri", "SDET");
		//pojo to json: serialization: use jackson api
		ObjectMapper mapper = new ObjectMapper();
		String reuestJsonBody = null;
		try {
			reuestJsonBody = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		try {
			HttpRequest httpPOSTRequest = HttpRequest.newBuilder()
						.uri(new URI("https://reqres.in/api/users"))
						.header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(reuestJsonBody))
						.build();
			
			System.out.println(httpPOSTRequest.uri());

			
			HttpResponse<String> httpResponse = 
										HttpClient
												.newHttpClient()
														.send(httpPOSTRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println(httpResponse.statusCode());
			System.out.println(httpResponse.body());
			
			
			//Desrialization: JSON to POJO: with jackson
			User userRes = mapper.readValue(httpResponse.body(), User.class);
			System.out.println(userRes);
			System.out.println(userRes.getName() + ":" + userRes.getJob());
			System.out.println(userRes.getId() + ":" + userRes.getCreatedAt());
			
			if(userRes.getName().equals(user.getName())) {
				System.out.println("user name is same");
			}
			
			System.out.println("---------------------");
			
			//GET CALL:
			try {
				HttpRequest httpGETRequest = HttpRequest.newBuilder()
							.uri(new URI("https://reqres.in/api/users/"+userRes.getId()))
							.GET()
							.build();
				
				System.out.println(httpGETRequest.uri());
				
				HttpResponse<String> httpGETResponse = 
										HttpClient
											.newHttpClient()
												.send(httpGETRequest, HttpResponse.BodyHandlers.ofString());
				
				System.out.println(httpGETResponse.statusCode());
				System.out.println(httpGETResponse.body());
				
				//deserialization : JSON -- to -- POJO
				
				
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
					
		
		
		
		
		
		
	}

}
