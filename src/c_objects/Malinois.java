package c_objects;

public final class Malinois extends Animal {
    public Malinois(Animal parent, Animal otherParent, int age, String name) {
        super(parent, otherParent, age, name);
    }

    public Malinois(int age, String name) {
        super(null, null, age, name);
    }

    @Override // Réécrire le comportement d'une méthode sensée etre déjà présente dans la classe
    public Malinois getChild(Animal otherParent, String nom) {
        return new Malinois(this,otherParent,0,nom);
    }
}

/* Il n'est pas possible d'hériter d'une classe notée final
class Impossible extends Malinois {

    public Impossible(Animal parent, Animal otherParent, int age, String name) {
        super(parent, otherParent, age, name);
    }
}*/
