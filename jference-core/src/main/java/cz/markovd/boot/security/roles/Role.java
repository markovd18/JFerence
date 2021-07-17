package cz.markovd.boot.security.roles;

/**
 * Enumeration of user roles for Spring.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 17.7.2021
 */
public enum Role {
    /**
     * Anonymous user, not logged in into the application.
     */
    ROLE_NONE,
    /**
     * Common logged in user.
     */
    ROLE_USER,
    /**
     * Admin user.
     */
    ROLE_ADMIN
}
