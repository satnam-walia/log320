package laboratoire2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Huffman{

    private static byte[] bytesFichier;
    private static List<Byte> bytesFichierDecoder;
    private static Map<Byte, Integer> byteFrequence = new HashMap<>();
    private static Noeud racine;
    private static Map<Byte, String> tableHuffmanCode = new HashMap<>();
    private static Map<String, Byte> tableHuffmanCodeDecode = new HashMap<>();

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    public void Compresser(String nomFichierEntre, String nomFichierSortie) throws IOException {
        readFile(nomFichierEntre);
        frequenceTable();
    }

    public void Decompresser(String nomFichierEntre, String nomFichierSortie){

    }

    public void readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        this.bytesFichier = Files.readAllBytes(path);
    }

    public void frequenceTable() throws IOException {
        for (Byte character : bytesFichier) {
            Integer frequency = byteFrequence.get(character);
            if (frequency == null) {
                frequency = 1;
            } else {
                frequency += 1;
            }
            byteFrequence.put(character, frequency);
        }
    }
}