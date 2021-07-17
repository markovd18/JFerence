package cz.markovd.jference.service;

import cz.markovd.jference.InvalidStateException;
import cz.markovd.jference.VOFactory;
import cz.markovd.jference.controller.vo.UserLoginVO;
import cz.markovd.jference.controller.vo.UserVO;
import cz.markovd.jference.domain.User;
import cz.markovd.jference.repository.UserRepository;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

/**
 * Service for working with users.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 10.7. 2020
 */
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private PasswordEncryptor passwordEncryptor;
    private UserRepository userRepository;
    private VOFactory voFactory;


    /**
     * Registers given user.
     * @param user user to register
     * @throws InvalidStateException when the user is already registered
     */
    @Transactional(Transactional.TxType.MANDATORY)
    public User registerUser(final User user) throws InvalidStateException {
        Assert.notNull(user, "Given user may not be null!");

        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new InvalidStateException("User with login " + user.getLogin() + " already exists!");
        }

        String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    /**
     * Logs in given user.
     * @param userLogin user login credentials
     * @return registered user VO
     */
    public UserVO loginUser(final UserLoginVO userLogin) {
        if (userLogin == null) {
            return null;
        }

        User registeredUser = userRepository.findByLogin(userLogin.getLogin());
        if (registeredUser.passwordMatches(passwordEncryptor.encryptPassword(userLogin.getPassword()))) {
            return voFactory.createUserVO(registeredUser);
        }

        logger.error("Invalid password {}", userLogin);
        return null;
    }

    /* *****************************
                AUTOWIRING
        ****************************
     */

    @Autowired
    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setVoFactory(VOFactory voFactory) {
        this.voFactory = voFactory;
    }
}
