import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    private HashMap<String, HashSet<String>> languages;
    public VigenereBreaker(){
        languages = new HashMap<String, HashSet<String>>();
    }
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb1 = new StringBuilder(message);
        StringBuilder sb2 = new StringBuilder();
        for(int k = whichSlice; k< message.length(); k = k + totalSlices){
            char ch = sb1.charAt(k);
            sb2.append(ch);
        }        
        return sb2.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cr = new CaesarCracker(mostCommon);
        for(int k=0; k < klength; k++ ){
            String sliceInput = sliceString(encrypted,k,klength);
            //System.out.println(sliceInput);
            int dKey = cr.getKey(sliceInput);
            key[k] = dKey;
        }
        return key;
    }

    public void breakVigenere () {
        languages.clear();
        
        /*
        FileResource fr1 = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(fr1);
        String decrypted = breakForLanguage(encrypted, dictionary, 'e');
        
        int klength = 4;
        int[] key = tryKeyLength(encrypted, klength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
        */
        FileResource fr1 = new FileResource();
        String encrypted = fr1.asString();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String lagName = f.getName();
            HashSet<String> set = readDictionary(fr);
            languages.put(lagName, set);
        }
        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println(decrypted);
    }
    
    public void testSliceString(){
        String message = "abcdefghijklm";
        String result = sliceString("abcdefghijklm", 3, 5);
        System.out.println(result);
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        //System.out.println(input);
        int[] key = tryKeyLength(input,4,'e');
        for(int k = 0; k < key.length; k++){
            System.out.println(key[k]);
        }
        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<String>();
        for(String word: fr.lines()){
            word = word.toLowerCase();
            set.add(word);
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        String[] input = (message.toLowerCase()).split("\\W+");
        for(int k=0; k < input.length; k++ ){
            String word = input[k];
            if(dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String result = "";
        int length = 0;
        char mostCommon = mostCommonCharln(dictionary);
        for(int klength = 1; klength <= 100 ; klength++){
            int[] key = tryKeyLength(encrypted, klength, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if(count > max){
                max = count;
                length = klength;
                result = decrypted;
            }
        }
        System.out.println("klength is: " + length);
        System.out.println("max is: " + max);
        return result;
    }
    
    public void findNumValidWord(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource fr1 = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(fr1);
        int klength = 38;
        int[] key = tryKeyLength(encrypted, klength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        int count = countWords(decrypted, dictionary);
        System.out.println(count);
    }
    
    public char mostCommonCharln(HashSet<String> dictionary){
        int[] letter = new int[26];
        int max = 0;
        int num_max = 0;
        StringBuilder alphabet = new StringBuilder("abcdefghigklmnopqrstuvwxyz");
        
        for(String word: dictionary){
            word = word.toLowerCase();
            for(int k=0; k < word.length(); k++ ){
                char ch = word.charAt(k);
                int idx = alphabet.toString().indexOf(ch);
                if(idx != -1){
                    letter[idx] = letter[idx] + 1;
                }
            }    
        }
        
        for(int k = 0; k < letter.length; k++){
            if(letter[k] > max){
                max = letter[k];
                num_max = k;
            }
        }
        char commonChar = alphabet.charAt(num_max);
        return commonChar;
        
    }
        
    public void testMostCommonCharln(){
        FileResource fr = new FileResource();
        HashSet<String> set = readDictionary(fr);
        char mostCommon = mostCommonCharln(set);
        System.out.println(mostCommon);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int max_num = 0;
        String decrypted = "";
        String language = "";
        
        
        for(String s: languages.keySet()){
            HashSet<String> dictionary = languages.get(s);
            String message = breakForLanguage(encrypted, dictionary);
            System.out.println("language of " + s + " is done!");
            int count = countWords(message, dictionary);
            if(count > max_num){
                max_num = count;
                decrypted = message;
                language = s;
            }
        }
        System.out.println("the language of decrypted message is:" + language);
        return decrypted;
    }
}

