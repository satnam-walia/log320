package laboratoire2;
import java.io.IOException;
//import laboratoire2.Huffman;

public class main {
    public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();

		Huffman algoHuff = new Huffman();
		algoHuff.Compresser("C:/Users/15147/Downloads/demo_compression_win10/LOG210-seance-05.pdf", "C:/Users/15147/Downloads/demo_compression_win10/LOG210-seance-051.pdf");
		algoHuff.Decompresser("C:/Users/15147/Downloads/demo_compression_win10/LOG210-seance-051.pdf", "C:/Users/15147/Downloads/demo_compression_win10/LOG210-seance-052.pdf");
		algoHuff.Compresser("C:/Users/15147/Downloads/demo_compression_win10/exemple.txt", "C:/Users/15147/Downloads/demo_compression_win10/exemple1.txt");
		algoHuff.Decompresser("C:/Users/15147/Downloads/demo_compression_win10/exemple1.txt", "C:/Users/15147/Downloads/demo_compression_win10/exemple2.txt");
		algoHuff.Compresser("C:/Users/15147/Downloads/demo_compression_win10/exempledemo.mp4", "C:/Users/15147/Downloads/demo_compression_win10/exempledemo1.mp4");
		algoHuff.Decompresser("C:/Users/15147/Downloads/demo_compression_win10/exempledemo1.mp4", "C:/Users/15147/Downloads/demo_compression_win10/exempledemo2.mp4");
		algoHuff.Compresser("C:/Users/15147/Downloads/demo_compression_win10/exemplePdf.pdf", "C:/Users/15147/Downloads/demo_compression_win10/exemplePdf1.pdf");
		algoHuff.Decompresser("C:/Users/15147/Downloads/demo_compression_win10/exemplePdf1.pdf", "C:/Users/15147/Downloads/demo_compression_win10/exemplePdf2.pdf");
		algoHuff.Compresser("C:/Users/15147/Downloads/demo_compression_win10/trigrams_COCA.txt", "C:/Users/15147/Downloads/demo_compression_win10/trigrams_COCA1.txt");
		algoHuff.Decompresser("C:/Users/15147/Downloads/demo_compression_win10/trigrams_COCA1.txt", "C:/Users/15147/Downloads/demo_compression_win10/trigrams_COCA2.txt");
		
		long elapsedTime = System.nanoTime() - startTime;

		System.out.println("Total execution time to create 1000K objects in Java in millis: "
				+ elapsedTime/1000000);
	}
}

