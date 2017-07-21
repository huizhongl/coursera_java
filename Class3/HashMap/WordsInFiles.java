import java.util.*;
import java.io.*;
import edu.duke.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    /*
    private void addWordsFromFile(DirectoryResource dr){
        for (File f: dr.selectedFiles()){
            String fName = f.getName();
            FileResource fr = new FileResource(f);
            for(String w: fr.words()){
                if(!map.containsKey(w)){
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(fName);
                    map.put(w,list);
                } else {
                    ArrayList<String> list = map.get(w);
                    if(!list.contains(fName)){
                        list.add(fName);
                        map.put(w,list);
                    }
                }
            }
        }
        for(String s: map.keySet()){
            System.out.print(s + ": " );
            ArrayList<String> list = map.get(s);
            for(int k=0; k<list.size(); k++){
                System.out.print(list.get(k) + ", ");
            }
            System.out.println();
        }
    }
    */
    private void addWordsFromFile(File f){
        String fName = f.getName();
        FileResource fr = new FileResource(f);
        for(String w: fr.words()){
            if(!map.containsKey(w)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(fName);
                map.put(w,list);
            } else {
                ArrayList<String> list = map.get(w);
                if(!list.contains(fName)){
                    list.add(fName);
                    map.put(w,list);
                }
            }
        }
    }
    
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int max = 0;
        for(String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            if(list.size() > max){
                max = list.size();
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int num){
        ArrayList<String> winf = new ArrayList<String>();
        for(String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            if(list.size() == num){
                winf.add(s);
            }
        }
        return winf;
    }
    
    private void printFilesIn(String word){
        for(String s: map.keySet()){
            if(word.equals(s)){
                ArrayList<String> list = map.get(s);
                for(int k=0; k < list.size(); k++){
                    System.out.println(list.get(k));
                }
            }
        }
    } 
    
    public void tester(){
        buildWordFileMap();
        System.out.println("maxNumber would return " + maxNumber());
        ArrayList<String> list1 = wordsInNumFiles(7);
        System.out.println(list1.size() + " words that appear in 7 files:");
        for(int k=0; k < list1.size(); k++){
            System.out.print(list1.get(k) + ", ");
        }
        System.out.println();
        ArrayList<String> list2 = wordsInNumFiles(4);
        System.out.println(list2.size() + " words that appear in 4 files:");
        for(int k=0; k < list2.size(); k++){
            System.out.print(list2.get(k) + ", ");
        }
        System.out.println();
        printFilesIn("tree");
        
        /*
        System.out.println();
        System.out.println("The HashMap is: ");
        for(String s: map.keySet()){
            System.out.print(s + ": " );
            ArrayList<String> list = map.get(s);
            for(int k=0; k<list.size(); k++){
                System.out.print(list.get(k) + ", ");
            }
            System.out.println();
        }
        */
    }
}
