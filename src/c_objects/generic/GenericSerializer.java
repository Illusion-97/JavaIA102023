package c_objects.generic;

import g_serialization.Annuaire;
import g_serialization.CSV;
import g_serialization.TXT;

import java.io.IOException;

public class GenericSerializer<T extends Serializer> {
    private final T serializer;

    public GenericSerializer(T serializer) {
        this.serializer = serializer;
    }

    private void serialize() {
        Annuaire annuaire;
        try {
            annuaire = serializer.importAnnuaire();
        } catch (Exception e) {
            System.out.println("Impossible d'importer l'annuaire.\nTentative d'export puis nouvel essai");
            serializer.exportAnnuaire(Annuaire.createAnnuaire());
            try {
                annuaire = serializer.importAnnuaire();
            } catch (Exception ex) {
                System.out.println("Echec de la récupération d'information, Fin du programme");
                return;
            }
        }
        System.out.println("annuaire = " + annuaire);
    }

    public static void main(String[] args) {
        /*GenericSerializer<CSV> csvSerializer = new GenericSerializer<>(new CSV());
        System.out.println("CSV Serializer");
        csvSerializer.serialize();

        GenericSerializer<TXT> txtSerializer = new GenericSerializer<>(new TXT());
        System.out.println("TXT Serializer");
        txtSerializer.serialize();*/

        doSerialization(new GenericSerializer<>(new CSV()));
        doSerialization(new GenericSerializer<>(new TXT()));
    }

    private static void doSerialization(GenericSerializer<Serializer> serializer) {
        //String currentGenericClass = serializer.getClass().getGenericInterfaces()[0].getTypeName();
        System.out.println(" Serializer");
        serializer.serialize();
    }
}
