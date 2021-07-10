package cz.markovd.jference.controller;

import cz.markovd.jference.InvalidStateException;
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
 * @author <a href="mailto:david.markov@marbes.cz">David Markov</a>
 * @since 28.2.2021
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;

    @PostMapping(value = "/register")
    public void registerUser(@RequestBody User user) {
        logger.info("Registering user: {}", user.getLogin());
        try {
            userService.registerUser(user);
        } catch (InvalidStateException e) {
            logger.error("Error while registering user {}", user, e);
        }
    }

    @PostMapping(value = "/login")
    public UserVO loginUser(@RequestBody UserLoginVO userLogin) {
        logger.info("User {} logging in...", userLogin);
        return userService.loginUser(userLogin);
    }


    /* *****************************
            AUTOWIRING
    ****************************
    */

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
