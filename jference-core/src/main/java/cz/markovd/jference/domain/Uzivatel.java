package cz.markovd.jference.domain;

import cz.markovd.jference.domain.dos.UzivatelDO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uzivatel")
public class Uzivatel extends UzivatelDO {

    public Uzivatel() {
    }

    public Uzivatel(Integer idUzivatel, String jmeno, String login, String heslo, String email, EnumPravo pravo) {
        super(idUzivatel, jmeno, login, heslo, email, pravo);
    }
}
