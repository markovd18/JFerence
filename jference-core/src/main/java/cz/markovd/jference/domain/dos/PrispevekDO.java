package cz.markovd.jference.domain.dos;

import cz.markovd.jference.domain.Uzivatel;
import org.hibernate.bytecode.enhance.spi.CollectionTracker;
import org.hibernate.engine.spi.SelfDirtinessTracker;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public class PrispevekDO implements SelfDirtinessTracker {

    public static final String ID_PRISPEVEK = "id_prispevek";

    public static final String DATUM = "datum";

    public static final String ID_UZIVATEL = "id_uzivatel";

    public static final String TITULEK = "titulek";

    public static final String TEXT = "text";

    public static final String SEKCE = "sekce";

    public static final String STAV = "stav";

    public static final String CESTA = "cesta";
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idPrispevek;
    @Column(length = 30)
    private Date datum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_uzivatel")
    private Uzivatel uzivatel;
    @Column(nullable = false, length = 255)
    private String titulek;
    @Column(nullable = false, length = 1000)
    private String text;
    @Column(nullable = false, length = 20)
    private EnumSekce sekce;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EnumStav stav;
    /**
     * Seznam změn pro zápis.
     */
    @Transient
    private Set<String> $$_changes;
    /**
     * Pozastavení sledování změn.
     */
    @Transient
    private boolean $$_suspend;

    protected PrispevekDO() {
        //
    }

    public PrispevekDO(Integer idPrispevek, Date datum, Uzivatel uzivatel, String titulek, String text, EnumSekce sekce, EnumStav stav) {
        this.idPrispevek = idPrispevek;
        this.datum = datum;
        this.uzivatel = uzivatel;
        this.titulek = titulek;
        this.text = text;
        this.sekce = sekce;
        this.stav = stav;
    }

    public Integer getIdPrispevek() {
        return idPrispevek;
    }

    public void setIdPrispevek(Integer idPrispevek) {
        if (!Objects.equals(this.idPrispevek, idPrispevek)) $$_hibernate_trackChange(ID_PRISPEVEK);
        this.idPrispevek = idPrispevek;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public String getTitulek() {
        return titulek;
    }

    public void setTitulek(String titulek) {
        this.titulek = titulek;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EnumSekce getSekce() {
        return sekce;
    }

    public void setSekce(EnumSekce sekce) {
        this.sekce = sekce;
    }

    public EnumStav getStav() {
        return stav;
    }

    public void setStav(EnumStav stav) {
        this.stav = stav;
    }

    @Override
    public boolean $$_hibernate_hasDirtyAttributes() {
        return $$_changes != null;
    }

    @Override
    public String[] $$_hibernate_getDirtyAttributes() {
        return $$_changes == null ? new String[0] : $$_changes.toArray(new String[$$_changes.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void $$_hibernate_trackChange(String attribute) {
        if ($$_suspend) return;

        if ($$_changes == null) {
            $$_changes = new HashSet<>();
        }
        $$_changes.add(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void $$_hibernate_clearDirtyAttributes() {
        $$_changes = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void $$_hibernate_suspendDirtyTracking(boolean suspend) {
        this.$$_suspend = suspend;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollectionTracker $$_hibernate_getCollectionTracker() {
        return null;
    }

    public enum EnumSekce {
        METALCORE,
        HARDCORE,
        DEATHCORE,
        OSTATNI
    }

    public enum EnumStav {
        PUBLIKOVANO,
        NEPUBLIKOVANO
    }
}
