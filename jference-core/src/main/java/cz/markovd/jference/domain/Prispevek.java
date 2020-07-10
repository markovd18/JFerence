package cz.markovd.jference.domain;

import cz.markovd.jference.domain.dos.PrispevekDO;

import java.util.Date;

public class Prispevek extends PrispevekDO {

    public Prispevek() {
    }

    public Prispevek(Integer idPrispevek, Date datum, Uzivatel uzivatel, String titulek, String text, EnumSekce sekce, EnumStav stav) {
        super(idPrispevek, datum, uzivatel, titulek, text, sekce, stav);
    }
}
