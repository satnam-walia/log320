package laboratoire2;

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


public class Converter {
	
	/**
	* convertir un tableau booléen de longueur 8 en octet
	* @param tab le tableau booléen à convertir
	* @retour l'octet créé par le tableau
	**/
	public static byte booleanToByte(boolean[] tab) {
		byte character = 0;
		if (tab.length == 8) {
			if (tab[0]) {
				character = (byte) (character | 0x80);
			}
			if (tab[1]) {
				character = (byte) (character | 0x40);
			}
			if (tab[2]) {
				character = (byte) (character | 0x20);
			}
			if (tab[3]) {
				character = (byte) (character | 0x10);
			}
			if (tab[4]) {
				character = (byte) (character | 0x08);
			}
			if (tab[5]) {
				character = (byte) (character | 0x04);
			}
			if (tab[6]) {
				character = (byte) (character | 0x02);
			}
			if (tab[7]) {
				character = (byte) (character | 0x01);
			}
		}

		return character;
	}

	/**
	* convertir un octet en onglet booléen de longueur 8
	* @param characterbyte l'octet à convertir
	* @return boolean tab auquel l'octet correspond
	**/
	public static boolean[] byteToBoolean(byte characterbyte) {
		boolean[] tab = new boolean[8];
		tab[0] = ((characterbyte & 0x80) != 0);
		tab[1] = ((characterbyte & 0x40) != 0);
		tab[2] = ((characterbyte & 0x20) != 0);
		tab[3] = ((characterbyte & 0x10) != 0);
		tab[4] = ((characterbyte & 0x08) != 0);
		tab[5] = ((characterbyte & 0x04) != 0);
		tab[6] = ((characterbyte & 0x02) != 0);
		tab[7] = ((characterbyte & 0x01) != 0);
		return tab;
	}
}
