package c_objects;

// Si il n'est pas précisé, toutes classe possède un constructeur par défaut.
// Ce dernier instancie les attributs de la classe avec des valeurs par défaut, juste apres avoir appelé le constructeur vide (ou par defaut) de la classe parent
/*
* public ClassName() {
        super(); n'est pas obligatoire si il n'a pas de paramètres
    }
* */
public class BergerAllemand extends Animal {
    private int pride;

    public BergerAllemand(Animal parent, Animal otherParent, int age, String name) {
        super(parent, otherParent, age, name);
        pride = 0;
    }

    @Override
    public BergerAllemand getChild(Animal otherParent, String nom) {
        return new BergerAllemand(this,otherParent,0,nom);
    }

    public BergerAllemand(int age, String name) {
        this(null, null, age, name);
    }


    public void displayPride() {
        System.out.println(name + " show his pride !");
    }

    @Override
    public void happyBirthday() {
        super.happyBirthday();
        displayPride();
    }


    // Des lors que vous écrivez au moins un constructeur, votre classe ne dispose plus d'un constructeur par défaut
    /*public BergerAllemand() { // Ceci est un constructeur vide
        super();
    }*/

}
