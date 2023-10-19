package g_serialization;

import java.io.Serializable;

// POJO != JavaBean
/*
* POJO = Plain Old Java Object (toute classe Java peut être considérée comme un POJO)
* JavaBean = représente une structure de classe particulière adaptée à la sauvegarde d'objet
* Cette structure implique :
* 1 : d'implémenter l'interface Serializable
* 2 : de n'avoir que des attributs privés
* 3 : disposer du constructeur vide si d'autres constructeurs sont présents (ou par celui défaut le cas échéant)
* 4 : disposer de getters et setters pour les attributs
*
* Ceci permet aux processus de sérialisation de fonctionner de la même façon pour la création d'objet :
* 1ère étape étant de l'instancier via le constructeur vide
* 2eme étape : pour chaque valeur devant être affectée à l'objet, on passera par le setter
* */
public class Person implements Serializable {
    private String name;
    private long tel;

    public Person() {
    }

    public Person(String name, long tel) {
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return String.format("%s : %10d",name,tel);
    }
}
