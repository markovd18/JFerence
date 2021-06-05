package cz.markovd.jference;

import cz.markovd.jference.controller.vo.EnumUserRight;
import cz.markovd.jference.controller.vo.UserVO;
import cz.markovd.jference.domain.User;
import org.springframework.stereotype.Component;

/**
 * Factory for creating VO's
 *
 * @author <a href="mailto:david.markov@marbes.cz">David Markov</a>
 * @since 28.2.2021
 */
@Component
public class VOFactory {

    public UserVO createUserVO(final User user) {
        if (user == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setName(user.getName());
        userVO.setLogin(user.getLogin());
        userVO.setEmail(userVO.getEmail());
        userVO.setRight(EnumUserRight.getValueOf(user.getRight()));

        return userVO;
    }
}
