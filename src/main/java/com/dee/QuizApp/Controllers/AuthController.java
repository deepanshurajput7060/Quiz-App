package com.dee.QuizApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.QuizApp.Dto.AuthRequest;
import com.dee.QuizApp.Dto.AuthResponse;
import com.dee.QuizApp.Dto.RegisterRequest;
import com.dee.QuizApp.Entity.User;
import com.dee.QuizApp.Repository.UserRepository;
import com.dee.QuizApp.Security.JwtUtil;
import org.springframework.web.bind.annotation.RequestBody; // ✅ Correct!


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
		authenticationManager.authenticate(new

		UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		String token = jwtUtil.generateToken(authRequest.getUsername());
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register( @RequestBody RegisterRequest registerRequest) {
		System.out.println("🚀 /auth/register hit");
		if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("User already present");
		}
		
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRole("USER_ROLE");
		
		userRepository.save(user);
		
		return ResponseEntity.ok("User Registered successfully");		
	}
	
	
}













