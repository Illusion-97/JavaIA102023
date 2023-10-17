package c_objects;

public class Animal {
    private static long count = 0; // Commun à toutes les instances de la classe (ont tous accès à la valeur en cours)

    public enum AgeRange { // est statique par défaut
        JEUNE,ADULTE,SENIOR
    }

    /*
    * Visibilité : D'où on peut accéder a un attribut ou une methode
    *  -> public : visible (accessible) de n'importe ou dans le programme
    *  -> private : uniquement dans la classe elle-même;
    *  -> default : visible par toutes les classes dans le même package
    *  -> protected : comme default mais également pour les classes enfant en dehors du package
    * */
    // final fixe une valeur : elle oblige son instanciation dans le constructeur, et n'en autorise 'aucune' modification
    final Animal[] parents; // Ne pas préciser de visibilité rends automatique 'default'
    public long id;
    private int age;
    protected String name;

    public Animal(Animal parent, Animal otherParent, int age, String name) {
        //parents = new Animal[]{parent,otherParent}; // Crée directement un nouveau tableau avec les objets passés en parametre
        this(); // Appelle son propre constructeur sans parametre
        this.age = age;
        this.name = name;
        // parents = new Animal[]{parent,otherParent}; Ayant déjà été attribué, je ne peux pas reaffecter un attribut final
        // par contre je peux mofidier son contenu
        parents[0] = parent;
        parents[1] = otherParent;
    }

    public Animal() {
        // parent étant final, il doit être instancié ici
        parents = new Animal[2];
        count++;
        id = count;
    }
}
