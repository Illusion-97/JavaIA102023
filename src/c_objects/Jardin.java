package c_objects;

public class Jardin {
    public static void main(String[] args) {
        /*Animal petiot = new Animal(null,null,2,"Petiot");
        petiot.happyBirthday();
        System.out.println("petiot.getAge() = " + petiot.getAge());*/

        BergerAllemand ba = new BergerAllemand(4,"Baba");
        Malinois era = new Malinois(2,"Era");
        Animal petiot = (Animal) new BergerAllemand(2,"Petiot"); // Polymorphisme (cast automatique vers une classe parent)

        ba.displayPride();
        // petiot.displayPride(); Petiot est ici considéré comme un Animal et non un BergerAllemand
        BergerAllemand petiotBA = (BergerAllemand) petiot;
        petiotBA.displayPride();
        ((BergerAllemand) petiot).displayPride();

        ba.happyBirthday();
        era.happyBirthday();
        System.out.println("petiot.getClass().getSimpleName() = " + petiot.getClass().getSimpleName());
        petiot.happyBirthday();

        BergerAllemand baChild = ba.getChild(era, "EraBaba");
        Malinois eraChild = era.getChild(petiot,"PetiotEra");
        BergerAllemand petiotChild = (BergerAllemand) petiot.getChild(new Malinois(4,"Inconnu"), "PetitInconnu");
        // Malinois petiotChildMalouf = (Malinois) petiot.getChild(new Malinois(4,"Inconnu"), "PetitInconnu"); Valable en terme de code, mais incorrect à l'exécution

        //eraChild.fight(petiot); petiot est 'considéré ici comme' un Animal et n'implémente donc pas Chien
        /*era.fight(petiotBA); // Ici considéré comme un BergerAllemand
        eraChild.fight(ba);*/
        petiotChild.fight(baChild);
    }
}
