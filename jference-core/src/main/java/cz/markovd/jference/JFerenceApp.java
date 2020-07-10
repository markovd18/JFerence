package cz.markovd.jference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackageClasses = JFerenceApp.class)
@SpringBootApplication
public class JFerenceApp {

    public static void main(String[] args) {
        SpringApplication.run(JFerenceApp.class, args);
    }

}