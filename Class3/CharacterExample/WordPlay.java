import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String alphabet_l = "aeiou";
        String alphabet_u = "AEIOU";
        int index = -1;
        if(Character.isLowerCase(ch)){
            index = alphabet_l.indexOf(ch);            
        }
        if(Character.isUpperCase(ch)){
            index = alphabet_u.indexOf(ch);            
        }
        if (index == -1){
            return false;
        } else {
            return true;
        }
    }
    public void testIsVowel(){
        char ch1 = 'F';
        System.out.println(isVowel(ch1));
        char ch2 = 'f';
        System.out.println(isVowel(ch2));
        char ch3 = 'I';
        System.out.println(isVowel(ch3));
        char ch4 = 'u';
        System.out.println(isVowel(ch4));
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder replace = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++ ){
            char currChar = replace.charAt(i);
            if(isVowel(currChar)){
                replace.setCharAt(i,ch);
            }
        }
        return replace.toString();
    }
    public void testReplaceVowels(){
        String replaced = replaceVowels("HellO world!", '*');
        System.out.println(replaced);
    }
    public String emphasize(String phrase, char ch){
        StringBuilder emphasized = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++ ){
            char currChar = emphasized.charAt(i);
            if(Character.toLowerCase(currChar) == Character.toLowerCase(ch)){
                if(i%2 == 0){
                    emphasized.setCharAt(i,'*');
                } else {
                    emphasized.setCharAt(i,'+');
                }
            }
        }
        return emphasized.toString();
    }
    public void testEmphasize(){
        String phrase1 = emphasize("dna ctgaaactga", 'a');
        System.out.println(phrase1);
        String phrase2 = emphasize("Mary Bella Abracadabra",'a');
        System.out.println(phrase2);
    }
}
