package h_jdbc.models;

import java.io.Serializable;

public class Transaction extends BaseEntity implements Serializable {
    private Personne acteur;
    private Marchandise objet;
    private int quantite;

    public Transaction() {
    }

    public Transaction(Personne acteur, Marchandise objet, int quantite) {
        this.acteur = acteur;
        this.objet = objet;
        this.quantite = quantite;
    }

    public Personne getActeur() {
        return acteur;
    }

    public void setActeur(Personne acteur) {
        this.acteur = acteur;
    }

    public Marchandise getObjet() {
        return objet;
    }

    public void setObjet(Marchandise objet) {
        this.objet = objet;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
