package h_jdbc.models;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable { // Les classes enfants seront annotées @Entity
    //@Id -> définir la clé primaire
    //GeneratedValue(...) strategy = ...IDENTITY -> AUTO_INCREMENT
    private long id;
    //@Version
    private int version;

    public BaseEntity() {
    }

    public BaseEntity(long id, int version) {
        this.id = id;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
