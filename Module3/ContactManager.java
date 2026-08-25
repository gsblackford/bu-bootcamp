import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager {
    HashMap<String, Contact> contacts;
    
    public ContactManager() {
        contacts = new HashMap<>();
    }

    public void addContact(String name, String phoneNumber) {
        contacts.put(name, new Contact(name, phoneNumber));
    }

    public void getContact(String name) {
        Contact contact = contacts.get(name); 
        if (contact != null) { 
            System.out.println(contact); 
        } else { 
            System.out.println("Contact not found."); 
        }
    }

    public void printAllContacts() {
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));  
        
        System.out.println("=== All Contacts === ");
        for (Contact c : sorted) {
            System.out.println(c);
        }
    }
 
    public static void main(String[] args) { 
        ContactManager manager = new ContactManager();
 
        // Step 4: add contacts here 
        manager.addContact("Ada Lovelace", "+1 617 555 0101");
        manager.addContact("Alan Turing", "+1 617 555 0102");
        manager.addContact("Grace Hopper", "+1 617 555 0103");
        manager.addContact("John von Neumann", "+1 617 555 0104");
        manager.addContact("Katherine Johnson", "+1 617 555 0105");

 
        // Step 5a: look up a known contact 
        manager.getContact("Ada Lovelace");

        // Step 5b: look up an unknown contact 
        manager.getContact("Unknown Contact");
 
        // Step 6: print sorted list
        manager.printAllContacts();

    } 
}