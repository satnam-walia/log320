package laboratoire2;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

//test
public class Huffman{

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    public void Compresser(String nomFichierEntre, String nomFichierSortie) throws FileNotFoundException,IOException {
        readFile(nomFichierEntre);
    }

    public void Decompresser(String nomFichierEntre, String nomFichierSortie){

    }



    public void readFile(String fileName) throws FileNotFoundException,IOException {
        int numOfReadChar_textMode = 0; // pour connaître le nombre de caractères lus
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Hashtable<String, Integer> freqTable = new Hashtable<>();
        /* TODO : pour chaque byte => voir s'il existe déjà dans la table
        ** TODO : si non : l'ajouter
        ** TODO : si oui; ajouter +1 à sa fréquence
        */

        try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")) {
            int singleCharInt;
            char singleChar;
            while((singleCharInt = inputStreamReader.read()) != -1) {
                singleChar = (char) singleCharInt;
                numOfReadChar_textMode++;
            }
        }
    }

}