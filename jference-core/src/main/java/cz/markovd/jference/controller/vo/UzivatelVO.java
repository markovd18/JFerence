package cz.markovd.jference.controller.vo;

/**
 * VO uživatele, předávané na klienta.
 *
 * @author David Markov
 * @since 28.2.2021
 */
public class UzivatelVO {

    String jmeno;
    String login;
    String email;
    EnumUzivatelPravo pravo;

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumUzivatelPravo getPravo() {
        return pravo;
    }

    public void setPravo(EnumUzivatelPravo pravo) {
        this.pravo = pravo;
    }
}

