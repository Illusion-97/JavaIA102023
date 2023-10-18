import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in); Scanner analyse une entrée, System.in représente une saisie dans la console
        System.out.println("Hello world!");
        System.out.println("Veuillez saisir un suite de caractères en minuscule");
        System.out.println("saisie = " + getSaisie());
    }

    private static String getSaisie() {
        Scanner scanner = new Scanner(System.in);
        String saisie;
        try {
            saisie = scanner.next("[a-z]*");
        } catch (InputMismatchException e) {
            System.out.println("La saisie est invalide, veuillez réessayer");
            saisie = getSaisie();
        }
        return saisie;
    }
}