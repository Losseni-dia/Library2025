package be.iccbxl.poo.mylibrary.entities;


import java.util.ArrayList;
import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyLibrary {
    private String name;

    /**
	 * Liste des livres
	 */
	private ArrayList<Book> books = new ArrayList<>();
	
	/**
	 * Liste des membres
	 */
	private ArrayList<Person> people = new ArrayList<>();

    public final static byte BOOK_LIMIT = 3;

    public ArrayList<Book> findBooksByTitle(String title) {
        //TODO
        return null;
    }


    public void addBook(Book book) {
		this.books.add(book);
	}

	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	public void printBooks() {
		Iterator<Book> it = this.getBooks().iterator();
		
		while(it.hasNext()) {
			Book b = it.next();						
			System.out.println(b.getTitle() + " - " + b.getAuthor());
		}
	}

	public void printMembers() {
		Iterator<Person> it = this.getPeople().iterator();
		
		while(it.hasNext()) {
			Person p = it.next();		
			System.out.println(p.getName() + ", inscrit le " + p.getRegistrationDate());
		}
	}

	public ArrayList<Person> findPersonByName(String name) {
		ArrayList<Person> people = new ArrayList<>();

		for (Person p : this.getPeople()) {
			if(p.getName().equals(name)) {
				System.out.println(this.getPeople().indexOf(p)+" _, "+p.getName() + ", inscrit le " + p.getRegistrationDate());
			people.add(p);
			}

			return people;
		}
		System.out.println("Ce membre n'existe pas.");
		return null;	
	}

	public ArrayList<Book> findBookByTitle(String title) {
		ArrayList<Book> books = new ArrayList<>();

		for (Book b : this.getBooks()) {
			if(b.getTitle().equals(title)) {
				System.out.println(this.getBooks().indexOf(b)+" _, "+b.getTitle() + " - " + b.getAuthor());

			books.add(b);
			}

			return books;
		}
		
		System.out.println("Ce livre n'existe pas.");
		return null;
	}

	public void saveBooks(ArrayList<Book> books) {
		

	}
	public void savePeople(ArrayList<Person> people) {
		//TODO
	}

    public int numberOfBorrowedBooks() {
		int count = 0;

		for(Person p : this.getPeople()) {
			for (ArrayList<Book> booklist : p.getLoans().values()) {
				if( booklist.size()> 0) {
					count += booklist.size(); 
				}
			}	
		}

		return count;
	}

    public int numberOfAvailableBooks() {
		int nbBookAvailable = 0;

	    nbBookAvailable = this.getBooks().size() - this.numberOfBorrowedBooks();

		return nbBookAvailable;
    };

}
