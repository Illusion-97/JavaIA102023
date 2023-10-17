package c_objects;

import java.util.Random;

/*
* D'origine, une insterface définit un "Contrat" que doit respecter une classe
* On précise des signatures de méthodes que l'on doit retrouver dans les classes qui implémentent l'interface
* */
public interface Chien {
    String victoryBark = "AHOU !"; // Comportement similaire a une variable statique qui est surtout immutable. Pour être plus précis il s'agit d'une forme de constante.
    void bark(); // Signature de méthode
    default void bark(String voice) {
        System.out.println(voice);
    }
    void fight(Chien chien);

    // Une fonction par défaut dans une interface peut être réécrite (override) mais si elle ne l'est pas, le comportement par défaut est déjà prévu
    default Chien getWinner(Chien enemy) {
        Chien winner = new Random().nextBoolean() ? this : enemy;
        winner.bark(String.format("%s : %s", winner,Chien.victoryBark));
        return winner;
    }
}
