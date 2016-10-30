package nl.computerhok.cruddie.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Appserver {

    public enum Location {Best, Boxtel}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appserverid;

    @Column(nullable = false, length = 128, unique = true)
    private String hostname;

    @Column(length = 1024)
    private String jvmargs;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Location location;

    @Column(length = 64)
    private String changedby;

    @Column(nullable = false)
    private Date creation;

    @Column(nullable = false)
    private Date lastchanged;

    @ManyToOne(optional = false)
    @JoinColumn(name = "appservergroupid", nullable = false)
    private Appservergroup appservergroup;

    protected Appserver() {
    }

    public Appserver(String hostname, String jvmargs, Location location, String changedby, Date creation, Date lastchanged, Appservergroup appservergroup) {
        this.hostname = hostname;
        this.jvmargs = jvmargs;
        this.location = location;
        this.changedby = changedby;
        this.creation = creation;
        this.lastchanged = lastchanged;
        this.appservergroup = appservergroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Appserver{");
        sb.append("appserverid=").append(appserverid);
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append(", jvmargs='").append(jvmargs).append('\'');
        sb.append(", location=").append(location);
        sb.append(", changedby='").append(changedby).append('\'');
        sb.append(", creation=").append(creation);
        sb.append(", lastchanged=").append(lastchanged);
        sb.append(", appservergroup=").append(appservergroup);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Appserver appserver = (Appserver) o;

        return new EqualsBuilder()
                .append(appserverid, appserver.appserverid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(appserverid)
                .toHashCode();
    }

    public Long getAppserverid() {
        return appserverid;
    }

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

    public String getChangedby() {
        return changedby;
    }

    public void setChangedby(String changedby) {
        this.changedby = changedby;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getLastchanged() {
        return lastchanged;
    }

    public void setLastchanged(Date lastchanged) {
        this.lastchanged = lastchanged;
    }

    public Appservergroup getAppservergroup() {
        return appservergroup;
    }

    public void setAppservergroup(Appservergroup appservergroup) {
        this.appservergroup = appservergroup;
    }
}
