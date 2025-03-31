package be.iccbxl.poo.mylibrary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriterTest {
    public static void main(String[] args) {
        //System.out.println("Read/Write");
        System.out.println("\n\n");

        String filename = "mylibrary/data/person2.txt";

        File f = new File(filename);
        FileWriter fw = null;
        BufferedWriter bw = null;

        String ligne = "Une ligne.";

            
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
               System.out.println("Impossible de créer le fichier!");
               System.exit(1);
            }
        }

        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);

            try {
                bw.write(ligne);
                bw.flush();
            } finally {
                fw.close();
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Fichier créé avec succès!");
    }

}
