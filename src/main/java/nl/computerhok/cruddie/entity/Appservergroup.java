package nl.computerhok.cruddie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appservergroup {

    public enum Stage { d, t, a, p}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appservergroupid;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Stage stage;

    protected Appservergroup() {
    }

    public Appservergroup(String name, Stage stage) {
        this.name = name;
        this.stage = stage;
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
}
