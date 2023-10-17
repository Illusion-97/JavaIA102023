package b_operateurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Boucles {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println("Itération : " + i);
        }

        int origin = random.nextInt(1,10), bound = origin + random.nextInt(1, origin + 1);
        // origin = 5, bound= 9
        // Iteration 5 ... Iteration 6 -> 9
        System.out.printf("Origin : %d , Bound : %d %n",origin,bound);
        for (int i = origin; i <= bound; i++ ) {
            System.out.println("Itération : " + i);
        }

        int[] anIntArray = random.ints(origin,origin,bound).toArray();
        for(int i = 0; i < anIntArray.length; i++) {
            System.out.printf("index : %d , Value : %d %n",i,anIntArray[i]);

        }

        System.out.print("anIntArray : {");
        for(int value : anIntArray) { // for each : pour chaque value de type int dans anIntArray
            System.out.print(value + ", ");
        }
        System.out.println("}");

        // Solution 1
        System.out.print("anIntArray : {");
        for(int i = 0; i < anIntArray.length ; i++) {
            if (i+1 < anIntArray.length)
                System.out.print(anIntArray[i] + ", ");
            else
                System.out.println(anIntArray[i] + "}");
        }

        // Solution 2
        System.out.print("anIntArray : {");
        for (int i = 0; i < anIntArray.length; i++)
            System.out.print(anIntArray[i] + (i != anIntArray.length - 1 ? ", " : "}\n"));

        // Solution 3
        int iteration = 0;
        System.out.print("anIntArray : {");
        for(int value : anIntArray) { // for each : pour chaque value de type int dans anIntArray
            iteration++; // J'ai besoin dans ce cas d'une variable qui me permet de savoir a quel index du tableau je me trouve
            System.out.print(value + (iteration == anIntArray.length ? "}\n" :", "));
        }


        System.out.printf("anIntArray {%s}\n",
                Arrays.stream(anIntArray) // Placer le tableau dans un flux
                .mapToObj(String::valueOf) // Convertir les int en String (primitif vers Objet -> mapToObj)
                .collect(Collectors.joining(", ")) // Rassembler toutes les valeurs (String) en insérant le délimiteur entre eux
        );

        List<Integer> aRandomIntegerList = new ArrayList<>();
        for(int value : anIntArray) {
            aRandomIntegerList.add(value); // Cast implicit vers le Wrapper
        }
        aRandomIntegerList = Arrays.stream(anIntArray) // Stream<int> (IntegerStream)
                .boxed() // Permet de passer autmatiquement vers le wrapper -> Stream<Integer>
                .toList(); // permet de passer d'un Stream<Integer> à une List<Integer>

        Object[] aRandomObjectArray = aRandomIntegerList.stream().toArray();
        int[] aRandomIntArrayFromList = aRandomIntegerList.stream().mapToInt(Integer::intValue).toArray();


        System.out.print("anIntArray : {");
        for (int i = 0; i < aRandomIntegerList.size(); i++)
            System.out.print(aRandomIntegerList.get(i) + (i == aRandomIntegerList.size() ? "}\n" : ", "));

        int whileIteration = 0;
        while(whileIteration < aRandomIntegerList.size()) {
            whileIteration++; // Toujours penser a actualier la valeur ou l'element de la condition pour éviter la boucle infinie
            System.out.println("whileIteration = " + whileIteration);
        }

        boolean condition = false;
        while (condition) {
            System.out.println("Inside while (do)"); // Never reached
        }

        do {
            System.out.println("Inside do-while"); // Exécuté une fois
        } while (condition);

        boolean[][] aRandomBooleanTable = new boolean[random.nextInt(origin,bound)][random.nextInt(origin,bound)]; // Chaque ligne possede le même nombre de colonnes
        for(int i = 0; i < aRandomBooleanTable.length ;i++) { // Je parcours les lignes de mon tableau a deux dimensions
            for (int j = 0; j < aRandomBooleanTable[i].length; j++) { // je parcours les colonnes du tableau a la ligne i
                aRandomBooleanTable[i][j] = random.nextBoolean(); // j'affecte une valeur aléatoire a chaque élement du tableau aux coordonnées I J
            }
        }

        /*
            aRandomBooleanTable :
            {XOXOXXOXOXO}
            {OXOXOOXOXOX}
            {XOXOXOOOXOO}
            {XOXOXXOOXOX}
            {XXOOXXXOOOX}
            {OXOXOOOOOXX}
            {OOOOOOXXOOO}
            {XOOXOXXOXXX}
         */

        for (int i = 0 ; i< aRandomBooleanTable.length ; i++){
            System.out.print("{");
            for (int j = 0 ; j<aRandomBooleanTable[i].length;j++){
                System.out.print( aRandomBooleanTable[i][j]? "O" :"X");
                ;
            }
            System.out.println("}");
        }

        System.out.println("aRandomBooleanTable :");
        for (boolean[] line : aRandomBooleanTable) {
            System.out.print("{");
            for (boolean rowValue : line) {
                System.out.print(rowValue ? "O" : "X");
            }
            System.out.println("}");
        }
    }
}
