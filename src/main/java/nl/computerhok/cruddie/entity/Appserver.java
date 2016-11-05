package nl.computerhok.cruddie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appserver extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Location {Best, Boxtel}

    @Column(nullable = false, length = 128, unique = true)
    private String hostname;

    @Column(length = 1024)
    private String jvmargs;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "appservergroupid", nullable = false)
    private Appservergroup appservergroup;

    protected Appserver() {
    }

    public Appserver(String hostname, String jvmargs, Location location, Appservergroup appservergroup, String lastchangedby) {
        setLastchangedby(lastchangedby);
        this.hostname = hostname;
        this.jvmargs = jvmargs;
        this.location = location;
        this.appservergroup = appservergroup;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Appserver{");
        sb.append("id=").append(id);
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append(", jvmargs='").append(jvmargs).append('\'');
        sb.append(", location=").append(location);
        sb.append(", appservergroup=").append(appservergroup);
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {        return id;    }

    public void setId(Long id) {        this.id = id;}

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getJvmargs() {
        return jvmargs;
    }

    public void setJvmargs(String jvmargs) {
        this.jvmargs = jvmargs;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Appservergroup getAppservergroup() {
        return appservergroup;
    }

    public void setAppservergroup(Appservergroup appservergroup) {
        this.appservergroup = appservergroup;
    }
}
