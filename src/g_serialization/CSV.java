package g_serialization;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class CSV {
    private final String FILENAME = "Annuaire.csv";

    // La personnalisation d'un fichier CSV étant arbitraire, il vaut parfois mieux fixer dans des constantes les choix effectués afin de centraliser leur gestion
    private static final String DELIMITER = ";"; // Ceci est la syntaxe d'une constante : par convention les noms de constantes sont en majuscule ;
    private static final String PERSON_DELIMITER = ",";
    private static final String SEPARATOR = "\n";

    public static void main(String[] args) {
        CSV csv = new CSV();
        csv.exportAnnuaire(Annuaire.createAnnuaire());

    }

    public void exportAnnuaire(Annuaire annuaire) {
        try (FileWriter fw = new FileWriter(FILENAME)) {
            fw.append(annuaire.getName());
            fw.append(DELIMITER);
            appendPerson(annuaire.getOwner(), fw);
            fw.append(SEPARATOR);
            annuaire.getContacts().forEach( person -> {
                // Pour rappel une lambda est une simplification de l'écriture d'une classe implémentant une interface
                // Si à l'intérieur de l'unique méthode de cette classe, des exceptions peuvent être levées, je dois immédiatement les gérer
                try {
                    appendPerson(person,fw);
                    fw.append(SEPARATOR);
                } catch (IOException e) {
                    System.out.println("Impossible d'écrire les informations de : " + person);
                }
            });

        } catch (IOException e) {
            System.out.println("Something went wrong : " + e.getMessage());
        }
    }

    private static void appendPerson(Person person, FileWriter fw) throws IOException {
        fw.append(person.getName());
        fw.append(PERSON_DELIMITER);
        fw.append(Objects.toString(person.getTel()));
    }

    public Annuaire importAnnuaire() {
      return null;
    }
}
