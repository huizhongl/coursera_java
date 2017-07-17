import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts){
        String[] word_store = new String[counts.length];
        for(String word: resource.words()){
            int length = word.length();
            if(!Character.isLetter(word.charAt(0))){
                word = word.substring(1);
                length = length - 1;                
            }
            if(!Character.isLetter(word.charAt(length - 1))){
                length = length - 1;
                word = word.substring(0,length);
            }
            counts[length] += 1;
            word_store[length] = word_store[length] + " " + word;
        } 
        for(int k = 0; k < counts.length; k++){
            if(counts[k] != 0){
                System.out.println(counts[k] + " words of length " + k + ":" + word_store[k] );
                
            }            
        }
        return counts;
    }
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
       
        int index = indexOfMax(countWordLengths(resource, counts));
        System.out.println("index position of the largest element in valuse is: " + index);
    }
    public int indexOfMax(int[] values){
        int max_index = 0;
        int max = values[0];
        for(int k = 1; k < values.length; k++ ){
            if(values[k] > max){
                max_index = k;
                max = values[k];
            }
        }
        return max_index;
    }
}
