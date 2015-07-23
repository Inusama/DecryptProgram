import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inu
 */
public class CeasarCipher {

    //ArrayList containing alphabet table
    ArrayList<String> alphabet;
    //ArrayList containing cipherText
    ArrayList<String> cipherText;
    
    public CeasarCipher(String fileName) throws IOException {
        run(fileName);
    }

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

        //Decrypt cipherText
        decryptText();
    }

    /* Decrypt cipherText
     * Use for loop to try every possible value of key
     * Get index of character in alphabet table, module the key
     * Print the new character
     */
    public void decryptText() {
        
        //For loop to try every key value
        for (int key = 1; key < alphabet.size(); key++) {

            //For loop to decrypt all cipher character
            for (int index = 0; index < cipherText.size(); index++) {                
                //Cipher charater to decrypt
                String character = cipherText.get(index);

                /* Get index of character in alphabet table
                 * Module the key to get original index
                 */
                int alphabetPosition = getAlphabetPosition(character);                
                int newPostion = alphabetPosition - key;

                //If new postion is negative, convert it                                  
                if (newPostion < 0) {
                    newPostion = alphabet.size() - (-1) * newPostion;
                }

                //Print original character
                System.out.print(alphabet.get(newPostion));
            }

            //Print value of current key
            System.out.println("");
            System.out.println("Key: " + key);
            System.out.println("=============");
        }
    }

    //Return the index of character in alphabet table 
    public int getAlphabetPosition(String character) {

        /* Take one cipher character  
         * Then compare to each character in alphabet table
         * If match, return the index
         */
        for (int index = 0; index < alphabet.size(); index++) {
            if (character.equals(alphabet.get(index))) {
                return index;
            }
        }
        return 0;
    }
}
