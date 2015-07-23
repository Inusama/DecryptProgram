import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inu
 */
public class VernamCipher {

    //ArrayList containing alphabet table
    ArrayList<String> alphabet;
    //ArrayList containing cipherText
    ArrayList<String> cipherText;

    //ArrayList containing the "key"
    ArrayList<String> key = new ArrayList<>();
    //2D ArrayList containing list of key
    List<List<String>> listOfKey = new ArrayList<>();

    public VernamCipher(String fileName) throws IOException {
        run(fileName);
    }

    //Read all keys from file
    //Read alphabet table from Alphabet class
    //Read cipherText from file and decrypt it
    public void run(String fileName) throws IOException {
        //Read cipherText
        MessageReader reader = new MessageReader();
        reader.readFile(fileName);
        cipherText = reader.getCipherText();

        //Read alphabet table
        Alphabet al = new Alphabet();
        alphabet = al.getAlphabet();

        //Read all keys from file
        readKey(fileName);

        //Decrypt cipherText
        decryptText();
    }

    //Read all keys from file
    public void readKey(String fileName) throws IOException {
        //Get the number of ciphertext file to make the name of key file
        String keyFileName = "key" + fileName.charAt(3) + ".enc";
        //Read file
        BufferedReader reader = new BufferedReader(new java.io.FileReader(keyFileName));
        String line;

        //Store key into 2D arraylist       
        int indexOfKey = 0;
        while ((line = reader.readLine()) != null) {

            listOfKey.add(new ArrayList<String>());

            for (int index = 0; index < line.length(); index++) {

                if (line.charAt(index) == '/') {
                    listOfKey.get(indexOfKey).add("/n");
                    index++;
                } else {
                    listOfKey.get(indexOfKey).add("" + line.charAt(index));
                }
            }
            indexOfKey++;
        }
    }

    public void decryptText() {
        //For-loop to loop through all keys in 2D arraylist
        for (int indexOfKey = 0; indexOfKey < listOfKey.size(); indexOfKey++) {

            //Limit is the max index of key in for loop, prevent index out of bound
            int limit = listOfKey.get(indexOfKey).size() - cipherText.size();

            //For-loop to loop through all position of a key (within limit)
            for (int increment = 0; increment <= limit; increment++) {

                //Store the current key
                key = new ArrayList<>();

                //For loop to decrypt all cipher character
                for (int index = 0; index < cipherText.size(); index++) {
                    //Cipher charater to decrypt
                    String cipherCharacter = cipherText.get(index);
                    //Key charater used to decrypt
                    String keyCharacter = listOfKey.get(indexOfKey).get(index + increment);

                    // Get index of character in alphabet table
                    int cipherCharPosition = getAlphabetPosition(cipherCharacter);
                    int keyCharPosition = getAlphabetPosition(keyCharacter);

                    //Store the index of decrypted character
                    int newPostion;

                    //If new postion is negative, convert it  
                    if (cipherCharPosition - keyCharPosition < 0) {
                        newPostion = cipherCharPosition - keyCharPosition + alphabet.size();
                    } else {
                        newPostion = cipherCharPosition - keyCharPosition;
                    }

                    //Add key character to current key
                    key.add(keyCharacter);
                    
                    //Print decrypted character
                    System.out.print(alphabet.get(newPostion));
                }

                //Print the key and index of that key in list
                //System.out.println("");
                System.out.printf("\n\n");
                //System.out.println("Index of key in list: " + indexOfKey);
                System.out.print("Key: ");
                printKey();
                System.out.println("==================================");
            }
        }
    }

    //Print the key of current message
    public void printKey() {
        for (int index = 0; index < key.size(); index++) {
            System.out.print(key.get(index));
        }
        System.out.println("");
    }

    //Return the index of character in alphabet table 
    public int getAlphabetPosition(String character) {

        /* Take one cipher character  
         * Then compare to each character in alphabet table
         * If match, return the index
         */
        for (int index = 0; index < alphabet.size(); index++) {
            if (character.equalsIgnoreCase(alphabet.get(index))) {
                return index;
            }
        }
        return 0;
    }
}
