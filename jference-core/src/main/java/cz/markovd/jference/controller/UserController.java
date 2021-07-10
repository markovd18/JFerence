package cz.markovd.jference.controller;

import cz.markovd.jference.InvalidStateException;
import cz.markovd.jference.VOFactory;
import cz.markovd.jference.controller.vo.UserLoginVO;
import cz.markovd.jference.controller.vo.UserVO;
import cz.markovd.jference.domain.User;
import cz.markovd.jference.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pro working with user requests.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 28.2.2021
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;
    private VOFactory voFactory;

    /**
     * Performs validation and if given user is valid and odes not already exist,
     * the user will be registered. After successful registration, VO of the registered user is returned.
     * Otherwise {@link InvalidStateException} is with cause description is thrown.
     *
     * @param user user to register
     * @return VO of registered user
     */
    @PostMapping(value = "/register")
    public UserVO registerUser(@RequestBody final User user) {
        logger.info("Registering user: {}", user.getLogin());
        try {
            return voFactory.createUserVO(userService.registerUser(user));
        } catch (InvalidStateException e) {
            logger.error("Error while registering user {}", user, e);
            throw new InvalidStateException("Error while registering new user", e);
        }
    }

    @PostMapping(value = "/login")
    public UserVO loginUser(@RequestBody final UserLoginVO userLogin) {
        logger.info("User {} logging in...", userLogin);
        return userService.loginUser(userLogin);
    }


    /* *****************************
            AUTOWIRING
    ****************************
    */

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setVoFactory(final VOFactory voFactory) {
        this.voFactory = voFactory;
    }
}
