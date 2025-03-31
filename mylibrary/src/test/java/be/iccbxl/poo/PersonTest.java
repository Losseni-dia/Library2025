package be.iccbxl.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import be.iccbxl.poo.mylibrary.entities.Book;
import be.iccbxl.poo.mylibrary.entities.NotAvailableException;
import be.iccbxl.poo.mylibrary.entities.Person;

public class PersonTest {

    @Test
    public void borrows() {
        //Préparation
        Map<LocalDate, ArrayList<Book>> loans = new TreeMap<LocalDate, ArrayList<Book>>();
        Person bob = new Person(UUID.randomUUID(),"Bob",LocalDate.of(2020, 10, 25), loans);
        Book book = new Book("Une vie","Guy de Maupassant",(short)210);
        
        //Utilisation
        bob.borrows(book);

        //Vérifications
        ArrayList<Book> emprunts = bob.getLoans().get(LocalDate.now());

        assertEquals(1, emprunts.size());
    }

    @Test
    public void emprunterWithPlusDisponibleShouldFail() {
        //Préparation
        Map<LocalDate, ArrayList<Book>> loans = new TreeMap<LocalDate, ArrayList<Book>>();
        Person bob = new Person(UUID.randomUUID(),"Bob",LocalDate.of(2020, 10, 25), loans);
        Person lydia = new Person(UUID.randomUUID(),"Bob",LocalDate.of(2020, 10, 25), loans);
        Book book = new Book("Une vie","Guy de Maupassant",(short)210);
        
        //Utilisation
        bob.borrows(book);    //1 seul exemplaire
        
        
        //Vérifications
        assertThrows(NotAvailableException.class, () -> {
            //Utilisation
            lydia.borrows(book);
        });
        
        ArrayList<Book> emprunts = lydia.getLoans().get(LocalDate.now());

        assertEquals(0, emprunts.size());
    }

}
