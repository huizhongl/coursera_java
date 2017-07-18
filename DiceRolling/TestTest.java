import edu.duke.*;
/**
 * Write a description of TestTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestTest {
    public void test(){
        FileResource fr = new FileResource();
        for(String word: fr.words()){
            int length = word.length();
            String newWord = word.substring(1, length -1);
            System.out.println(newWord);
        }
    }

}
