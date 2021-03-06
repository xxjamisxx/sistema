package br.com.sistema.controller;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.sistema.security.LoginDTO;


@RestController
@CrossOrigin
public class LoginController {
   
	 @PostMapping(value={"/api/public/login"}, consumes={"application/json"})
	  public ResponseEntity<?> autentica(@RequestBody LoginDTO loginDTO)
	  {
	    RestTemplate restTemplate = new RestTemplate();
	    
	    RequestEntity<LoginDTO> request = RequestEntity.post(URI.create("http://localhost:8080/api/login")).contentType(MediaType.APPLICATION_JSON).body(loginDTO);
	    try
	    {
	      ResponseEntity<String> response = restTemplate.exchange(request, String.class);
	      return ResponseEntity.ok(response.getHeaders().get("X-AUTH-TOKEN").get(0));
	    }
	    catch (Exception e) {}
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	  }
}
