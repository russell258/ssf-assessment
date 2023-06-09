package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping //(method = RequestMethod.GET, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class FrontController {

	@Autowired
	private AuthenticationService aSvc;


	//task 1 set landing page
	@GetMapping(path="/")
	public String showLoginPage(Model m, Login login, HttpSession session){
		// m.addAttribute("login", new Login());
		return "view0";
	}

	@PostMapping(path="/login", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String validateLogin(Model m, @Valid Login login, HttpSession session, BindingResult result) throws Exception{
		if (result.hasErrors()){
			return "view0";
		}
		m.addAttribute("login", login);
		try{
			aSvc.authenticate(login.getUsername(),login.getPassword());
		} catch (HttpClientErrorException e){
			//catch if 401 error response sent. 
			if (e.getStatusCode()==HttpStatusCode.valueOf(401)){
				//increase attempt count by 1.
				login.increaseAttempt();
				//add login fail message and generate captchaservice
				m.addAttribute("loginFail", "Incorrect username/password");
				Captcha c = aSvc.generateCaptchaService();
				m.addAttribute("captcha", c);
				return "view0";
			}
			// HttpStatusCode status  = aSvc.authenticate(login.getUsername(), login.getPassword()).getStatusCode();
			// System.out.println("checking STATUS >>>> " + status);
		}catch (HttpStatusCodeException e){
			//catch if 400 error response sent
			if (e.getStatusCode()==HttpStatusCode.valueOf(400)){
				//increase attempt count by 1.
				login.increaseAttempt();
				//add login fail message and generate captchaservice
				m.addAttribute("loginFail", "Invalid payload");
				Captcha c = aSvc.generateCaptchaService();
				m.addAttribute("captcha", c);
				return "view0";
			}
		}
		

		return "view1";
	}

	// TODO: Task 2, Task 3, Task 4, Task 6
	

}


		// MultiValueMap<String,String> form = new LinkedMultiValueMap<>();
		// form.add("username", login.getUsername());
		// form.add("password", login.getPassword());
		// RequestEntity<MultiValueMap<String,String>> req = RequestEntity
		// 												.post("localhost:8080")
		// 												.body(form, form.class);
		// RestTemplate template = new RestTemplate();

		// ResponseEntity<String> resp = template.exchange(req,String.class);