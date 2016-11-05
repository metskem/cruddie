package nl.computerhok.cruddie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appservergroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Stage { d, t, a, p}

    @Column(nullable = false, length = 64, unique = true)
    private String name;

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

    public Long getId() {        return id;    }

    public void setId(Long id) {        this.id = id;    }

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
}
