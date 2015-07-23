import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inu
 */
public class RandomSubstitution {

    //ArrayList containing cipherText
    ArrayList<String> cipherText;
    //ArrayList containing alphabet table
    ArrayList<String> alphabet;
    //ArrayList containing alphabet substitution table
    ArrayList<String> alphabetSub = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public RandomSubstitution(String fileName) throws IOException {
        run(fileName);
    }

    /* Read cipherText from file and decrypt it
     * Read alphabet table from Alphabet class
     * User insert alphabet substitution table
     */
    public void run(String fileName) throws IOException {
        //Read cipherText
        MessageReader reader = new MessageReader();
        reader.readFile(fileName);
        cipherText = reader.getCipherText();

        //Read alphabet table
        Alphabet al = new Alphabet();
        alphabet = al.getAlphabet();

        //Print frequency of each character in cipher text
        checkFrequency();

        while (true) {
            //User insert alphabet substitution table
            insertAlphabetSubstitution();
            alphabetSub = getAlphabetSubstitution();

            //Decrypt cipherText
            decryptText();
            alphabetSub.clear();
            System.out.println("");
            System.out.println("");
        }
    }

    //Print alphabet table
    public void printAlphabet() {
        //Print alphabet table
        for (int index = 0; index < alphabet.size(); index++) {
            System.out.print(alphabet.get(index));
        }

        System.out.println("");
    }

    /* Ask user to input substitution table     
     * User can input one by one character in one run         
     * Continue until substitution table is full and plain text is revealed
     * Each substitution must be input below the original character    
     * Unknown substitution can be replaced by "*"
     * The substitution table will be store in an array list
     * with the index of substitution character equal to original character
     */
    public void insertAlphabetSubstitution() {
        //For marking purpose, please copy and paste the below key when being asked
        //Alphabet           = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'"/n0123456789"; 
        //Key for message 04 = "M :J8BI)R*1YG05'*EL(?WP7T*$ZN**.V*****F/n**********";       
        //Key for message 10 = "DI;-A.R4B*31*)Q"*$? F7'*8*PVW**:OC**S0*/n**********";   
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input substitution table below alphabet table: ");
        printAlphabet();
        String alphabetLine = scanner.nextLine();

        //Split user input into single character
        String[] alphabetLineArray = alphabetLine.split("");

        //Store alphabet substitution table into an arraylist
        for (int index = 1; index < alphabetLineArray.length; index++) {
            if (alphabetLineArray[index].equals("/")) {
                alphabetSub.add("/n");
                index++;
            } else {
                alphabetSub.add(alphabetLineArray[index]);
            }
        }
        System.out.println("");
    }

    //Return alphabet substitution table
    public ArrayList<String> getAlphabetSubstitution() {
        return alphabetSub;
    }

    //Decrypt cipherText
    public void decryptText() {

        //For loop through the whole chipher text
        for (int index = 0; index < cipherText.size(); index++) {
            //String storing the original character of cipher character
            String character = "";

            /* Check if the cipher character is availble in substitution table
             * If yes, get the original character in alphabet table
             * using the index of cipher character in substitution table
             */
            for (int index2 = 0; index2 < alphabetSub.size(); index2++) {
                if (cipherText.get(index).equals(alphabetSub.get(index2))) {
                    character = alphabet.get(index2);
                }
            }

            //If the character is not empty, print it
            //If not, print the current cipher character
            if (!character.isEmpty()) {
                System.out.print(ANSI_RED + character + ANSI_RESET);
            } else {
                System.out.print(cipherText.get(index));
            }
        }
    }

    /* Check and print the frequency of each character in cipher text
     * Frequency can be use to guess the original character of the substitution
     * Character with the large frequency can be "space", "dot", "comma"
     * Character with the large frequency can also be vowels
     */
    public void checkFrequency() {

        //For loop to check every character in alphabet table
        for (int index = 0; index < alphabet.size(); index++) {
            //Integer storing character frequency
            int frequency = 0;

            //For loop to check the appearance of character in cipher text
            for (int index2 = 0; index2 < cipherText.size(); index2++) {
                if (cipherText.get(index2).equals(alphabet.get(index))) {
                    frequency++;
                }
            }

            //Print the frequency
            System.out.format("%1$4s:%2$2s", alphabet.get(index), frequency);
        }
        System.out.printf("\n\n");
    }
}
