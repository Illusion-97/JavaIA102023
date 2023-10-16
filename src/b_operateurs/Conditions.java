package b_operateurs;

import java.util.Random;

public class Conditions {
    public static void main(String[] args) {
        Random random = new Random();

        // region Two Cases (if/else)
        int a = random.nextInt(), b = random.nextInt(5), c = random.nextInt(2,5);
        System.out.printf("a = %d; b = %d; c = %d %n",a,b,c);
        if (a == b) System.out.println("a == b");
        else if (a == c) System.out.println("a == c");
        else if (b == c) System.out.println("b == c");
        else System.out.println("a != b != c");

        if (a == b && a == c) {
            System.out.println("a == b == c");
        } else {
            if (a == b) System.out.println("a == b");
            else System.out.println("a != b");

            if (a == c) System.out.println("a == c");
            else System.out.println("a != c");
        }

        if (a == b && a == c) {
            System.out.println("a == b == c");
        } else if (a != b && a != c && b != c) {
            System.out.println("a != b != c");
        } else {
            if (a == b) System.out.println("a == b");
            else System.out.println("b == c");
        }

        if(random.nextBoolean()) System.out.println("Condition vraie");
        else System.out.println("Condition fausse");

        String result = random.nextBoolean() ? "vraie" : "fausse"; // Ternaire
        System.out.println("Condition " + result);

        System.out.println("Condition " + (random.nextBoolean() ? "vraie" : "fausse"));
        // endregion

        // region Multiple cases
        switch (b) { // b range : 0 à 4
            case 0:
                System.out.println("Valeur d'origine");
                break;
            case 2:
                System.out.println("Valeur médianne");
                break;
            case 4:
                System.out.println("Valeur maximale");
                break;
            default:
                System.out.println("Impair");
        }

        // La syntaxe ci dessous fonctionne de manière identique à la précendente, elle s'utilise dans la situation ou chaque cas explicite contiens un break;
        switch (b) { // b range : 0 à 4
            case 0 -> System.out.println("Valeur d'origine");
            case 2 -> System.out.println("Valeur médianne");
            case 4 -> System.out.println("Valeur maximale");
            default -> System.out.println("Impair");
        }

        /*
        0 -> Valeur d'origine
           -> Valeur basse
        1 -> Valeur basse
        2 -> Valeur médianne
        3 -> Valeur haute
        4 -> Valeur maximale
          -> Valeur haute
         */

        switch (b) {
            case 0:
                System.out.println("Origin Value");
            case 1:
                System.out.println("Lower Half");
                break;
            case 4:
                System.out.println("Bound Value");
            case 3:
                System.out.println("Upper Half");
                break;
            default:
                System.out.println("Mid Value");
        }

        //endregion

    }
}
