package c_objects.generic;

import g_serialization.Annuaire;

import java.io.IOException;

public interface Serializer {
    void exportAnnuaire(Annuaire annuaire);
    Annuaire importAnnuaire() throws Exception;
}
