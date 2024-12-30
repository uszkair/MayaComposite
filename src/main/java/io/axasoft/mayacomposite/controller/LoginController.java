package io.axasoft.mayacomposite.controller;

import io.axasoft.mayacomposite.config.security.JwtTokenUtil;
import io.axasoft.mayacomposite.request.LoginRequest;
import io.axasoft.mayacomposite.request.RegisterRequest;
import io.axasoft.mayacomposite.response.ApiResponse;
import io.axasoft.mayacomposite.response.JwtTokenResponse;
import io.axasoft.mayacomposite.service.CustomUserDetailsService;
import io.axasoft.mayacomposite.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil,
                           CustomUserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<JwtTokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user using Spring Security's AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // If authentication is successful, generate a JWT token
            User user = (User) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(user.getUsername());

            // Wrap the JWT token in the response object
            JwtTokenResponse jwtToken = new JwtTokenResponse();
            jwtToken.setToken(token);

            ApiResponse<JwtTokenResponse> successResponse = new ApiResponse<>("Login successful", jwtToken);
            return ResponseEntity.ok(successResponse);

        } catch (AuthenticationException e) {
            // If authentication fails, return a structured error response
            ApiResponse<JwtTokenResponse> errorResponse = new ApiResponse<>("Invalid email or password", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }


    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use.");
        }

        // Create a new User and save it
        io.axasoft.mayacomposite.model.User newUser = new io.axasoft.mayacomposite.model.User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword()); // Password will be hashed in UserService
        userService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }
}
