package com.IAPDemoPOC.Subscription.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/sign_up")
	public ResponseEntity<String> signUp(@RequestBody SignupRequestDTO request) {
		String response = new String();
		try {
			if (authService.signUp(request.getEmail(), request.getPassword(),request.getUserName())) {
				response="SUCCESS";
			} else {
				response="FAILURE";
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response="FAILURE";
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
		try {
			String token = authService.login(request.getEmail(), request.getPassword());
			LoginResponseDTO loginDto = new LoginResponseDTO();
			loginDto.setRequestStatus("SUCCESS");
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("AUTH_TOKEN", token);

			ResponseEntity<LoginResponseDTO> response = new ResponseEntity<>(loginDto, headers, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			LoginResponseDTO loginDto = new LoginResponseDTO();
			loginDto.setRequestStatus("FAILURE");
			ResponseEntity<LoginResponseDTO> response = new ResponseEntity<>(loginDto, null, HttpStatus.BAD_REQUEST);
			return response;
		}
	}

	@GetMapping("/validate")
	public boolean validate(@RequestParam("token") String token) {
		System.out.println("Here I am");
//        return false;
		return authService.validate(token);
	}
}
