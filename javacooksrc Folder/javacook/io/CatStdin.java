import java.io.*;

/**
 * Read and print, using BufferedReader from System.in, onto System.out
 */
public class CatStdin {

    public static void main(String[] av) {
        CatFile c = new CatFile();
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                System.out.println(inputLine);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
