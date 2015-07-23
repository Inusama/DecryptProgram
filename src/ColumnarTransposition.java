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
public class ColumnarTransposition {

    //ArrayList containing cipherText
    ArrayList<String> cipherText;

    public ColumnarTransposition(String fileName) throws IOException {
        run(fileName);
    }

    //Read cipherText from file and decrypt it
    public void run(String fileName) throws IOException {
        //Read cipherText
        MessageReader reader = new MessageReader();
        reader.readFile(fileName);
        cipherText = reader.getCipherText();

        //Decrypt cipherText
        decryptText();
    }

    /* Decrypt cipherText
     * Use nested for-loop to stimulate a grid 
     * Put each cipher character into the grid in an order
     * Print the plain text from the grid     
     */
    public void decryptText() {
        //Size of cipherText
        double length = cipherText.size();

        //For loop to try every key value
        for (double key = 2; key < 40; key++) {
            //Number of column in the grid, equal to the key
            int column = (int) key;
            //Number of row in the grid, equal to size of cipher text divide by key
            int row = (int) Math.round(length / key);

            /* Stimulate a grid using nested for-loop
             * Put character into each row until it full
             * Then jump to next row
             */
            try {
                /* The difference between 2 indexes of nearby character is i + j * row
                 * i is the current row when input character
                 * j is the current column when input character
                 * row is number of row in the grid 
                 */
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        System.out.print(cipherText.get(i + j * row));
                    }
                }
                printKey((int) key);

            } catch (Exception ex) {
                printKey((int) key);
            }
        }
    }

    //This method is used to support message 11+12
    //Print the plaintext that is combined by message 11 and 12
    public void arrangeText() throws IOException {
        //Arraylist containing plaintext of message 11
        ArrayList<String> keyText;
        //Arraylist containing plaintext of message 12
        ArrayList<String> keyText_2;

        //Read plaintext of message 11
        //Store in arraylist
        MessageReader reader = new MessageReader();
        reader.readWordFromFile("key11.enc");
        keyText = reader.getCipherText();

        //Read plaintext of message 12
        //Store in arraylist
        reader = new MessageReader();
        reader.readWordFromFile("key12.enc");
        keyText_2 = reader.getCipherText();

        //Print combined plaintext
        System.out.println("Plaintext 11 + 12:");
        for (int i = 0; i < keyText.size(); i++) {
            System.out.print(keyText.get(i) + " ");
            System.out.print(keyText_2.get(i) + " ");
        }
    }

    //Print the value of current key
    public void printKey(int key) {
        System.out.println("");
        System.out.println("Key: " + key);
        System.out.println("========");
    }
}
