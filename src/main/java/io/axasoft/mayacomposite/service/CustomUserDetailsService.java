package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.model.User;
import io.axasoft.mayacomposite.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        ApplicationConstants.RESOURCE_NOT_FOUND + ": " + email));

        // Convert the User object to a Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("USER") // Customize roles/authorities as needed
                .build();
    }
}
