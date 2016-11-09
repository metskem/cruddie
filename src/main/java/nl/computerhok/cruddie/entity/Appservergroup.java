package nl.computerhok.cruddie.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Pattern;

@Entity
public class Appservergroup extends BaseEntity {
    private static final Logger LOG = LoggerFactory.getLogger(Appservergroup.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Stage {d, t, a, p}

    @Column(nullable = false, length = 64, unique = true)
    private String name;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "{invalid.email}")
    @Column(length = 1024)
    private String contact;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Stage stage;

    protected Appservergroup() {
    }

    public Appservergroup(String name, Stage stage, String lastchangedby) {
        setLastchangedby(lastchangedby);
        this.name = name;
        this.stage = stage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Appservergroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", stage=").append(stage);
        sb.append('}');
        return sb.toString();
    }

    @PrePersist
    public void prepersist() {        LOG.warn("prepersisting " + this);    }

    @PostPersist
    public void postpersist() {        LOG.warn("postpersisting " + this);    }

    @PreRemove
    public void preremove() {        LOG.warn("preremoving " + this);    }

    @PostRemove
    public void postremove() {        LOG.warn("postremoving " + this);    }

    @PreUpdate
    public void preupdate() {        LOG.warn("preupdating " + this);    }

    @PostUpdate
    public void postupdate() {        LOG.warn("postupdating " + this);    }

    public Long getId() {        return id;}

    public void setId(Long id) {        this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {        this.stage = stage;    }

    public String getContact() {        return contact;   }

    public void setContact(String contact) {        this.contact = contact;    }
}
