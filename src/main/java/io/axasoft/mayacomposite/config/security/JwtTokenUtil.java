package io.axasoft.mayacomposite.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    // Generate a token for a given email
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Validate the given token
    public boolean validateToken(String token, UserDetails userDetails) {
        String emailFromToken = extractEmail(token); // Extract email from the token
        return (emailFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Helper method to check if the token is expired
    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

    // Method to extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // Method to extract email (subject) from the token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Method to extract all claims from the token
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
