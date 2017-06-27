import java.io.*;
import java.util.zip.*;

/**
 * Read some data from a gzip file.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: ReadGZIP.java,v 1.2 2000/11/25 17:55:30 ian Exp $
 */
public class ReadGZIP {
	public static void main(String[] argv) throws IOException {
		String FILENAME = "file.txt.gz";

		// Since there be four constructors here, I wrote them all out in full.
		// In real life you would probably nest these constructor calls.
		FileInputStream fin = new FileInputStream(FILENAME);
		GZIPInputStream gzis = new GZIPInputStream(fin);
		InputStreamReader xover = new InputStreamReader(gzis);
		BufferedReader is = new BufferedReader(xover);

		String line;
		// Now read lines of text: the BufferedReader puts them in lines,
		// the InputStreamReader does Unicode conversion, and the
		// GZipInputStream "gunzip"s the data from the FileInputStream.
		while ((line = is.readLine()) != null)
			System.out.println("Read: " + line);
	}
}
