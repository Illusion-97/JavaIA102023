package c_objects;

public final class Malinois extends Animal implements Chien {
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

    @Override
    public void bark() {
        // selon son AgeRange : Jeune -> HawHaw / Adulte : WafWaf / Senior : Wouf
        /*String voice = switch (getAgeRange()) {
            case JEUNE -> "HawHaw";
            case ADULTE -> "WafWaf";
            case SENIOR -> "Wouf";
        };*/
        bark(switch (getAgeRange()) {
            case JEUNE -> "HawHaw";
            case ADULTE -> "WafWaf";
            case SENIOR -> "Wouf";
        });
    }

    @Override
    public void fight(Chien chien) {
        /* Le malinois ici (Maya) affronte Rex et perds le combat
        Maya is fighting for honor!
        Rex : AHOU!
        Maya is going to hide...*/
        /*Le malinois ici (Maya) affronte Rex et remporte le combat
        Maya is fighting for honor!
        Maya : AHOU!
        Maya jump on his owner!*/
        System.out.println(name + " is fighting for honor!");
        System.out.println( name + (this == getWinner(chien) ? " jump on his owner!" : " is going to hide..."));
    }
}

/* Il n'est pas possible d'hériter d'une classe notée final
class Impossible extends Malinois {

    public Impossible(Animal parent, Animal otherParent, int age, String name) {
        super(parent, otherParent, age, name);
    }
}*/
