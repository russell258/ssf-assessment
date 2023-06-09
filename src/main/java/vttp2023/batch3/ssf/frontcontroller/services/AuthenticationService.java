package vttp2023.batch3.ssf.frontcontroller.services;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.model.Login;

@Service
public class AuthenticationService {

	// TODO: Task 2 
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
		Login login = new Login(username,password);
		//build http request and redirect to FrontController authenticateLogin to send and receive payload
		HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create("https://authservice-production-e8b2.up.railway.app"))
									.header("Content-Type","application/json")
									.POST(BodyPublishers.ofString(login.toJSON().toString()))
									.build();
		RequestEntity<String> req = RequestEntity
									.post("https://authservice-production-e8b2.up.railway.app/api/authenticate")
									.contentType(MediaType.APPLICATION_JSON)
									.headers("Accept",MediaType.APPLICATION_JSON_VALUE)
									.body(login.toJSON().toString(),String.class);

		System.out.println("CHECKING WHAT REQUEST IS BUILT >>>>> " +request);

	
		// authenticateLogin(request, login);						
		// JsonObject o = login.toJSON();
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
