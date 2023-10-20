package h_jdbc.models;

import java.io.Serializable;

public class Personne extends BaseEntity implements Serializable {
    private String nom;
    private String prenom;
    private Role role;

    public Personne() {
    }

    public Personne(long id, int version, String nom, String prenom, Role role) {
        super(id, version);
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("{id : %d, version : %d, nom : %s, prenom : %s, role : %s}",
                getId(),getVersion(),nom,prenom,role.name());
    }
}
