package cz.markovd.jference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Configuration
//@EnableJpaRepositories(basePackageClasses = JFerenceApp.class)
@SpringBootApplication
public class JFerenceApp {

    public static void main(String[] args) {
        SpringApplication.run(JFerenceApp.class, args);
    }

//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        return new LocalSessionFactoryBean();
//    }

}