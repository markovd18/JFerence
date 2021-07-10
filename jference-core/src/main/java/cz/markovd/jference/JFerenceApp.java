package cz.markovd.jference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackageClasses = JFerenceApp.class, basePackages = {"cz.markovd.boot", "cz.markovd.jference"})
@EntityScan(basePackageClasses = JFerenceApp.class, basePackages = {"cz.markovd.boot", "cz.markovd.jference"})
@ComponentScan(basePackageClasses = JFerenceApp.class, basePackages = {"cz.markovd.boot", "cz.markovd.jference"})
@SpringBootApplication
public class JFerenceApp {

    public static void main(String[] args) {
        SpringApplication.run(JFerenceApp.class, args);
    }


}