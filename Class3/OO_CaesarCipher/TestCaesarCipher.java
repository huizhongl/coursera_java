import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {    
    public void test(){
        CaesarCipher cc = new CaesarCipher(15);
        // String encrypted = cc.encrypt("WAITING FOR ME! WINNIPEG!");
        String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    public void simpleTest(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);  
        int key = breakCaesarCipher(input);
        CaesarCipher ccc = new CaesarCipher(key);
        String decrypted_new = ccc.decrypt(input);
        System.out.println(decrypted_new);
        
    }
    public int breakCaesarCipher(String input){
        Decrypt dc = new Decrypt();
        int[] freqs = dc.countLetters(input);
        int maxDex = dc.maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
           dkey = 26 - (4 - maxDex); 
        }
        return dkey; 
    }
}
