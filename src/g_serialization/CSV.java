package g_serialization;

import c_objects.generic.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSV implements Serializer {
    private final String FILENAME = "Annuaire.csv";

    // La personnalisation d'un fichier CSV étant arbitraire, il vaut parfois mieux fixer dans des constantes les choix effectués afin de centraliser leur gestion
    private static final String DELIMITER = ";"; // Ceci est la syntaxe d'une constante : par convention les noms de constantes sont en majuscule ;
    private static final String PERSON_DELIMITER = ",";
    private static final String SEPARATOR = "\n";

    public static void main(String[] args) {
        CSV csv = new CSV();
        Annuaire annuaire;
        try {
            annuaire = csv.importAnnuaire();
        } catch (Exception e) {
            System.out.println("Impossible d'importer l'annuaire.\nTentative d'export puis nouvel essai");
            csv.exportAnnuaire(Annuaire.createAnnuaire());
            try {
                annuaire = csv.importAnnuaire();
            } catch (IOException ex) {
                System.out.println("Echec de la récupération d'information, Fin du programme");
                return;
            }
        }
        System.out.println("annuaire = " + annuaire);
    }

    @Override
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

    @Override
    public Annuaire importAnnuaire() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) { // BufferedReader me permet de lire mon fichier, ligne par ligne
            String line = bufferedReader.readLine(); // bufferedReader.readLine() me retourne sous forme de String, la ligne suivante ou null s'il n'y en pas plus.
            String[] annuaireDatas = line.split(DELIMITER);
            /*List<Person> contacts = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                contacts.add(readPerson(line));
            }*/
            return new Annuaire(annuaireDatas[0],readPerson(annuaireDatas[1]),bufferedReader.lines().map(CSV::readPerson).toList());
        }
    }

    private static Person readPerson(String person) {
        String[] personDatas = person.split(PERSON_DELIMITER);
        return new Person(personDatas[0],Long.parseLong(personDatas[1]));
    }
}
