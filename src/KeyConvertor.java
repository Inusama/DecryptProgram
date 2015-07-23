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

/*This class will replace all characters not part of the assignment alphabet 
 *by the character '9' and use it as the key to decrypt message 06*/
public class KeyConvertor {

    public static void main(String[] args) {

        //The original plaintext of message 04
        String line = "In modern terminology, a Vernam cipher is a symmetrical stream cipher "
                + "in which the plaintext is combined with a random or pseudorandom stream of "
                + "data (the \"keystream\") of the same length, to generate the ciphertext, using "
                + "the Boolean \"exclusive or\" (XOR) function.";

        //Arraylist containing alphabet table
        ArrayList<String> alphabet;
        //Get alphabet table from alphabet class
        Alphabet al = new Alphabet();
        alphabet = al.getAlphabet();

        //For-loop to loop through the whole message
        for (int index = 0; index < line.length(); index++) {

            String character = "";

            //Check every single character if it is available in the alphabet table
            for (int index2 = 0; index2 < alphabet.size(); index2++) {

                if (("" + line.charAt(index)).equals(alphabet.get(index2))) {
                    character = "" + line.charAt(index);
                }
            }

            //If the character is available in the alphabet table, print it
            //If not, print "9"
            if (!character.isEmpty()) {
                System.out.print(character);
            } else {
                System.out.print("9");
            }
        }
    }
}
