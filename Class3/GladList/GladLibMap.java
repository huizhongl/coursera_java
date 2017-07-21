
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    
    
    
    private ArrayList<String> count;
    private ArrayList<String> count_category;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        
        
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        count = new ArrayList<String>();
        count_category = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] category = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for(int k=0; k < category.length; k++){
            ArrayList<String> list = new ArrayList<String>();
            list = readIt(source + "/" + category[k] + ".txt");
            myMap.put(category[k], list);
        }
        
       
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for(String s: myMap.keySet()){
            if(label.equals(s)){
                ArrayList<String> list = myMap.get(s);
                return randomFrom(list);
            }
        }
        
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String need_sub = w.substring(first+1,last);
        String sub = getSubstitute(w.substring(first+1,last));
        while(count.indexOf(sub) != -1){
            sub = getSubstitute(w.substring(first+1,last));
        }
        count.add(sub);
        if(count_category.indexOf(need_sub) == -1){
            count_category.add(need_sub);
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        count.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println();
        System.out.println();
        for(int k=0; k < count.size(); k++ ){
            System.out.println(count.get(k));
        }
        System.out.println("total words in map is: " + totalWordsInMap());
        System.out.println("total words considered is: " + totalWordsConsidered());
        //totalWordsConsidered();
    } 
    
    private int totalWordsInMap(){
        int total = 0;
        for (String s: myMap.keySet()){
            ArrayList<String> list = myMap.get(s);
            total = list.size() + total;
        }
        return total;
    }
    
    private int totalWordsConsidered (){
        int total = 0;
        for(int k=0; k < count_category.size(); k++){
            if(myMap.containsKey(count_category.get(k))){
                ArrayList<String> list = myMap.get(count_category.get(k));
                total = total + list.size();
            }
            
            //System.out.println(count_category.get(k));
        }
        return total;
        //System.out.println(count_category.size());
    }
}

