import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + 
                        alphabet.substring(0,key); 
        mainKey = key;
    }
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int k = 0; k < input.length(); k++){
            char currChar = Character.toUpperCase(input.charAt(k));
            int index = alphabet.indexOf(currChar);
            if(index != -1){
               char newChar = shiftedAlphabet.charAt(index);
               sb.setCharAt(k,newChar); 
            }            
        }
        return sb.toString();
    }
    public String decrypt(String input){
         CaesarCipher cc = new CaesarCipher(26 - mainKey);
         String decrypted = cc.encrypt(input);
         return decrypted;
    }
}
