package cz.markovd.jference.domain;

import cz.markovd.jference.domain.dos.PostDO;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post extends PostDO {

    public Post() {
    }

    public Post(Integer idPrispevek, Date datum, User uzivatel, String titulek, String text, EnumSection sekce, EnumState stav) {
        super(idPrispevek, datum, uzivatel, titulek, text, sekce, stav);
    }
}
