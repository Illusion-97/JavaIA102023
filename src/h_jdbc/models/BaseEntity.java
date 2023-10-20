package h_jdbc.models;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    private long id;
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
