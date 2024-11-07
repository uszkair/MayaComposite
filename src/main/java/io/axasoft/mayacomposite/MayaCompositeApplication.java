package io.axasoft.mayacomposite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class MayaCompositeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayaCompositeApplication.class, args);
    }

}
