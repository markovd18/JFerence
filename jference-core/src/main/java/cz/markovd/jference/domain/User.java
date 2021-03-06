package cz.markovd.jference.domain;

import cz.markovd.jference.domain.dos.UserDO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class User extends UserDO {

    public User() {
    }

    public User(Integer idUser, String name, String login, String password, String email, EnumRightLevel right) {
        super(idUser, name, login, password, email, right);
    }

    /**
     * Checks if the user's password matches given string.
     * @param password string to compare password to
     * @return true when the strings match, otherwise false
     */
    public boolean passwordMatches(final String password) {
        return this.getPassword().equals(password);
    }
}
