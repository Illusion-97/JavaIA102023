package c_objects;

public abstract class Animal {
    private static long count = 0; // Commun à toutes les instances de la classe (ont tous accès à la valeur en cours)

    public enum AgeRange { // est statique par défaut
        JEUNE,ADULTE,SENIOR
    }
    // Jeune age < 3 / Senior age >= 6

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

    protected Animal(Animal parent, Animal otherParent, int age, String name) {
        //parents = new Animal[]{parent,otherParent}; // Crée directement un nouveau tableau avec les objets passés en parametre
        this(); // Appelle son propre constructeur sans parametre
        this.age = age;
        this.name = name;
        // parents = new Animal[]{parent,otherParent}; Ayant déjà été attribué, je ne peux pas reaffecter un attribut final
        // par contre je peux mofidier son contenu
        parents[0] = parent;
        parents[1] = otherParent;
    }

    private Animal() {
        // parent étant final, il doit être instancié ici
        parents = new Animal[2];
        count++;
        id = count;
    }

    //public abstract <Child extends Animal> Child getChild(Animal otherParent, String nom); // Type générique
    public abstract Animal getChild(Animal otherParent, String nom); // Type générique

    public void happyBirthday() { // je peut rendre accessible un comportement utilisant l'attribut privé et tout en gardant la responsabilité de sa modification
        age++;
        System.out.printf("Happy Birthday %s : %d an(s) %n",name,age); // String.format("template", ...variables)
    }

    public int getAge() { // je rends accessible l'information 'brute' sans donner la possibilité de la modifier
        return age;
    }

    protected AgeRange getAgeRange() {
        // Jeune age < 3 / Senior age >= 6
        /*if(age < 3) return AgeRange.JEUNE;
        else if (age >= 6) return AgeRange.SENIOR;
        return AgeRange.ADULTE;  */

        return age < 3 ? AgeRange.JEUNE : (age >= 6 ? AgeRange.SENIOR : AgeRange.ADULTE);
    }

    @Override
    public String toString() {
        return name;
    }
}
