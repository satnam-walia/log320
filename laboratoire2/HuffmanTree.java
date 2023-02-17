package laboratoire2;

import laboratoire2.Converter;

import java.util.ArrayList;

/**
 * LE CODE SOURCE DE L'ALGORITHME HUFFMAN A ETE INSPIRE DE SITES SUIVANT :
 * http://courses.ics.hawaii.edu/ics211f18/morea/120.trees/files/Huffman.java
 * https://courses.cs.washington.edu/courses/cse143/12wi/homework/8/
 * 
 * 
 * LE CODE SOURCE DE L'ALGORITHME LZW A ETE INSPIRE DE SITES SUIVANT :
 * https://codereview.stackexchange.com/questions/122080/simplifying-lzw-compression-decompression
 * https://rosettacode.org/wiki/LZW_compression
 * 
 * 
 * @author NAMOUCHI Cherif BALTI Sami
 */


public class HuffmanTree implements Comparable<HuffmanTree>
{
	public int frequency = -1;
	public byte character;
	public boolean leafnode = false;
	public HuffmanTree left = null;
	public HuffmanTree right = null;
	
	/**
	* Construit un arbre de Huffman contenant l'élément spécifié à la
	* racine, et un arbre de Huffman gauche et à droite comme enfants.
	* @param item item à la racine de cet arbre de Huffman
	* @param b1 Huffman Tree en bas à gauche
	* @param b2 huffman tree en dessous à droite
	* @param f La fréquence des caractères pour le tri
	**/
	public HuffmanTree(byte item, HuffmanTree b1, HuffmanTree b2, int f) {
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
	public HuffmanTree(HuffmanTree b1, HuffmanTree b2, int f) {
		frequency = f;
		left = b1;
		right = b2;
	}
	
	
	/**
	  * Construit un arbre de Huffman vide
	 **/
	public HuffmanTree() {
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
	
	public HuffmanTree getLeft() {
		return left;
	}
	
	public HuffmanTree getRight() {
		return right;
	}
	
	/**
	* Compare cet arbre de Huffman avec l'arbre de Huffman spécifié pour la commande.
	* @return un entier négatif, zéro ou un entier positif comme cet objet
	* est inférieur, égal ou supérieur à l'objet spécifié.
	**/
	public int compareTo(HuffmanTree bt) {
			return frequency - bt.frequency;
	}
	
	
	/**
	* Convertir l'arbre de Huffman en un tableau booléen pour l'impression en fichier
	* Caractère de retour pour l'arbre de Huffman
	**/
	public boolean[] toBooleanArray(){
		boolean[] boolArray = new boolean[1024];
		byte[] characters = new byte[1024];
		ArrayList<HuffmanTree> queue = new ArrayList();
		
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
			boolean[] bits = Converter.byteToBoolean(characterByte);
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
		ArrayList<HuffmanTree> queue = new ArrayList();
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