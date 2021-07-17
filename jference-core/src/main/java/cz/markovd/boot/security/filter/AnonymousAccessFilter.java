package cz.markovd.boot.security.filter;

import cz.markovd.boot.security.roles.Role;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 *  Modification of {@link AnonymousAuthenticationFilter}. Allows even anonymous users to authenticate.
 *  Allows the usage of {@link cz.markovd.jference.security.AllowAnonymousAccess} for development
 *  purposes.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 17.7.2021
 */
public class AnonymousAccessFilter extends AnonymousAuthenticationFilter {

    private static final String UNAUTHORIZED_USER = "Anonymous";
    /**
     * Roles of unauthorized anonymous user. Spring requires at least one role for every user.
     */
    private static final List<GrantedAuthority> ANONYMOUS = Collections.singletonList(
            new SimpleGrantedAuthority(Role.ROLE_NONE.name()));

    public AnonymousAccessFilter() {
        super(UNAUTHORIZED_USER, UNAUTHORIZED_USER, ANONYMOUS);
    }

    @Override
    protected Authentication createAuthentication(final HttpServletRequest request) {
        AnonymousAuthenticationToken auth = (AnonymousAuthenticationToken) super.createAuthentication(request);
        auth.setAuthenticated(true);
        return auth;
    }
}
