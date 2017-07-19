import edu.duke.*;
import  java.util.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myCharacter;
    private ArrayList<Integer> myCounts;
    private String person;
    public CharactersInPlay(){
        myCharacter = new ArrayList<String>();
        myCounts = new ArrayList<Integer>();
        person = "";
    }
    public void update(String person){
        int index = myCharacter.indexOf(person);
        if(index == -1){
            myCharacter.add(person);
            myCounts.add(1);
        } else {
            int value = myCounts.get(index);
            myCounts.set(index, value+1);
        }
    }
    public void findAllCharacters(){
        myCharacter.clear();
        myCounts.clear();
        FileResource fr = new FileResource();
        for(String line: fr.lines()){
            person = "";
            int index = line.indexOf(".");            
            if(index != -1 ){
                person = line.substring(0,index);
                update(person);
            }
        }
        
    }
    public void tester(){
        findAllCharacters();
        for(int k=0; k < myCounts.size(); k++ ){
            if(myCounts.get(k) > 30){
                System.out.println(myCharacter.get(k) + "\t" + myCounts.get(k));
            }
        }
        System.out.println("those characters that have exactly number speaking parts, ");
        System.out.println("where number is greater than or equal to num1 and less than or equal to num2: ");
        charactersWithNumParts(10,15);
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int k=0; k < myCounts.size(); k++){
            if(myCounts.get(k) >= num1 && myCounts.get(k) <= num2 ){
                System.out.println(myCharacter.get(k) + "\t" + myCounts.get(k));
            }
        }        
    }
}
