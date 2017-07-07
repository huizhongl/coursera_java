
/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class FindGeneWhile {
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int currentIndex = dna.indexOf("TAA", startIndex + 3);
        while(currentIndex != -1){
            if((currentIndex - startIndex) % 3 == 0 ){
                return dna.substring(startIndex, currentIndex + 3);
            } else {
                currentIndex = dna.indexOf("TAA", currentIndex + 1);
            }
        }
        return "";
    }
    public void testFindGeneSimple(){
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        String result = findGene(dna);
        System.out.println("Gene is " + result);
    }
}
