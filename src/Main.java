import a_variables.Types;
import b_operateurs.Boucles;
import b_operateurs.Conditions;
import b_operateurs.MathsOperators;
import c_objects.Jardin;
import d_flux.Streams;
import e_exceptions.TryCatch;

import java.util.*;
import java.util.function.Consumer;

public class Main {

    //"Types",args -> Types.main(args)
    private static final Map<String, Consumer<String[]>> mainsMap = Map.of(
            "Types",Types::main,
            "MathsOperations", MathsOperators::main,
            "Conditions", Conditions::main,
            "Boucles", Boucles::main,
            "Jardin", Jardin::main,
            "Streams", Streams::main,
            "Exceptions", TryCatch::main
    );

    private static final String[] mainsName = mainsMap.keySet().toArray(new String[0]); // Je transforme en tableau la liste des clés de ma map

    private static String[] arguments;
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        arguments = args;
        displayMenu();
    }

    private static void displayMenu() {
        System.out.println("Saisissez le numéro du programme à lancer :");
        for (int i = 0; i < mainsName.length; i++) {
            System.out.printf(" %d\t->\t%s\n",i,mainsName[i]);
        }
        System.out.println("-1\t->\t Quitter");
        startMain(getSaisie());
    }

    private static void startMain(int index){
        if(index == -1)return; // return; dans une methode void permet d'en sortir
        try {
            mainsMap.get(mainsName[index]).accept(arguments);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("L'index saisi ne corresponds à aucun programme");
        }  catch (RuntimeException e) { // On peut catch les exceptions lancées par les autres mains
            System.out.println("Exception Main");
        } finally {
            displayMenu();
        }
    }

    public static int getSaisie() {
        Scanner scanner = new Scanner(System.in);
        int saisie;
        try {
            saisie = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("La saisie est invalide, veuillez réessayer");
            saisie = getSaisie();
        }
        return saisie;
    }
}