package laboratoire3;
import java.io.*;
import java.util.*;

public class DocDist {

    public static void main(String[] args) throws Exception {
       
        String file1 = "laboratoire3/files/monte_cristo_1-Dumas.txt";
        String file2 = "laboratoire3/files/adventures_of_Sherlock_Holmes-Doyle.txt";
    
        Map<String, Integer> freq1 = getWordFreq(file1);
        Map<String, Integer> freq2 = getWordFreq(file2);

        Map<String, Integer> freq1Dist = getTotalDistinctWords(freq1);
        Map<String, Integer> freq2Dist = getTotalDistinctWords(freq2);
        
        double magnitude1 = computeMagnitude(freq1Dist);
        double magnitude2 = computeMagnitude(freq2Dist);

        double scalarProduct = computeScalarProduct(freq1Dist, freq2Dist);
        
        double cosineSimilarity = scalarProduct / (magnitude1 * magnitude2);


        System.out.printf("Fichier %s : %d mots, %d mots distincts\n", file1, getTotalWords(freq1), freq1Dist.size());
        System.out.printf("Fichier %s : %d mots, %d mots distincts\n", file2, getTotalWords(freq2), freq2Dist.size());
        System.out.printf("La distance cosinus entre les deux documents est de %.6f radians.\n", Math.acos(cosineSimilarity));
    }

    private static Map<String, Integer> getWordFreq(String filename) throws Exception {
        Map<String, Integer> freqMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue; // Skip empty lines
            }
            String[] words = line.split("[^\\p{L}\\p{M}0-9]+|(?<=\\()|(?=\\))");
            for (String word : words) {
                word = word.replaceAll("^[\\(\\)]+|[\\(\\)]+$", ""); // Remove any leading or trailing parentheses
                if (!word.isEmpty() && !word.equals("-")) { // Only add non-empty words that are not just a hyphen
                    if (word.matches("\\d{4}-\\d{4}")) { // If the word is a date range
                        String[] dateRangeWords = word.split("-"); // Split the date range into two words
                        for (String dateRangeWord : dateRangeWords) {
                            freqMap.put(dateRangeWord, freqMap.getOrDefault(dateRangeWord, 0) + 1);
                        }
                    } else { // Otherwise, add the word to the frequency map
                        freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }
        br.close();
        return freqMap;
    }
    
    
    private static int computeScalarProduct(Map<String, Integer> freq1, Map<String, Integer> freq2) {
        int scalarProduct = 0;
        Set<String> words1 = freq1.keySet();
        Set<String> words2 = freq2.keySet();
        for (String word1 : words1) {
            for (String word2 : words2) {
                if (word1.equals(word2)) {
                    scalarProduct += freq1.get(word1) * freq2.get(word2);
                }
            }
        }
        return scalarProduct;
    }

    private static double computeMagnitude(Map<String, Integer> freq) {
        int sumOfSquares = 0;
        Collection<Integer> values = freq.values();
        for (int value : values) {
            sumOfSquares += value * value;
        }
        return Math.sqrt(sumOfSquares);
    }

    private static int getTotalWords(Map<String, Integer> freq) {
        int totalWords = 0;
        Collection<Integer> values = freq.values();
        for (int value : values) {
            totalWords += value;
        }
        return totalWords;
    }

    private static Map<String, Integer> getTotalDistinctWords(Map<String, Integer> freq) {
        Map<String, Integer> distinctWords = new HashMap<>();
        for (String word : freq.keySet()) {
            String lowerCaseWord = word.toLowerCase();
            if (distinctWords.containsKey(lowerCaseWord)) {
                distinctWords.put(lowerCaseWord, distinctWords.get(lowerCaseWord) + freq.get(word));
            } else {
                distinctWords.put(lowerCaseWord, freq.get(word));
            }
        }
        return distinctWords;
    }
}
