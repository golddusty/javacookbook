import java.io.*;

/**
 * ExecDemo shows how to execute an external program and read its output.
 */
public class ExecDemoSort {
	public static void main(String[] av) throws IOException { 

		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();

		// A process object tracks one external running process
		Process p;
		
		// file contains unsorted data
		p = r.exec("sort sortdemo.txt");

		// getInputStream gives an Input stream connected to 
		// the process p's standard output (and vice versa). We use
		// that to construct a BufferedReader so we can readLine() it.
		BufferedReader is = new BufferedReader(
			new InputStreamReader(p.getInputStream()));

		System.out.println("Here is your sorted data:");

		String aLine;
		while ((aLine = is.readLine()) != null)
			System.out.println(aLine);
		
		System.out.println("That is all, folks!");

		return;
	}
}
