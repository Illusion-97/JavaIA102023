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
        /* un type réference se comporte à la manière d'un pointeur, initialement il n'y a pas de taille mémoire prédéfinie
        il faudra donc toujours affecter une valeur à une déclaration de type primitif
        un type reference va pointer vers une ou plusieurs valeurs de type primitives (ou également réference)
        afin que la taille affectée en mémoire corresponde uniquement au types primitifs nécessaires au stockage de l'information
         */
        String aString = null;                                          // Par défaut un String est immutable
        aString = "String Content";                                     // pour la saisie de texte : " pour les String et ' pour les char
        System.out.println("Valeur avant replace : " + aString);        // Valeur avant replace : String Content
        aString.replace(" ", "");                       // Une modification de la valeur de l'objet n'est pas automatiquement réaffectée
        System.out.println("Valeur après replace : " + aString);        // Valeur après replace : String Content
        aString = aString.replace(" ", "");             // Pour que la modification de la valeur soit effective, je dois la réassigner à la variable déclarée
        System.out.println("Valeur après réaffectation : " + aString);  // Valeur après réaffectation : StringContent

        Object parent = null; // Toutes les classes héritent automatiquement de la classe Object, classe qui elle même est un type reference
        // enregion

        // region WRAPPER
        Integer anIntWrapper = 0;                                   // int anInt = 0;
        int anIntFromWrapper = new Integer(0);                // Je peux créer un Objet de type réference par son contructeur eu utiliser cet objet directement dans un type primitif
        int anotherIntFromWrapper = anIntWrapper;                   // La conversion entre un wrapper et ton type primitif est automatique
        int anIntFromValueOf = Integer.valueOf(0);
        int anIntFromStringValueOf = Integer.valueOf("0");      // Ecriture est dépréciée
        int anIntFromStingParseInt = Integer.parseInt("0");     // Utilie pour valider des saisies utilisateur
        // endregion

    }
}
