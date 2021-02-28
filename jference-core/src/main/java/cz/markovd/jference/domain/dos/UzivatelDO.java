package cz.markovd.jference.domain.dos;


import org.hibernate.bytecode.enhance.spi.CollectionTracker;
import org.hibernate.engine.spi.SelfDirtinessTracker;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public class UzivatelDO implements SelfDirtinessTracker {

    public static final String ID_UZIVATEL = "id_uzivatel";

    public static final String JMENO = "jmeno";

    public static final String LOGIN = "login";

    public static final String HESLO = "heslo";

    public static final String EMAIL = "email";

    public static final String PRAVO = "pravo";

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idUzivatel;

    @Column(nullable = false, length = 50)
    private String jmeno;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false, length = 60)
    private String heslo;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EnumPravo pravo;
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

    protected UzivatelDO() {
    }

    public UzivatelDO(Integer idUzivatel, String jmeno, String login, String heslo, String email, EnumPravo pravo) {
        this.idUzivatel = idUzivatel;
        this.jmeno = jmeno;
        this.login = login;
        this.heslo = heslo;
        this.email = email;
        this.pravo = pravo;
    }

    public Integer getIdUzivatel() {
        return idUzivatel;
    }

    public void setIdUzivatel(Integer idUzivatel) {
        if (!Objects.equals(this.idUzivatel, idUzivatel)) $$_hibernate_trackChange(ID_UZIVATEL);
        this.idUzivatel = idUzivatel;
    }

    @Override
    public String toString() {
        return "Uzivatel: idUzivatele=" + getIdUzivatel();
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        if (jmeno != null) {
            if (jmeno.length() == 0) {
                jmeno = null;
            } else if (jmeno.length() > 50) {
                jmeno = jmeno.substring(0, 50);
            }
        }
        if (!Objects.equals(this.jmeno, jmeno)) $$_hibernate_trackChange(JMENO);
        this.jmeno = jmeno;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null) {
            if (login.length() == 0) {
                login = null;
            } else if (login.length() > 50) {
                login = login.substring(0, 50);
            }
        }
        if (!Objects.equals(this.login, login)) $$_hibernate_trackChange(LOGIN);
        this.login = login;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        if (heslo != null) {
            if (heslo.length() == 0) {
                heslo = null;
            } else if (heslo.length() > 60) {
                heslo = heslo.substring(0, 60);
            }
        }

        if (!Objects.equals(this.heslo, heslo)) $$_hibernate_trackChange(HESLO);
        this.heslo = heslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            if (email.length() == 0) {
                email = null;
            } else if (email.length() > 60) {
                email = email.substring(0, 60);
            }
        }

        if (!Objects.equals(this.email, email)) $$_hibernate_trackChange(EMAIL);
        this.email = email;
    }

    public EnumPravo getPravo() {
        return pravo;
    }

    public void setPravo(EnumPravo pravo) {
        if (!Objects.equals(this.pravo, pravo)) $$_hibernate_trackChange(PRAVO);
        this.pravo = pravo;
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

    public enum EnumPravo {
        ADMIN(20),
        RECENZENT(10),
        AUTOR(5);

        private final int vaha;

        EnumPravo(int vaha) {
            this.vaha = vaha;
        }

        public int getVaha() {
            return vaha;
        }
    }
}
