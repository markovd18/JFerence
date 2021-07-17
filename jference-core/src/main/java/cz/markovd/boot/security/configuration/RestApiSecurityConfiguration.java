package cz.markovd.boot.security.configuration;

import cz.markovd.boot.security.filter.AnonymousAccessFilter;
import cz.markovd.jference.security.AllowAdminAccessOnly;
import cz.markovd.jference.security.AllowAnonymousAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Configuration for REST API.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 10.07.21
 */
@Configuration
@EnableWebSecurity
public class RestApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ConfigurableListableBeanFactory beanFactory;
    private HandlerMappingIntrospector introspector;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.anonymous().disable();

        http.addFilterAfter(anonymousAccessFilter(), ExceptionTranslationFilter.class);
        http.authorizeRequests()
                .requestMatchers(getUrls(AllowAnonymousAccess.class)).access("authenticated || anonymous")
                .requestMatchers(getUrls(AllowAdminAccessOnly.class)).hasRole("ADMIN")
                // the rest of the urls will need an authentication
                .anyRequest().authenticated();
    }

    /**
     * Creates an array of {@link MvcRequestMatcher}'s for every API with given access annotation.
     *
     * @param annotation access annotation (eg. {@link AllowAnonymousAccess} or {@link AllowAdminAccessOnly})
     * @return array of request matchers for every API with given access annotation
     */
    private MvcRequestMatcher[] getUrls(final Class<? extends Annotation> annotation) {
        List<MvcRequestMatcher> matchers = new LinkedList<>();

        // assuming that most of the beans are initialized by now
        String[] controllerNames = beanFactory.getBeanNamesForAnnotation(RestController.class);
        for (String controllerName : controllerNames) {
            BeanDefinition controllerBeanDefinition = beanFactory.getBeanDefinition(controllerName);
            String controllerClassName = controllerBeanDefinition.getBeanClassName();
            if (controllerClassName == null) {
                continue;
            }

            try {
                Class<?> controllerClass = ClassUtils.forName(controllerClassName,
                        beanFactory.getBeanClassLoader());
                matchers.addAll(processControllerBean(controllerClass, annotation));
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return matchers.toArray(new MvcRequestMatcher[0]);
    }

    /**
     * Creates a list of {@link MvcRequestMatcher}'s for all API's of given controller with given access annotation.
     *
     * @param controllerClass controller class
     * @param annotation access annotation (eg. {@link AllowAnonymousAccess} or {@link AllowAdminAccessOnly}}
     * @return list of request matchers for controllers api
     */
    private List<MvcRequestMatcher> processControllerBean(final Class<?> controllerClass,
                                                    final Class<? extends Annotation> annotation) {
        List<MvcRequestMatcher> requestMatchers = new LinkedList<>();
        RequestMapping controllerApiAnnotation =
                AnnotatedElementUtils.getMergedAnnotation(controllerClass, RequestMapping.class);
        // we do not consider edge cases like empty mapping
        String controllerApiUrl = controllerApiAnnotation != null && controllerApiAnnotation.value().length > 0
                ? controllerApiAnnotation.value()[0] : "";
        RequestMethod[] requestMethods = controllerApiAnnotation != null
                ? controllerApiAnnotation.method() : new RequestMethod[0];
        // if the annotation is on the entire controller class, we consider it as if it was on every method
        boolean allowOnAllControllerMethods = controllerClass.isAnnotationPresent(annotation);

        for (Method controllerMethod : controllerClass.getMethods()) {
            if (allowOnAllControllerMethods || controllerMethod.isAnnotationPresent(annotation)) {
                Set<RequestMapping> methodApiAnnotations =
                        AnnotatedElementUtils.getAllMergedAnnotations(controllerMethod, RequestMapping.class);

                for (RequestMapping methodApiAnnotation : methodApiAnnotations) {
                    String[] methodUrls = methodApiAnnotation.value().length > 0
                            ? methodApiAnnotation.value() : new String[] {""};

                    for (String methodUrl : methodUrls) {
                        String apiUrl = controllerApiUrl + methodUrl;
                        List<HttpMethod> allowedHttpMethods = getHttpMethods(requestMethods, methodApiAnnotation.method());

                        requestMatchers.addAll(allowedHttpMethods.stream().map(httpMethod -> {
                            MvcRequestMatcher matcher = new MvcRequestMatcher(introspector, apiUrl);
                            matcher.setMethod(httpMethod);
                            return matcher;
                        }).collect(Collectors.toList()));
                    }
                }
            }
        }

        return requestMatchers;
    }

    /**
     * Unites controller and method http methods into one list. See {@link HttpMethod}.
     *
     * @param controllerHttpMethods controller http methods
     * @param methodHttpMethods method http methods
     * @return unified list of http methods
     */
    private List<HttpMethod> getHttpMethods(final RequestMethod[] controllerHttpMethods,
                                            final RequestMethod[] methodHttpMethods) {
        if (controllerHttpMethods.length == 0 && methodHttpMethods.length == 0) {
            return Collections.singletonList(null);     // NULL means allow every method
        }

        if (controllerHttpMethods.length == 0) {
            return requestMethodsToHttpMethods(methodHttpMethods);
        }

        if (methodHttpMethods.length == 0) {
            return requestMethodsToHttpMethods(controllerHttpMethods);
        }

        // need to do the intersection so there is no method twice
        List<RequestMethod> arrayIntersection = Arrays.asList(controllerHttpMethods);
        arrayIntersection.retainAll(Arrays.asList(methodHttpMethods));

        return requestMethodsToHttpMethods(arrayIntersection.toArray(new RequestMethod[0]));
    }

    /**
     * Converts {@link RequestMethod} enumerations into {@link HttpMethod} enumerations with the same name.
     *
     * @param requestMethods request methods
     * @return http methods with the same name
     */
    private List<HttpMethod> requestMethodsToHttpMethods(final RequestMethod[] requestMethods) {
        return Arrays.stream(requestMethods)
                .map(requestMethod -> HttpMethod.resolve(requestMethod.name()))
                .collect(Collectors.toList());
    }

    @Bean
    public AnonymousAccessFilter anonymousAccessFilter() {
        return new AnonymousAccessFilter();
    }


    /*
            **********************************
            *           AUTOWIRING           *
            **********************************
     */

    @Autowired
    public void setBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Autowired
    public void setIntrospector(HandlerMappingIntrospector introspector) {
        this.introspector = introspector;
    }
}
