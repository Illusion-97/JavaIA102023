package f_core;

import java.util.List;
import java.util.Scanner;

public class CheatSheet {
    private static String jvm = "JMV"; // Static variable : partagée par tous les objets (instances) de la classe
    public String title; // Déclaration de variable de classe : {visibilité} {Type / Class} {nom}
    protected int a = 1, b = 2; // Déclaration de plusieurs variables de même visibilité et type;

    public CheatSheet(String title) { // Constructeur : initialiser toutes les variables de la classe
        this.title = title; // Initialise la variable avec une valeur donnée dès la création de l'objet
        // Les variables qui ne sont pas mentionnées dans le constructeur sont initialisées avec leurs valeurs par défaut
    }

    public CheatSheet() {
        // Quelle est la valeur de title
        // Apres passage par ce constructeur, title est initialisé à null
    }

    public void setTitle(String title) {
        this.title = title; // Affecte une valeur
    }
    
    private static void printJVM() {  // Static method : partagée par tous les objets (instances) de la classe
        /*String uninstancied; // Une variable non instanciée n'est pas 'null', dans ce cas précis, elle n'a pas de valeur
        System.out.println(uninstancied);*/
        System.out.print( // Sortie de la console
                jvm // Paramètre donné à la méthode println de cette sortie
        );
    }

    public int calcul( // Déclaration de méthode : {visibilité} {Type / Class de retour} {nom}
           int firstArg, // Paramètre / Argument
           int secondArg,
           int thirdArg
    ) { // Corps de la méthode
        int result; // Déclaration de variable locale (propre à la méthode)
        result = firstArg + secondArg - thirdArg;
        return result; // return est un point de sortie de la méthode; obligatoire si la fonction a un type de retour
        // Il est possible de retrouver un return; dans une méthode void pour en stopper l'exécution
    }

    public static void main(String[] args) {
        // System.out.println(title); title est une variable d'instance, elle ne peut être directement utilisée
        System.out.println("new CheatSheet().title = " + new CheatSheet().title);

        // Une méthode statique n'a accès qu'aux autres éléments statiques de sa propre classe
        CheatSheet.printJVM();
        System.out.println();

        CheatSheet cheatSheet = new CheatSheet("Cheat Sheet"); // Création / Instanciation d'un objet
        int version = cheatSheet.calcul(cheatSheet.a, 2, cheatSheet.b); // Appel d'une méthode de l'objet en utilisant des paramètres venant de l'objet ou non
        System.out.printf("Titre : %s Version %d %n",cheatSheet.title,version); // String.format : %s String, %d pour les nombres, %n line break
        System.out.println();
        System.out.println(".java / .class / JAR / " + jvm + " / JRE / JDK"); // Concaténation
        System.out.println("La commande 'javac' compiler le code d'un fichier .java en Bytecode dans un .class");
        System.out.println("Les fichiers compilés peuvent être placés dans une archive de type JAR (ou WAR) reconnues pour être exécutés dans l'environnement de Java");
        System.out.print("The "); // print sans line break
        printJVM(); // l'appel d'une autre méthode de la même classe ne nécessite pas la précision de l'objet ou de la classe elle-même
        // Ce principe est valable pour des méthodes importées statiquement
        System.out.println("""
                 (Java Virtual Machine) lis le Bytecode des .class et l'exécute
                Puisque le code n'est pas écrit pour une machine ou un environnement spécifique, on dit que les applications Java sont 'WORA'
                Ceci signifie 'Write Once Run Anywhere' puisque toute machine avec un JRE installé est capable d'exécuter un programme java""");
        String[] jre = {jvm, "les bibliothèques JAVA contenant les classes coeur du langage"};
        System.out.println("Le JRE (Java Runtime Environment) contiens : ");
        for (String string : jre) { // ForEach() Déclaration de variable locale : collection sur laquelle itérer
            System.out.println("-> " + string);
        }
        System.out.println("Le JDK (Java Development Kit) contiens le JRE ainsi que les JDT (Java Development Tools) incluant le compiler (javac), JavaDebugger, JavaDoc, etc.");
        System.out.println("Voulez-vous afficher les version de Java bénéficiant du 'Long-Term Support' ? (Y/N)");
        String userInput = new Scanner(System.in) // Analyse l'entrée dans la console
                .next(); // Capture la premiere saisie validée par la touche Entrée
        if(userInput != null && userInput.equalsIgnoreCase("Y")) {
            List.of(
                    "-> 7 (Terminé en juillet 2022)",
                    "-> 8 (Continu jusqu'en Décembre 2030)",
                    "-> 11 (Continu jusqu'en Septembre 2026)",
                    "-> 17 (Continu jusqu'en Décembre 2029 ou plus tard)",
                    "-> 21 (Continu jusqu'en Décembre 2030)"
            ).forEach(System.out::println);
        }
    }
}
