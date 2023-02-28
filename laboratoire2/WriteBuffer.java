package laboratoire2;

import java.io.OutputStream;
import java.io.IOException;

class WriteBuffer {
	int pos = 0; 
	boolean[] buffer = new boolean[8];
	byte[] outStream = new byte[12288];
	int outLength = 0; 
	OutputStream out;
	boolean booleandata = true; 
	
	public WriteBuffer(OutputStream out, boolean bitdata) {
		this.out = out;
		booleandata = bitdata;
	}
	
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
					byte character = Converter.booleanToByte(buffer);
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
}