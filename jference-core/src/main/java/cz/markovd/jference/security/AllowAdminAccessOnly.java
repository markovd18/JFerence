package cz.markovd.jference.security;

import cz.markovd.jference.controller.vo.EnumUserRight;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation, allowing access to REST API only to users with
 * {@link EnumUserRight#ADMIN} right.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 05.06.21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AllowAdminAccessOnly {
}
