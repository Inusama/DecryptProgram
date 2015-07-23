import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Inu
 */
public class MessageReader {

    ArrayList<String> cipherText = new ArrayList<>();
    
    public void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        
        String line;

        while ((line = reader.readLine()) != null) {
            for (int index = 0; index < line.length(); index++) {

                if ((line.charAt(index) == '<')
                        || (line.charAt(index) == '>')) {
                    //Do nothing
                } else {
                    cipherText.add("" + line.charAt(index));
                }

                if (index == line.length() - 1) {
                    cipherText.add("/n");
                }
            }
        }        
        cipherText.remove(cipherText.size() - 1);
    }
    
    public void readWordFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        
        String line;
        String[] parts = null;

        while ((line = reader.readLine()) != null) {
            parts = line.split(" ");
        }     
        
        for (int index = 0 ; index < parts.length; index++) {
            cipherText.add(parts[index]);
        }
    }

    public ArrayList<String> getCipherText() {
        return cipherText;
    }
}
