package be.iccbxl.poo.mylibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.UUID;

import be.iccbxl.poo.mylibrary.entities.Book;
import be.iccbxl.poo.mylibrary.entities.Language;
import be.iccbxl.poo.mylibrary.entities.MyLibrary;
import be.iccbxl.poo.mylibrary.entities.NotAvailableException;
import be.iccbxl.poo.mylibrary.entities.Person;
import be.iccbxl.poo.mylibrary.entities.RangeException;

public class Main {
    public static void main(String[] args) {
        /*
         * 76.	//Affichage du menu
            77.	écrire "Menu principal
                1 - Ajouter un membre
	            2 - Ajouter un livre
                3 - Emprunter un livre
                4 - Afficher les statistiques
                5 - Sauvegarder les membres
                6 - Sauvegarder les livres
                7 - Charger les membres
                8 - Charger les livres
                0 – Quitter
         */
        MyLibrary library = new MyLibrary();
        library.setName("Nid des lecteurs");
        
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        String menuPrincipal = "Menu principal\n"
            +"\t1 - Ajouter un membre\n"
	        +"\t2 - Ajouter un livre\n"
            +"\t3 - Emprunter un livre\n"
            +"\t4 - Afficher les statistiques\n"
            +"\t5 - Sauvegarder les membres\n"
            +"\t6 - Sauvegarder les livres\n"
            +"\t7 - Charger les membres\n"
            +"\t8 - Charger les livres\n"
            +"\t0 - Quitter";

        //Affichage du menu
        do {
            try {
                System.out.println(menuPrincipal);

                //Lecture du choix
                choix = scanner.nextInt();
                scanner.nextLine(); //Vider le tampon

                //Valider l'entrée utilisateur (entier compris entre 0 et 4)
                if(choix<0 || choix>4) {
                    throw new RangeException("Choix non valide :"+choix+" ([0,1,2,3,4])");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre parmi ceux proposés.");
                scanner.nextLine(); //Vider le tampon
            } catch(RangeException e) {
                System.out.println("Veuillez entrer un nombre parmi ceux proposés.");
                scanner.nextLine(); //Vider le tampon
                choix = -1;
            }
        } while(choix==-1);

        //Traitement du choix
        switch (choix) {
            case 1:
                //Demander le nom
                System.out.println("Quel est le nom du nouveau membre ?");
                String nom = scanner.nextLine();

                //Créer une Person
                Map<LocalDate,ArrayList<Book>> loans = new TreeMap<>();
                Person p = new Person(UUID.randomUUID(),nom,LocalDate.now(),loans);
                
                //Ajouter à la library
                library.addPerson(p);
                break;
            
                case 2 :
                    //Demander les informations du livre
                    //Demander le titre
                    System.out.println("Quel est le titre du nouveau livre ?");
                    String titre = scanner.nextLine();
                    //Demander l'auteur
                    System.out.println("Quel est l'auteur du nouveau livre ?");
                    String auteur = scanner.nextLine();
                    //Demander le nombre de pages
                    System.out.println("Quel est le nombre de pages du nouveau livre ?");
                    int nbPages = scanner.nextInt();
                    //Demander la durée de prêt	
                    System.out.println("Quelle est la durée de prêt du nouveau livre ?");
                    int dureeDePret = scanner.nextInt();
                    //Demander le prix de location
                    System.out.println("Quel est le prix de location du nouveau livre ?");
                    double prixPret = scanner.nextDouble();
                    //Demander la langue
                    System.out.println("Quelle est la langue du nouveau livre ?");
                    System.out.println("1 - FR");
                    System.out.println("2 - ANG");
                    System.out.println("3 - NL");
                    int choixLangue = scanner.nextInt();
                    Language langue = null;
                    switch (choixLangue) {
                        case 1:
                            langue = Language.FR;
                            break;
                        case 2:
                            langue = Language.EN;
                            break;
                        case 3:
                            langue = Language.NL;
                            break;
                        default:
                            System.out.println("Langue non valide.");
                            break;
                    }

                    //Demander le nombre d'exemplaires
                    System.out.println("Quel est le nombre d'exemplaires du nouveau livre ?");
                    int nbExemplaires = scanner.nextInt();
                    //Demander si le livre est empruntable
                    System.out.println("Le livre est-il empruntable ? (true/false)");
                    boolean empruntable = scanner.nextBoolean();                

                    //Créer un Book
                    ArrayList<Person> emprunteurs = new ArrayList<>();
                    Book b = new Book(UUID.randomUUID(),titre,auteur,(short) nbPages,(byte) dureeDePret,prixPret,langue,(short) nbExemplaires,empruntable,emprunteurs);

                    //Ajouter à la library
                    library.addBook(b);
                    break;
                    
                case 3 :
                    //Entrer le nom du membre
                    ArrayList<Person> people = null;
                   
                    while (people == null) {
                        System.out.println("Veuillez entrer le nom de l'emprunteur ?");
                        String nomMembre = scanner.nextLine();
                        people = library.findPersonByName(nomMembre);
                        
                    }

                    //Selectionner le bon membre
                    System.out.println("Veuillez entrer le numéro correspondant l'emprunteur :");
                    int numMembre = scanner.nextInt();

                    //Recuperer le membre
                    Person person = library.getPeople().get(numMembre);

                    //Entrer le titre du livre à emprunter
                    ArrayList<Book> books = null;
                   
                    while (books== null) {
                        System.out.println("Quel est le titre du livre à emprunter ?");
                        String titreLivre = scanner.nextLine();
                        books = library.findBooksByTitle(titreLivre);   
                    }
 
                    //Selectionner le bon livre
                    System.out.println("Veuillez entrer le numéro correspondant au livre à emprunter:");
                    int numLivre = scanner.nextInt();
 
                    //Recuperer le livre
                    Book book = library.getBooks().get(numLivre);

                    //Emprunter le livre
                    try {
                       person.borrows(book);
                    } catch (NotAvailableException e) {
                        System.out.println("Le livre sélectionné n'est plus disponible..");
                    }
            
            case 4 :
                //Afficher les statistiques
                System.out.println("Nombre de livres : " + library.getBooks().size());
                System.out.println("Nombre de membres : " + library.getPeople().size());
                System.out.println("Nombre de livres empruntés : " + library.numberOfBorrowedBooks());
                System.out.println("Nombre de livres disponibles : " + library.numberOfAvailableBooks());
                break;
        
            case 5 :
                /* Mock */
                library.saveBooks(library.getBooks());
                
            
            case 6 :
                /* Mock */
                library.savePeople(library.getPeople());

                //TODO Gestion des cas d'erreur
            default:
                System.out.println("Fin du programme.");
                System.exit(0);
        }

        System.out.println(library);
    }
}