package cz.markovd.jference.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation allowing access to REST API to everyone.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 10.07.21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AllowAnonymousAccess {
}
