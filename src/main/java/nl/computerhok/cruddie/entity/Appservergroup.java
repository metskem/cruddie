package nl.computerhok.cruddie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Appservergroup {

    public enum Stage { d, t, a, p}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appservergroupid;

    @Column(nullable = false, length = 64, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Stage stage;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP not null")
    private Date created;

//    @Column(nullable = false, columnDefinition = "varchar(64) default \'unknown\'")
    @Column(columnDefinition = "varchar(64) default 'unknown' not null")
    private String lastchangedby;

    //   MySQL syntax:
//   @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP not null")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP not null")
    private Date lastchanged;

    protected Appservergroup() {
    }

    public Appservergroup(String name, Stage stage, Date created, String lastchangedby, Date lastchanged) {
        this.name = name;
        this.stage = stage;
        this.created = created;
        this.lastchangedby = lastchangedby;
        this.lastchanged = lastchanged;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Appservergroup{");
        sb.append("appservergroupid=").append(appservergroupid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", stage=").append(stage);
        sb.append(", created=").append(created);
        sb.append(", lastchangedby='").append(lastchangedby).append('\'');
        sb.append(", lastchanged=").append(lastchanged);
        sb.append('}');
        return sb.toString();
    }

    public Long getAppservergroupid() {
        return appservergroupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Date getCreated() {        return created;    }

    public void setCreated(Date created) {        this.created = created;    }

    public String getLastchangedby() {        return lastchangedby;    }

    public void setLastchangedby(String lastchangedby) {        this.lastchangedby = lastchangedby;    }

    public Date getLastchanged() {        return lastchanged;    }

    public void setLastchanged(Date lastchanged) {        this.lastchanged = lastchanged;    }
}
