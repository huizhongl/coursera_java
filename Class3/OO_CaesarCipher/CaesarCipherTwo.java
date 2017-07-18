import edu.duke.*;
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int dkey1;
    private int dkey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + 
                           alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + 
                           alphabet.substring(0,key2);
        dkey1 = key1;
        dkey2 = key2;
    }
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int k = 0; k < input.length(); k = k + 2 ){
            char currChar = Character.toUpperCase(sb.charAt(k));
            int index = alphabet.indexOf(currChar);
            if(index != -1){
                char newChar = shiftedAlphabet1.charAt(index);
                sb.setCharAt(k, newChar);
            }
        }
        for(int k = 1; k < input.length(); k = k + 2 ){
            char currChar = Character.toUpperCase(sb.charAt(k));
            int index = alphabet.indexOf(currChar);
            if(index != -1){
                char newChar = shiftedAlphabet2.charAt(index);
                sb.setCharAt(k, newChar);
            }
        }
        return sb.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - dkey1, 26 - dkey2 );
        String decrypted = cct.encrypt(input);
        return decrypted;
    }
}
