package io.axasoft.mayacomposite.controller;

import io.axasoft.mayacomposite.model.User;
import io.axasoft.mayacomposite.config.security.JwtTokenUtil;
import io.axasoft.mayacomposite.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public LoginController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation(value = "Authenticate user and generate JWT token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successful"),
            @ApiResponse(code = 401, message = "Invalid email or password")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userService.validatePassword(password, user.getPassword())) {
                // Generate JWT token
                String token = jwtTokenUtil.generateToken(email);
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Invalid password.");
            }
        } else {
            return ResponseEntity.status(401).body("User not found.");
        }
    }
}
