package a_variables;


import c_objects.Animal;

import java.util.*;

public class Types {
    public static void main(String[] args) {
        System.out.println(args[0]);
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
        // endregion

        // region WRAPPER
        Integer anIntWrapper = 0;                                   // int anInt = 0;
        int anIntFromWrapper = new Integer(0);                // Je peux créer un Objet de type réference par son contructeur eu utiliser cet objet directement dans un type primitif
        int anotherIntFromWrapper = anIntWrapper;                   // La conversion entre un wrapper et ton type primitif est automatique
        int anIntFromValueOf = Integer.valueOf(0);
        int anIntFromStringValueOf = Integer.valueOf("0");      // Ecriture est dépréciée
        int anIntFromStingParseInt = Integer.parseInt("0");     // Utilie pour valider des saisies utilisateur
        // endregion

        // region COLLECTIONS
        /* int[] anIntArray */int anIntArray[] = {150,17,29,93,46}; // On crée un tableau avec des valeurs préféfinies (La taille d'un tableau est immutable)
        System.out.println(anIntArray[0]); // 150 : les index commencent a 0 jusqu'a length -1
        int[] anotherIntArray = new int[5];
        System.out.println(anotherIntArray[0]); // 0
        String[] aStringArray = new String[10];
        System.out.println(aStringArray[0]); // null
        aStringArray[0] = "String Content"; // L'affectation se fait en précisant l'index auquel doit se trouver la valeur dans le tableau
        System.out.println(aStringArray[0]); // String Content
        //System.out.println(aStringArray[15]); // ArrayIndexOutOfBoundsException

        boolean[][] aBooleanTable = {{true,false},{false,true,true},{}};
        System.out.println(aBooleanTable[1][2]); // true

        List<String> aStringList = new ArrayList<>();
        //System.out.println(aStringList.get(0)); // IndexOutOfBoundsException en comparation avec le tableau (même si une liste à une taille par défaut) elle n'instancie aucun objet
        aStringList.add("first");
        System.out.println(aStringList.get(0)); // first
        String second = "second";
        aStringList.add(second);
        String third = "third";
        aStringList.add(0, third);
        aStringList.add(1, "fourth");
        System.out.println(aStringList.get(0)); // third
        System.out.println(aStringList.get(1)); // fourth

        System.out.println(aStringList.indexOf(second)); // 3 IndexOf me permet retrouver la place d'un élément dans ma liste
        System.out.println(aStringList.indexOf("fifth")); // -1 Quand l'objet (ou valeur) recherché n'existe pas dans la liste

        aStringList.remove(0);
        System.out.println(aStringList.get(0)); // fourth
        System.out.println("aStringList.remove(\"third\") = " + aStringList.remove("third"));

        List<String> aListFromArrayAsList = Arrays.asList("first",second,third,"fourth"); // en une ligne je crée une liste avec des valeurs prédéfinies
        List<String> aListFromListOf = List.of("first",second,third,"fourth"); // Une liste crée de ces façons est immutable (on ne pourra pas en changer la taille)

        Set<String> aStringSet = new HashSet<>(); // Les sets fonctionnent exactement comme des listes, au détail près qu'elles n'autorisent pas de doublons

        Map<String,String> aStringMap = new HashMap<>(); // prépare une collection clé/valeur qui s'assurera (comme les sets) que les clés soient unique
        aStringMap.put("first","First Value");
        String secondValue = "Second Value";
        aStringMap.put(second,secondValue);
        aStringMap.put(third,"Third Value");
        String fourthValue = "Fourth Value";
        aStringMap.put("fourth", fourthValue);

        System.out.println(aStringMap.get("fourth"));

        System.out.println(aStringMap.get("first")); // First Value
        System.out.println(aStringMap.get(second)); // Second Value

        aStringMap.put(second, "New Second Value"); // Si la clé passée à la fonction put est déjà présente, la valeur est remplacée, dans le cas contraire, elle est ajoutée
        System.out.println(aStringMap.get(second)); // New Second Value

        aStringMap.replace(third, "New Third Value"); // la fonction replace n'ajoute pas de données si la clé est absente
        System.out.println(aStringMap.get(third)); // New Third Value

        System.out.println(aStringMap.containsKey("fifth")); // false
        System.out.println(aStringMap.containsValue("Second Value")); // false

        System.out.println("aStringMap.remove(third) = " + aStringMap.remove(third)); // New Third Value
        System.out.println("aStringMap.remove(fifth) = " + aStringMap.remove("fifth")); // null


        Map<String,AMutableObject> integerMap = new HashMap<>();
        AMutableObject aMutableObject = new AMutableObject();
        integerMap.put("first",aMutableObject);
        System.out.println(integerMap.get("first")); // null
        aMutableObject.setValue("New Value");
        System.out.println(integerMap.get("first")); // New Value
        // endregion
    }
}

class AMutableObject {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}