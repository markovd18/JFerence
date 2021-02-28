package cz.markovd.jference.controller.vo;

/**
 * VO pro data přenášená z klienta při pokusu o přihlášení uživatele.
 *
 * @author David Markov
 * @since 28.2.2021
 */
public class UserLoginVO {

    private String login;
    private String heslo;

    public String getLogin() {
        return login;
    }

    public String getHeslo() {
        return heslo;
    }
}
