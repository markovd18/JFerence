package cz.markovd.jference.domain.dos;

import cz.markovd.jference.domain.User;
import org.hibernate.bytecode.enhance.spi.CollectionTracker;
import org.hibernate.engine.spi.SelfDirtinessTracker;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public class PostDO implements SelfDirtinessTracker {

    public static final String ID_POST = "id_post";

    public static final String DATE = "date";

    public static final String ID_USER = "id_user";

    public static final String TITLE = "title";

    public static final String TEXT = "text";

    public static final String SECTION = "section";

    public static final String STATE = "state";

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer idPost;
    @Column(length = 30)
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ID_USER)
    private User user;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 1000)
    private String text;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EnumSection section;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EnumState state;
    /**
     * Set of changes to commit.
     */
    @Transient
    private Set<String> $$_changes;
    /**
     * Suspension of change tracking.
     */
    @Transient
    private boolean $$_suspend;

    protected PostDO() {
        //
    }

    public PostDO(Integer idPost, Date date, User user, String title, String text, EnumSection section, EnumState state) {
        this.idPost = idPost;
        this.date = date;
        this.user = user;
        this.title = title;
        this.text = text;
        this.section = section;
        this.state = state;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        if (!Objects.equals(this.idPost, idPost)) $$_hibernate_trackChange(ID_POST);
        this.idPost = idPost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EnumSection getSection() {
        return section;
    }

    public void setSection(EnumSection section) {
        this.section = section;
    }

    public EnumState getState() {
        return state;
    }

    public void setState(EnumState state) {
        this.state = state;
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

    public enum EnumSection {
        METALCORE,
        HARDCORE,
        DEATHCORE,
        OTHERS
    }

    public enum EnumState {
        PUBLISHED,
        UNPUBLISHED
    }
}
