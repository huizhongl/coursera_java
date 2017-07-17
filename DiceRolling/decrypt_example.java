import edu.duke.*;
/**
 * Write a description of decrypt_example here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class decrypt_example {
    CaesarCipher cc = new CaesarCipher();
    public int decrypt(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
           dkey = 26 - (4 - maxDex); 
        }
        return dkey;        
    }
    public int[] countLetters(String encrypted){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] freqs = new int[26];
        for(int k = 0; k < encrypted.length(); k++ ){
            char ch = Character.toLowerCase(encrypted.charAt(k));            
            int index = alphabet.indexOf(ch);
            if(index != -1){
                freqs[index] += 1;
            }
        }
        return freqs;
    }
    public int maxIndex(int[] freqs){
        int max = freqs[0];
        int maxDex = 0;
        for(int k = 1; k < freqs.length; k++ ){
            if(freqs[k] > max){
                max = freqs[k];
                maxDex = k;
            }
        }
        return maxDex;
    }
    public void testDecrypt(){
        String encrypted = "Gp  bqpok feiq cbbbbbbbbb";
        int dkey = decrypt(encrypted);
        String origin = cc.encrypt(encrypted, 26 - dkey );
        System.out.println(dkey);
    }
    
    public void decryptTwoKeys(){
        FileResource resource = new FileResource();
        String encrypted = resource.asString();
        String encrypted_1 = "";
        String encrypted_2 = "";
        for(int k = 0; k < encrypted.length(); k = k + 2){
            encrypted_1 = encrypted_1 + encrypted.substring(k,k+1);
        }
        for(int k = 1; k < encrypted.length(); k = k + 2){
            encrypted_2 = encrypted_2 + encrypted.substring(k,k+1);
        }
        int dkey1 = decrypt(encrypted_1);
        int dkey2 = decrypt(encrypted_2);
        System.out.println(dkey1 + " " + dkey2);
        String origin = cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        System.out.println(origin);
    }    
}
