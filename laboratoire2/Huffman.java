package laboratoire2;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class Huffman{

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    public void Compresser(String nomFichierEntre, String nomFichierSortie){

    }

    public void Decompresser(String nomFichierEntre, String nomFichierSortie){

    }

    public 
    String fileName = "";
    // utile pour connaître le nombre de caractères lus
    int numOfReadChar_textMode = 0;
    BufferedReader br = new BufferedReader(fr);

    try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")) {
        int singleCharInt;
        char singleChar;
        while((singleCharInt = br.read()) != -1) {
            singleChar = (char) singleCharInt;
            // singleChar
            numOfReadChar_textMode++;
        }
    }

    public 

}