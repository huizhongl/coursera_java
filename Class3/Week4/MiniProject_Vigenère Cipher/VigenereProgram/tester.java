import edu.duke.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(1);
        FileResource fr = new FileResource();
        String input = fr.asString();
        //System.out.println("input: " + input);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public void testCaesarCracker(){
        //CaesarCracker ck1 = new CaesarCracker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //String decrypted = ck1.decrypt(encrypted);
        //System.out.println(decrypted);
        
        CaesarCracker ck2 = new CaesarCracker('a');
        String decrypted2 = ck2.decrypt(encrypted);
        System.out.println(decrypted2);
        
    }
    
    public void testVigenereCipher(){
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypted = vc.encrypt(input);
        System.out.println(encrypted);
    }
    
    
}
