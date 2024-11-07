package io.axasoft.mayacomposite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // You can fetch the current user from the security context or any other way
        return Optional.of("system"); // Replace "system" with actual user information
    }
}
