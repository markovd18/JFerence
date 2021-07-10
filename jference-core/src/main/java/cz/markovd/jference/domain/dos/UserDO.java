package cz.markovd.jference.domain.dos;


import org.hibernate.bytecode.enhance.spi.CollectionTracker;
import org.hibernate.engine.spi.SelfDirtinessTracker;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public class UserDO implements SelfDirtinessTracker {

    public static final String ID_USER = "id_user";

    public static final String NAME = "name";

    public static final String LOGIN = "login";

    public static final String PASSWORD = "password";

    public static final String EMAIL = "email";

    public static final String RIGHT_LEVEL = "rightLevel";

    /**
     * Unique ID of the user. Serves as a Private key of the database entity.
     */
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idUser;

    /**
     * User's real name. Used only in account detail.
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * User's unique nickname. Widely used in comment sections and private messaging.
     */
    @Column(nullable = false, length = 50, unique = true)
    private String login;

    /**
     * Password of the user. Content is 60B hash of the original password.
     */
    @Column(nullable = false, length = 60)
    private String password;

    /**
     * User's email address.
     */
    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EnumRightLevel rightLevel;
    /**
     * List of changes to write.
     */
    @Transient
    private Set<String> $$_changes;
    /**
     * Suspend change tracking.
     */
    @Transient
    private boolean $$_suspend;

    protected UserDO() {
    }

    public UserDO(Integer isUser, String name, String login, String password, String email, EnumRightLevel rightLevel) {
        this.idUser = isUser;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.rightLevel = rightLevel;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUzivatel) {
        if (!Objects.equals(this.idUser, idUzivatel)) $$_hibernate_trackChange(ID_USER);
        this.idUser = idUzivatel;
    }

    @Override
    public String toString() {
        return "User: idUser=" + getIdUser();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            if (name.length() == 0) {
                name = null;
            } else if (name.length() > 50) {
                name = name.substring(0, 50);
            }
        }
        if (!Objects.equals(this.name, name)) $$_hibernate_trackChange(NAME);
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null) {
            if (password.length() == 0) {
                password = null;
            } else if (password.length() > 60) {
                password = password.substring(0, 60);
            }
        }

        if (!Objects.equals(this.password, password)) $$_hibernate_trackChange(PASSWORD);
        this.password = password;
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

    public EnumRightLevel getRightLevel() {
        return rightLevel;
    }

    public void setRightLevel(EnumRightLevel right) {
        if (!Objects.equals(this.rightLevel, right)) $$_hibernate_trackChange(RIGHT_LEVEL);
        this.rightLevel = right;
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

    /**
     * Level of rights that user has. {@link #ADMIN} can perform any operation - can create new post, review one
     * or delete them. {@link #REVIEWER} can create new posts and review posts, that are assigned to him.
     * {@link #AUTHOR} can only create new post, but has to wait for it to be reviewed and accepted.
     */
    public enum EnumRightLevel {
        /**
         * Admin can create new post, review one or delete them. He can also assign new post to the {@link #REVIEWER}
         * for a review.
         */
        ADMIN(20),
        /**
         * Reviewer can create new post and review those that are assigned to him by {@link #ADMIN}.
         */
        REVIEWER(10),
        /**
         * Basic authorization level - can only create new post and wait for it to be reviewed by {@link #REVIEWER}
         * and accepted by {@link #ADMIN}.
         */
        AUTHOR(5);

        /**
         * Integral weight of the authorization level. Higher weight means higher rights.
         */
        private final int weight;

        EnumRightLevel(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }
}
