package cz.markovd.jference.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation, allowing access to REST API only to logged in users.
 *
 * @author <a href="mailto:david.markov@marbes.cz">David Markov</a>
 * @since 05.06.21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AllowLoggedInAccessOnly {
}
