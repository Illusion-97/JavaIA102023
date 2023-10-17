package c_objects;

// Si il n'est pas précisé, toutes classe possède un constructeur par défaut.
// Ce dernier instancie les attributs de la classe avec des valeurs par défaut, juste apres avoir appelé le constructeur vide (ou par defaut) de la classe parent
/*
* public ClassName() {
        super(); n'est pas obligatoire si il n'a pas de paramètres
    }
* */
public class BergerAllemand extends Animal implements Chien {
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

    @Override
    public void bark() {
        // si pride == 0 "aww" sinon je voudrait qu'il repete 'WOFF' autant de fois qu'il a de pride
        // WOUFWOUFWOUF
        bark(pride <= 0 ? "aww" : "WOFF".repeat(pride));
    }

    @Override
    public void fight(Chien chien) {
        // Rex fights for his pride!
        // Si le Berger Allemand gagne, sa fierté est incrémentée, et il le montre !
        System.out.println(name + " fights for his pride!");
        if(getWinner(chien) == this){
            pride++;
            displayPride();
        }
        // Les condition pour une ternaire :
        // Le résultat d'une ternaire est toujours utilisé (soit une affectation soit passage ne tant que parametre)
        // Puisque c'est toujours utilisé, il faut traiter les 2 cas (condition vraie ou fausse)
        // Si on ne réalise une action que dans un seul des cas (condition vraie) on ne peut deja pas utiliser de ternaire
        // de plus l'incrémentaton et l'appel de displayPride() ne retournent rien.
    }


    // Des lors que vous écrivez au moins un constructeur, votre classe ne dispose plus d'un constructeur par défaut
    /*public BergerAllemand() { // Ceci est un constructeur vide
        super();
    }*/

}
