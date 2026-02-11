package GETCallAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GETAPITest {

	public static void main(String[] args) {

		try {
			HttpRequest httpRequest = HttpRequest.newBuilder()
						.uri(new URI("https://reqres.in/api/users?page=2"))
						.GET()
						.build();
			
			HttpResponse<String> httpResponse = 
									HttpClient
										.newHttpClient()
											.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			
			System.out.println(httpResponse.statusCode());
			System.out.println(httpResponse.body());
			
			//deserialization : JSON -- to -- POJO
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
