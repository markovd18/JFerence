package cz.markovd.jference.controller.vo;

/**
 * User value object.
 *
 * @author <a href="mailto:"David Markov
 * @since 28.2.2021
 */
public class UserVO {

    String name;
    String login;
    String email;
    EnumUserRight right;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public EnumUserRight getRight() {
        return right;
    }

    public void setRight(final EnumUserRight right) {
        this.right = right;
    }
}

