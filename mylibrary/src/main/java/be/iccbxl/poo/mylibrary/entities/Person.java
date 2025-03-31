package be.iccbxl.poo.mylibrary.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable  {
    private UUID id;
    private String name;
    private LocalDate registrationDate;
    private Map<LocalDate, ArrayList<Book>> loans = new TreeMap<LocalDate, ArrayList<Book>>();

    public ArrayList<Book> getLateBooks() {
        //TODO
        return null;
    }

    public void borrows(Book book)  {
        if (!book.isBorrowable()){
            System.out.println("Le livre n'est pas empruntable.");
            return;  
        }
        if(book.getBorrowers().size() >= book.getNbCopies()) {
            System.out.println("Le livre n'est pas disponible.");
            LocalDate dateRetour = LocalDate.now().plusDays(book.getLoadPeriod());
            System.out.println("Le livre sera disponible le " + dateRetour);  
            throw new NotAvailableException();      
            
        }
        if (this.loans.size() >= MyLibrary.BOOK_LIMIT) {
            System.out.println("Vous avez atteint la limite de livres empruntés.");
            return;
        }
        for (Map.Entry<LocalDate, ArrayList<Book>> emprunt : this.loans.entrySet()) {
            if (emprunt.getValue().contains(book)) {
                System.out.println("Vous avez déjà emprunté ce livre.");
                return;
            } 
        }
        if (this.loans.containsKey(LocalDate.now())) {
            this.loans.get(LocalDate.now()).add(book);
        } else {
            ArrayList<Book> books = new ArrayList<>();
            books.add(book);
            this.loans.put(LocalDate.now(), books);
            book.getBorrowers().add(this);
        }
        
    }
}
