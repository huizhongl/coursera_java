import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(14, 24);
        String encrypted = cct.encrypt(input);
        System.out.println("encrypted message: " + encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("decrypted message: " + decrypted);
        String decrypted_new = breakCaesarCipher(input);
        System.out.println("decrypted_new message: " + decrypted_new);
    }
    public String breakCaesarCipher(String input){
        String encrypted_1 = "";
        String encrypted_2 = "";
        for(int k = 0; k < input.length(); k = k + 2){
            encrypted_1 = encrypted_1 + input.substring(k,k+1);
        }
        for(int k = 1; k < input.length(); k = k + 2){
            encrypted_2 = encrypted_2 + input.substring(k,k+1);
        }
        TestCaesarCipher tcc = new TestCaesarCipher();
        int key1 = tcc.breakCaesarCipher(encrypted_1);
        int key2 = tcc.breakCaesarCipher(encrypted_2);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String decrypted = cct.decrypt(input);
        return decrypted;
    }    
}
