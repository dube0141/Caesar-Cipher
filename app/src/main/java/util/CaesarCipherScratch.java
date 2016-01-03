package util;

/**
 * This class is a study on how to implement your own util.CaesarCipher.java.
 *
 * Pro-tip: developers sometimes use a "scratch" file to better understand a requirement, and how
 * to best solve it. Notice --- I don't require a complete app to see how to better understand
 * the relationship between characters --- A, d, $, 3, etc. --- and their ASCII values.
 *
 * Android Project
 *    Download this file, and add it to your "util" package.
 *
 * Usage:
 *    Run -> Run -> Select: CaesarCipherScratch
 *
 *    To run the Caesar Cipher app on the emulator:
 *    Run -> Run -> Select: app
 *
 * View the output:
 *    View -> Tool Windows -> Run
 *
 * @author Gerald.Hurdle@AlgonquinCollege.com
 */
public class CaesarCipherScratch {

    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The Java virtual machine (JVM) is configured to look for a special method: main( String[] ).
     *
     * If the JVM sees main(), the class is loaded by Java's ClassLoader, and the main( ) method is
     * called.
     *
     * @param args
     */
    public static void main(String[] args) {
        char aLetter;
        char encryptedLetter;
        int  encryptedLetterASCII;
        int  rotation;

        // Display the ASCII values for the capital letters
        for( int index = 0; index < CAPITAL_LETTERS.length(); index++ ) {
            aLetter = CAPITAL_LETTERS.charAt( index );
            System.out.println( "Letter: " + aLetter + "\tASCII (decimal): " + (int) aLetter );
        }

        System.out.println( "============================================================" );

        // Test-case: 'A' + 1 equals 'B'
        aLetter = 'A';
        rotation = 1;				// the rotation value for 'B'

        encryptedLetterASCII = (int) aLetter + rotation;
        encryptedLetter = (char) encryptedLetterASCII;

        System.out.println( "aLetter: " + aLetter + "\t==> Encrypted Letter: " + encryptedLetter );

        // Test-case: 'Z' + 1 equals 'A'
        // Notice: rotating the last letter of the upper-case letters (Z) by one position is: upper-
        //         case A.
        aLetter = 'Z';
        rotation = 1;				// the rotation value for 'B'

        // Notice: % is Java's modulus operator
        // Reference: http://mathbits.com/MathBits/Java/DataBasics/Mathoperators.htm
        encryptedLetterASCII = ( ((int) aLetter - 'A' + rotation) % 26) + (int) 'A';
        encryptedLetter = (char) encryptedLetterASCII;

        System.out.println( "aLetter: " + aLetter + "\t==> Encrypted Letter: " + encryptedLetter );

        // Test-case: 'Z' + 2 equals 'B'
        aLetter = 'Z';
        rotation = 2;				// the rotation value for 'C'

        encryptedLetterASCII = ( ((int) aLetter - 'A' + rotation) % 26) + (int) 'A';
        encryptedLetter = (char) encryptedLetterASCII;

        System.out.println( "aLetter: " + aLetter + "\t==> Encrypted Letter: " + encryptedLetter );
    }
}