package b_operateurs;

import java.util.Random;

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

    }
}
