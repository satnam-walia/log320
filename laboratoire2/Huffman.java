package laboratoire2;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Huffman implements Comparable<Huffman>
{

    /**
     * Compressez le flux d'entrée dans le flux de sortie en utilisant le codage Huffman
     * @param nomFichierEntre, in2 le flux d'entrée à compresser
     * @param nomFichierSortie le flux de sortie pour écrire les données compressées
     **/

    public void Compresser(String nomFichierEntre, String nomFichierSortie) throws FileNotFoundException,IOException {
        File f1 = new File(nomFichierEntre);
        boolean isInExist = f1.exists();
        File f2 = new File(nomFichierSortie);
        boolean isOutExist = f2.exists();

        if (!isInExist)
            System.out.println("Le fichier d'entrée de compression n'existe pas.");
        else if (isOutExist)
            System.out.println("Le fichier de sortie de compression existe deja. Veuillez l'enlever.");
        else {
            try {
                FileInputStream fis1 = new FileInputStream(nomFichierEntre);
                FileInputStream fis2 = new FileInputStream(nomFichierEntre);
                FileOutputStream fos = new FileOutputStream(nomFichierSortie);

                compress_process(fis1, fis2, fos);

                fis1.close();
                fis2.close();
                fos.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Fichier introuvable.");
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

	/**
	* Compressez le flux d'entrée dans le flux de sortie en utilisant le codage Huffman
	* @param in1 le flux d'entrée à compresser
	* @param in2 le flux d'entrée à compresser
	* @param out le flux de sortie pour écrire les données compressées
	**/
    public String compress_process(InputStream in1, InputStream in2, OutputStream out) {
        try {
            this.out = out;
            byte[] buffer = new byte[10240];
            int searching = in1.read(buffer);
            int[] frequency = new int[256];
            while (searching > 0) {
                for (int i = 0; i < searching; i++) {
                    frequency[(int) buffer[i] + 128]++;
                }
                searching = in1.read(buffer);
            }
            in1.close();
            PriorityQueue<Huffman> ph = new PriorityQueue();
            Huffman null_tree = new Huffman();
            for (int i = 0; i < 256; i++) {
                if (frequency[i] > 0) {
                    ph.offer(new Huffman((byte) (i - 128), null_tree,
                            null_tree, frequency[i]));
                }
            }
            if (ph.size() == 1) {
                ph.offer(new Huffman((byte) 0, null_tree, null_tree, 0));
            }
            while (ph.size() > 1) {
                Huffman bt_get1 = ph.poll();
                Huffman bt_get2 = ph.poll();
                Huffman add = new Huffman(bt_get1, bt_get2,
                        bt_get1.frequency + bt_get2.frequency);
                ph.offer(add);
            }

            Huffman htree = ph.poll();
            write(htree.toBooleanArray());
            boolean[][] huffmantreeArrayList = htree.toArrayList();
            searching = in2.read(buffer);
            while (searching > 0) {
                for (int i = 0; i < searching; i++) {
                    byte characterByte = buffer[i];
                    boolean[] characterBoolArray =
                            huffmantreeArrayList[(int) characterByte + 128];
                    write(characterBoolArray);
                }
                searching = in2.read(buffer);
            }
            flush();
            in2.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("IO Exception111");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
	* Décompresser un flux d'entrée déjà compressé en un flux de sortie
	* @param nomFichierEntre du flux d'entrée à décompresser
	* @param nomFichierSortie le flux de sortie pour écrire les données décompressées
	**/
	public void Decompresser(String nomFichierEntre, String nomFichierSortie){
		
		File f1 = new File(nomFichierEntre);
		boolean isInExist = f1.exists();
		File f2 = new File(nomFichierSortie);
		boolean isOutExist = f2.exists();
		
		if (!isInExist)
			System.out.println("Le fichier d'entrée de décompression n'existe pas.");
		else if (isOutExist)
			System.out.println("Le fichier de sortie de décompression n'existe pas.");
		else {
			try {				
				FileInputStream fis = new FileInputStream(nomFichierEntre);
				FileOutputStream fos = new FileOutputStream(nomFichierSortie);
				decompress2(fis, fos);
				fis.close();
				fos.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Fichier introuvable.");
				System.out.println(ex.getMessage());
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public Huffman(OutputStream out, boolean bitdata) {
		this.out = out;
		booleandata = bitdata;
	}

    /** 
	 * Décompresser le flux d'entrée dans le flux de sortie en utilisant le codage Huffman
	 * @param in le flux d'entrée à décompresser
	 * @param out le flux de sortie pour écrire les données décompressées
	 **/
	public String decompress2(InputStream in, OutputStream out) {
		try {
			Huffman wb = new Huffman(out, false);
			
			this.out = out;
			byte[] buffer = new byte[12288];
			boolean[] bufferbool = new boolean[8];
			boolean[] treeleaf = new boolean[8];
			boolean treefound = false;
			int numLeavesThisLevel = 0;
			int numLeaves = 0;
			int nodesFoundThisLevel = 0;
			int nodesThisLevel = 1;
			boolean treemade = false;
			int numLeavesFound = 0;
			int treeleafposition = 0;
			
			ArrayList<Huffman> htreebuilder = new ArrayList();
			ArrayList<Huffman> leaves = new ArrayList();
			htreebuilder.add(new Huffman());
			Huffman htree = htreebuilder.get(0);
			Huffman currentTree = htree;
			int position = 0;
			
			int searching = in.read(buffer);
			while (searching > 0) {
				for (int i = 0; i < (searching - 2); i++) {
					bufferbool = byteToBoolean(buffer[i]);
					for (int j = 0; j < 8; j++) {
						if (!treefound) {
							if (bufferbool[j]) {
								Huffman lookingat = htreebuilder.get(position);
								leaves.add(lookingat);
								
								numLeavesThisLevel++;
								numLeaves++;
							}
							else {
								Huffman lookingat = htreebuilder.get(position);
								lookingat.left = new Huffman();
								lookingat.right = new Huffman();
								htreebuilder.add(lookingat.getLeft());
								htreebuilder.add(lookingat.getRight());
								
								nodesFoundThisLevel++;
							}
							position++;
							if ((nodesFoundThisLevel + numLeavesThisLevel) == nodesThisLevel) {
								nodesThisLevel = nodesFoundThisLevel * 2;
								nodesFoundThisLevel = 0;
								numLeavesThisLevel = 0;
								if (nodesThisLevel == 0) {
									treefound = true;
								}
							}
						}
						else if (!treemade) {
							treeleaf[treeleafposition] = bufferbool[j];
							treeleafposition++;
							if (treeleafposition == 8) {
								Huffman leaf = leaves.get(numLeavesFound);
								leaf.character = booleanToByte(treeleaf);
								leaf.leafnode = true;
								treeleafposition = 0;
								numLeavesFound++;
								
								if (numLeavesFound == numLeaves) {
									treemade = true;
								}
							}
						}
						else {
							if (!bufferbool[j]) {
								currentTree = currentTree.getLeft();
							}
							else {
								currentTree = currentTree.getRight();
							}
							if (currentTree.isLeaf()) {
								wb.write(currentTree.getCharacter());
								currentTree = htree;
							}
						}
					}
				}
				buffer[0] = buffer[searching - 2];
				buffer[1] = buffer[searching - 1];
				searching = in.read(buffer, 2, buffer.length - 2);
				if (searching != -1) {
					searching += 2;
				}
			}
			boolean[] paddingBits = byteToBoolean(buffer[0]);
			boolean[] lastBits = byteToBoolean(buffer[1]);
			int bytesToIgnore = 0;
			for (int i = 0; i < 8; i++) {
				if (lastBits[i]) {
					bytesToIgnore++;
				}
			}
			bufferbool = paddingBits;
			for (int j = 0; j < (8 - bytesToIgnore); j++) {
				if (!bufferbool[j]) {
					currentTree = currentTree.getLeft();
				}
				else {
					currentTree = currentTree.getRight();
				}
				if (currentTree.isLeaf()) {
					write(currentTree.getCharacter());
					currentTree = htree;
				}
			}
			wb.flush();
			in.close();
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
    int pos = 0;
    boolean[] buffer = new boolean[8];
    byte[] outStream = new byte[12288];
    int outLength = 0;
    OutputStream out ;
    boolean booleandata = true;

    /**
     * écrire des données binaires dans le flux de sortie
     * @param b bits données à écrire
     **/
    public void write(boolean[] b) throws IOException{
        if (booleandata) {
            for (int i = 0; i < b.length; i++) {
                buffer[pos] = b[i];
                pos++;
                if (pos > 7) {
                    byte character = booleanToByte(buffer);
                    pos -= 8;
                    outStream[outLength] = character;
                    outLength++;
                }
                if (outLength > 12200) {
                    out.write(outStream, 0, outLength);
                    outLength = 0;
                }
            }
        }
    }

    /**
     * écrire un tableau d'octets dans le flux de sortie
     * @param b tableau d'octets à écrire
     **/
    public void write(byte[] b) throws IOException{
        if (!booleandata) {
            for (int i = 0; i < b.length; i++) {
                outStream[outLength] = b[i];
                outLength++;
                if (outLength > 12200) {
                    out.write(outStream, 0, outLength);
                    outLength = 0;
                }
            }
        }
    }

    /**
     * écrire un seul octet dans le flux de sortie
     * @param b octet à écrire
     **/
    public void write(byte b) throws IOException{
        if (!booleandata) {
            outStream[outLength] = b;
            outLength++;
            if (outLength > 12200) {
                out.write(outStream, 0, outLength);
                outLength = 0;
            }
        }
    }

    /**
     * remplit la sortie finale avec des 0 et écrit dans le flux de sortie
     * puis met 1 mais le nombre de fois que 0 a été utilisé pour compléter les 8 bits précédents
     **/
    public void flush() throws IOException{
        if (booleandata) {
            boolean[] padding = new boolean[8 + (8 - pos)];
            int poscopy = pos;
            while (poscopy < 8) {
                padding[padding.length - (poscopy + 1)] = true;
                poscopy++;
            }
            write(padding);
        }
        out.write(outStream, 0, outLength);
        outLength = 0;
    }

    public static byte booleanToByte(boolean[] bool) {

        byte valeur = 0;
        //pour chaque element x dans le tableau bool
        for (boolean x : bool) {
            valeur = (byte) (valeur << 1);
            valeur = (byte) (valeur | (x ? 1 : 0));
        }
        return valeur;
    }

    /**
     * convertir un octet en onglet booléen de longueur 8
     * @param characterbyte l'octet à convertir
     * @return boolean tab auquel l'octet correspond
     **/
    public static boolean[] byteToBoolean(byte val) {
        boolean[] bool = new boolean[8];
        for (int i = 0; i < 8; i++) {
            bool[7 - i] = (val & 1) == 1 ? true : false;
            val = (byte) (val >> 1);
        }
        return bool;
    }

    public int frequency = -1;
	public byte character;
	public boolean leafnode = false;
	public Huffman left = null;
	public Huffman right = null;

    /**
	* Construit un arbre de Huffman contenant l'élément spécifié à la
	* racine, et un arbre de Huffman gauche et à droite comme enfants.
	* @param item item à la racine de cet arbre de Huffman
	* @param b1 Huffman Tree en bas à gauche
	* @param b2 huffman tree en dessous à droite
	* @param f La fréquence des caractères pour le tri
	**/
	public Huffman(byte item, Huffman b1, Huffman b2, int f) {
		frequency = f;
		character = item;
		left = b1;
		right = b2;
		leafnode = true;
	}
	
	/**
	* Construit un arbre de Huffman ne contenant aucun élément à la
	* racine, et un arbre de Huffman gauche et à droite comme enfants.
	* @param b1 Huffman Tree en bas à gauche
	* @param b2 huffman tree en dessous à droite
	* @param f La fréquence des caractères pour le tri
	**/
	public Huffman(Huffman b1, Huffman b2, int f) {
		frequency = f;
		left = b1;
		right = b2;
	}
	
	
	/**
	  * Construit un arbre de Huffman vide
	 **/
	public Huffman() {
	}
	
    /**
	* Déterminer si cet arbre de Huffman est une feuille
	* @retour vrai s'il s'agit d'une feuille. Faux sinon.
	**/
	public boolean isLeaf() {
		return leafnode;
	}
	
	public byte getCharacter() {
		return character;
	}
	
	public Huffman getLeft() {
		return left;
	}
	
	public Huffman getRight() {
		return right;
	}
	
	/**
	* Compare cet arbre de Huffman avec l'arbre de Huffman spécifié pour la commande.
	* @return un entier négatif, zéro ou un entier positif comme cet objet
	* est inférieur, égal ou supérieur à l'objet spécifié.
	**/
	public int compareTo(Huffman bt) {
			return frequency - bt.frequency;
	}
	
	
	/**
	* Convertir l'arbre de Huffman en un tableau booléen pour l'impression en fichier
	* Caractère de retour pour l'arbre de Huffman
	**/
	public boolean[] toBooleanArray(){
		boolean[] boolArray = new boolean[1024];
		byte[] characters = new byte[1024];
		ArrayList<Huffman> queue = new ArrayList();
		
		queue.add(this);
		boolArray[0] = false;
		int arraypos = 0;
		int position = 0;
		int characterpos = 0;
		while (position < queue.size()) {
			boolArray[arraypos] = queue.get(position).isLeaf();
			if (queue.get(position).isLeaf()) {
				characters[characterpos] = queue.get(position).getCharacter();
				characterpos++;
			}
			else {
				queue.add(queue.get(position).getLeft());
				queue.add(queue.get(position).getRight());
			}
			position++;
			arraypos++;
		}
		
		boolean[] ret = new boolean[arraypos + 8 * characterpos];
		for (int i = 0; i < arraypos; i++) {
			ret[i] = boolArray[i];
		}
		for (int i = 0; i < characterpos; i++) {
			byte characterByte = characters[i];
			boolean[] bits = Huffman.byteToBoolean(characterByte);
			for (int j = 0; j < 8; j++) {
				ret[arraypos + (8 * i) + j] = bits[j];
			}
		}
		
		return ret;
	}
	
	
	/**
	* Convertir l’arbre de Huffman en un booléen [] [] pour mapper facilement les octets à
	* leurs bits compressés
	* @return Une carte d'octets en bits
	**/
	public boolean[][] toArrayList() {
		boolean[][] ret = new boolean[256][];
		ArrayList<Huffman> queue = new ArrayList();
		ArrayList<boolean[]> binary = new ArrayList();
		queue.add(this);
		binary.add(new boolean[0]);
		
		int position = 0;
		while (position < queue.size()) {
			boolean isLeaf = queue.get(position).isLeaf();
			if (!isLeaf) {
				boolean[] binaryString = binary.get(position);
				boolean[] binaryLeft = new boolean[binaryString.length + 1];
				boolean[] binaryRight = new boolean[binaryString.length + 1];
				for (int i = 0; i < binaryString.length; i++) {
					binaryLeft[i] = binaryString[i];
					binaryRight[i] = binaryString[i];
				}
				binaryLeft[binaryLeft.length - 1] = false;
				binaryRight[binaryRight.length - 1] = true;
				
				queue.add(queue.get(position).getLeft());
				binary.add(binaryLeft);
				queue.add(queue.get(position).getRight());
				binary.add(binaryRight);
			}
			else {
				int toAdd = (int) queue.get(position).getCharacter() + 128;
				ret[toAdd] = binary.get(position);
			}
			position++;
		}
		
		return ret;
	}

}