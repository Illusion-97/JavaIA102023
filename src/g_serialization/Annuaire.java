package g_serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Annuaire implements Serializable { // Classe à sauvegarder
    private String name;
    private Person owner; // Pour qu'un objet d'une autre classe soit également sauvegardé, cette classe doit également implémenter Serializable
    private List<Person> contacts; // Les interfaces de collections sont prévues pour être sérialisées, le résultat dépendra donc du type de données dans la liste

    public Annuaire() {
    }

    public Annuaire(String name, Person owner, List<Person> contacts) {
        this.name = name;
        this.owner = owner;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nOwner : %s\nContacts : {\n\t%s\n}",
                name,
                owner.getName(),
                contacts.stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n\t")));
    }

    public static Annuaire createAnnuaire() {
        List<Person> contacts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            /*Person person = new Person();
            person.setName("Person "+ i);
            person.setTel(random.nextLong());*/
            contacts.add(new Person("Person "+ i, random.nextLong(0,Integer.MAX_VALUE)));
        }
        return new Annuaire("Mon Annuaire", new Person("Yanis",549685),contacts);
    }
}
