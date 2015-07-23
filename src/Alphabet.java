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
public final class Alphabet {

    ArrayList<String> alphabet = new ArrayList<>();
    
    public Alphabet() {
        insertAlphabet();
    }

    public void insertAlphabet() {
        String alphabetLine = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"/n0123456789";                
        String[] alphabetLineArray = alphabetLine.split("");

        for (int index = 1; index < alphabetLineArray.length; index++) {
            if (alphabetLineArray[index].equals("/")) {
                alphabet.add("/n");
                index++;
            } else {
                alphabet.add(alphabetLineArray[index]);
            }
        }
    }
      
    public ArrayList<String> getAlphabet() {
        return alphabet;
    }
}
