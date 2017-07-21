import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequenciesMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequenciesMap {
    public void countWords(){
        int count = 0;
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        FileResource fr = new FileResource();
        for(String word: fr.words()){
            count += 1;
            word = word.toLowerCase();
            if(!map.containsKey(word)){
                map.put(word,1);
            } else {
                map.put(word, map.get(word)+1);
            }            
        }
        System.out.println(count);
        for(String s: map.keySet()){  
            System.out.println(map.get(s) + "\t" + s);
        }
    }

}
