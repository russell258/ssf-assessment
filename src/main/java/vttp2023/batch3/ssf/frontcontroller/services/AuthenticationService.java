package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.model.Login;

@Service
public class AuthenticationService {

	// TODO: Task 2 
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public ResponseEntity<String> authenticate(String username, String password) throws Exception {
		Login login = new Login(username,password);
		
		RequestEntity<String> req = RequestEntity
									.post("https://authservice-production-e8b2.up.railway.app/api/authenticate")
									.contentType(MediaType.APPLICATION_JSON)
									.header("Accept",MediaType.APPLICATION_JSON_VALUE)
									.body(login.toJSON().toString(),String.class);

		System.out.println("CHECKING THE REQUEST BUILT >>>>> " + req);
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req,String.class);
		System.out.println("CHECK THE RESPONSE SENT >>>> " + resp);
		return resp;
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		
	}

	//generate captcha with  2 random int and operator
	public Captcha generateCaptchaService(){
		Random r = new Random();
		Captcha c = new Captcha();
		//random.nextInt(max - min + 1) + min
		c.setFirst(r.nextInt(50 - 1 + 1) + 1);
		c.setSecond(r.nextInt(50 - 1 + 1) + 1);
		c.setOp(capOp[(r.nextInt(4))]);
		return c;
	}

	//math operations for captcha
	public static final char[] capOp = {
        '+',
        '-',
        '*',
		'/',
    };

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}


		// HttpRequest request = HttpRequest.newBuilder()
		// 							.uri(URI.create("https://authservice-production-e8b2.up.railway.app"))
		// 							.header("Content-Type","application/json")
		// 							.POST(BodyPublishers.ofString(login.toJSON().toString()))
		// 							.build();