package a_variables;

public class Types {
    public static void main(String[] args) {
        // Déclaration de variable : C'est définir son type et son nom
        Object unObjet;
        // Instanciation : c'est affecter une valeur à une déclaration
        unObjet = new Object();
        // Les deux étapes peuvent se faire d'une traite :
        Object unAutreObjet = new Object();

        // 2 Type de variables -> Les types primitifs et les types référence

        // region PRIMITIFS
        /* un type primitif est un type auquel une taille mémoire est affectée
        de ce fait, ces types ont une valeur par défaut dès leur déclaration.
        */
        boolean aBoolean = false;       // 1 bit
        byte aByte = 0;                 // 8 bits
        short aShort = 0;               // 16 bits
        char aChar = '\u0000';          // 16 bits
        int anInt = 0;                  // 32 bits
        float aFloat = 0.0f;            // 32 bits f pour la signature differenciée du double
        double aDouble = 0.0d;          // 64 bits
        long aLong = 0;                 // 64 bits
        // endregion

        // region REFERENCE
        String aString = null;          //
        aString = "String Content";     // pour la saisie de texte : " pour les String et ' pour les char
        System.out.println("Valeur avant replace : " + aString);
    }
}
