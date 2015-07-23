import java.io.IOException;
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

//Use to run the whole program
public class MainProgram {

    public static void main(String[] args) throws IOException {        
        CeasarCipher ceasar;
        VernamCipher vernam;
        ColumnarTransposition columnar;
        RandomSubstitution substitution;

        //Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a message to decrypt");
        System.out.println("Enter 1 for message 01");
        System.out.println("Enter 2 for message 02");
        System.out.println("...");
        System.out.println("Enter 12 for message 12\n");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        System.out.println("");

        //Swith case depend on user input
        switch (choice) {
            case 1:
                columnar = new ColumnarTransposition("msg1.enc");
                break;
            case 2:
                ceasar = new CeasarCipher("msg2.enc");
                break;
            case 3:
                ceasar = new CeasarCipher("msg3.enc");
                break;
            case 4:
                System.out.println("For marking purpose, please copy and "
                        + "paste the following line when being asked");
                System.out.println("M :J8BI)R*1YG05'*EL(?WP7T*$ZN**.V*****F/n**********" + "\n");

                substitution = new RandomSubstitution("msg4.enc");
                break;
            case 5:
                vernam = new VernamCipher("msg5.enc");
                break;
            case 6:
                vernam = new VernamCipher("msg6.enc");
                break;
            case 7:
                columnar = new ColumnarTransposition("msg7.enc");
                break;
            case 8:
                ceasar = new CeasarCipher("msg8.enc");
                break;
            case 9:
                System.out.println("This message was solved by hand");
                System.out.println("Plaintext: COMMAND");
                break;
            case 10:
                System.out.println("For marking purpose, please copy and "
                        + "paste the following line when being asked");
                System.out.println("DI;-A.R4B*31*)Q\"*$? F7'*8*PVW**:OC**S0*/***********" + "\n");

                substitution = new RandomSubstitution("msg10.enc");
                break;
            case 11:               
                columnar = new ColumnarTransposition("msg11.enc");
                columnar.arrangeText();
                break;
            case 12:
                columnar = new ColumnarTransposition("msg12.enc");
                columnar.arrangeText();
                break;
        }
    }
}
