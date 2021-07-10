package cz.markovd.boot.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;

/**
 * Configuration for REST API.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 10.07.21
 */
@Configuration
@Order(RestApiSecurityConfiguration.ORDER)
public class RestApiSecurityConfiguration extends ResourceServerConfiguration {

    public static final int ORDER = 1050;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.anonymous().disable();
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
