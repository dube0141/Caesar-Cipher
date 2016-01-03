package util;

import android.util.Log;

import static com.algonquincollege.dube0141.caesarcipher.Constants.*;

/**
 *  Lab 7 Intents - Caesar Cipher
 *  @author Gabriel Dubé (dube0141)
 */

public class CaesarCipher {
    private static final int MAX_ROTATIONS;

    static {
        MAX_ROTATIONS = ROTATIONS.length();
    }

    private CaesarCipher() {
        //NOP
    }

    static String decrypt(String encryptedMessage) {
        return CaesarCipher.decrypt(encryptedMessage, ROT13);
    }

    public static String decrypt(String encryptedMessage, int rotation) {
        String decryptedLetter = "";
        int decryptedLetterASCII;

        if (encryptedMessage == null) return "";

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char aLetter = encryptedMessage.charAt(i);

            if (Character.isUpperCase(aLetter)) {
                decryptedLetterASCII = aLetter - (rotation % 26);
                if (decryptedLetterASCII < 'A') {
                    decryptedLetterASCII = decryptedLetterASCII + 26;
                }
            } else if (Character.isLowerCase(aLetter)) {
                decryptedLetterASCII = aLetter - (rotation % 26);
                if (decryptedLetterASCII < 'a') {
                    decryptedLetterASCII = decryptedLetterASCII + 26;
                }
            } else {
                decryptedLetterASCII = aLetter;
            }
            decryptedLetter += (char) decryptedLetterASCII;
        }

        return decryptedLetter;
    }

    static String encrypt(String plainMessage) {
        return CaesarCipher.encrypt(plainMessage, ROT13);
    }

    public static String encrypt(String plainMessage, int rotation) {
        String encryptedLetter = "";
        int encryptedLetterASCII;

        if (plainMessage == null) return "";

        for (int i = 0; i < plainMessage.length(); i++) {
            char aLetter = plainMessage.charAt(i);
            if (Character.isUpperCase(aLetter)) {
                encryptedLetterASCII = (((int) aLetter - 'A' + rotation) % 26) + (int) 'A';
            } else if (Character.isLowerCase(aLetter)) {
                encryptedLetterASCII = (((int) aLetter - 'a' + rotation) % 26) + (int) 'a';
            } else {
                encryptedLetterASCII = aLetter;
            }
            encryptedLetter += (char) encryptedLetterASCII;
        }

        return encryptedLetter;
    }
}
