package g_serialization;

import c_objects.generic.Serializer;

import java.io.*;

public class TXT implements Serializer {
    private final String FILENAME = "Annuaire-bis.txt";

    public static void main(String[] args) {
        // La sérialisation c'est le principe permettant la sauvegarde et l'import d'instances d'objets d'un programme dans des systèmes de stockage externe
        TXT txt = new TXT();
        Annuaire annuaire;
        try {
            annuaire = txt.importAnnuaire();
        } catch (Exception e) {
            System.out.println("Impossible d'importer l'annuaire.\nTentative d'export puis nouvel essai");
            txt.exportAnnuaire(Annuaire.createAnnuaire());
            try {
                annuaire = txt.importAnnuaire();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Echec de la récupération d'information, Fin du programme");
                return;
            }
        }
        System.out.println("annuaire = " + annuaire);
    }

    @Override
    public void exportAnnuaire(Annuaire annuaire) {
        /*FileOutputStream os = new FileOutputStream(FILENAME); // Outil d'écriture dans un fichier
        ObjectOutputStream oos = new ObjectOutputStream(os); // Meme outil, mais adapté à la sérialization d'objets
        oos.writeObject(annuaire);
        oos.close(); // Toujours fermer le flux d'accès a un fichier
        os.close();*/


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) { // try with resources
            oos.writeObject(annuaire);
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e.getMessage());
        }// La syntaxe d'un try with ressources permet d'automatiquement fermer les flux ouverts durant la déclaration
    }

    @Override
    public Annuaire importAnnuaire() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (Annuaire) ois.readObject();
        } // Ici try ne me sert qu'à fermer les flux ouverts, la gestion des exceptions et laissée aux fonctions appelante
    }
}
