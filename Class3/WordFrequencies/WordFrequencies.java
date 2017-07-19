import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String s: fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }           
        }
        
    }
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size() );
        System.out.println("The word that occurs most often and its count are: " + myWords.get(findIndexOfMax()) + "   " + myFreqs.get(findIndexOfMax()));
        for(int k=0; k < myWords.size(); k++){
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
    }
    public int findIndexOfMax(){
        int maxIndex = 0;
        int max = 0;
        for(int k=0; k < myFreqs.size(); k++ ){
            int value = myFreqs.get(k);
            if(value > max){
                max = value;
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
