import java.util.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> map;
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start, String dna){
        map.clear();
        for(int k=start; k <= dna.length()-3; k=k+3){
            String s = dna.substring(k,k+3);
            if(map.containsKey(s)){
                map.put(s, map.get(s)+1);
            } else {
                map.put(s, 1);
            }
        }
        /*
        for(String a: map.keySet()){
            System.out.println(a + "\t" + map.get(a));
        }
        */
    }
    
    public String getMostCommonCodon(){
        String common = "";
        int max = 0;
        for(String s: map.keySet()){
            if(map.get(s) > max){
                max = map.get(s);
                common = s;
            }
        }
        return common;
    }
    
    public void printCodonCounts(int start, int end){
        System.out.println("print all the codon in the HashMap along with their counts if between " + start + " and " + end);;
        for(String s: map.keySet()){
            if(map.get(s) >= start && map.get(s) <= end){
                System.out.println(s + " " + map.get(s));
                System.out.println();
            }
        }        
    }
        
    public void test(){
        buildCodonMap(2, "CGTTCAAGTTCAA");
        System.out.println();
        System.out.println("The most common codon is: " + getMostCommonCodon());
        printCodonCounts(2,3);
    }
    public void testtest(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.trim();
        //System.out.println(dna.length());
        dna = dna.toUpperCase();
        for (int num = 0; num < 3; num++){
            buildCodonMap(num, dna);
            System.out.println("Reading frame starting with " + num + " results in " + map.size() +  " unique codons");
            String common = getMostCommonCodon();
            System.out.println("and most common codon is" + common + "with count " + map.get(common));
            System.out.println("Counts of codons between num1 and num2 inclusive are:");
            printCodonCounts(7,8);
            System.out.println();
            System.out.println();
            
            
        }
        
    }
}
