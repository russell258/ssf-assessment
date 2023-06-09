package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@RestController
@RequestMapping(path="/protected")
public class ProtectedController {

	@Autowired
	private AuthenticationService aSvc;


	// pathvariable because resource name will depend on what is rooted
	//check if login authenticated (happend during login). else, won't show

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
	@GetMapping(path="{resource}")
	public ResponseEntity<String> accessProtected(@PathVariable String resource, Login login){

		//Check authentication is not false which is not logged in and user is not in the disable redis list
		if (login.isAuth()==false && !aSvc.checkDisableService(login.getUsername()).isEmpty()){
			JsonObject error = Json.createObjectBuilder()
									.add("message","Login is not authenticated")
									.build();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error.toString());
		}
		return ResponseEntity.ok("Authenticated");
	}
	

}
