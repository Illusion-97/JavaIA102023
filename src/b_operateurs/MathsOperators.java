package b_operateurs;

import java.util.Random;

public class MathsOperators {
    public static void main(String[] args) {
        // region IMPLICIT CONVERSION
        int anInt = 10;
        double aDouble = 9.36;
        float aFloat = 5.81f; // Les valeurs décimales sont traitées par défaut en tant que 'double', il faut donc préciser avec la signature 'f' pour un float

        double aDoubleFromInt = anInt;
        float aFloatFromInt = anInt;

        int anIntFromDouble = (int) aDouble;        // Double étant plus 'précis' que int, la conversion dans ce sens n'est pas automatique, il faut caster
        float aFloatFromDouble = (float) aDouble;
        int anIntFromFloat = (int) aFloat;

        int anIntResultFromIntAndInt = anInt + anInt; /* anInt * 2 */
        double aDoubleFromIntAndDouble = anInt + aDouble; // Le resultat est toujours du type le plus précis (le plus loud)
        int anIntResultFromIntAndDoubleCasted = anInt + ((int) (aDouble)); // On peut cast le parametre le plus lourd
        int anIntResultFromIntAndDouble = (int) (anInt + aDouble); // Ou directement le résultat
        //endregion

        // region Strings
        String aString = "String Content";
        System.out.println(aString + " String Added"); // + utilisé avec un ou des String procède à une concaténation
        System.out.println(aString + " and an Int : " + anInt); // ce qui résulte toujours en une chaine de charactères
        aString += " and a double : "; // réaffecte le résultat de la concaténation à la variable aString
        aString += aDouble;
        System.out.println("aString = " + aString); // String Content and a double : 9.36
        aString = aString.substring(0,14);
        System.out.println(aString); // String Content
        System.out.println(String.format("%s and a Float : %1.3f",aString,aFloat));
        /* String format permet de gérer la concaténation de plusieurs paramètre avec du texte prédéfini
        * tout en donnant la possibilité de definir un format particulier pour les nombres
        * A retenir :
        *   - %s : pour une variable de type String
        *   - %d : pour un type numérique
        *   - %X.Yf : pour un nombre décimal avec X nombres avant la virgule (remplacé par des espaces au besoin) et Y nombres après
        *   - %n : pour sauter une ligne  */
        System.out.printf("%s and a Float : %1.3f %n",aString,aFloat); // printf fonctionne de la même façon, attention il ne saute pas automatiquement de ligne
        // endregion

        // region Random
        Random random = new Random(); // Recommandation : on ne crée qu'une fois l'objet Random
        boolean randomBoolean = random.nextBoolean();
        int randomInt = random.nextInt();
        long randomLong = random.nextLong();
        int randomIntWithBound = random.nextInt(10);                      // From 0 to 9 (bound est exclus)
        int randomIntFromTo = random.nextInt(5,16);                 // From 5 to 15
        double[] aRandomDoubleArray = random.doubles(5).toArray();     // crée un tableau de 5 doubles aléatoires (en utilisant des streams)
        int[] aRandomIntArray = random.ints(5, 1, 101).toArray(); // les limitations s'écrivent de la même façon que l'obtention d'une valeur unique
        // endregion
    }
}
