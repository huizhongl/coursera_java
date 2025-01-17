
/**
 * Write a description of Q1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            if((currentIndex - startIndex) % 3 == 0){
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }
        return dna.length();
    }
    public void testFindStopCodon(){
       String dna = "ATGTTTTGA";
       System.out.println(findStopCodon(dna, 0, "TTG"));
    }
    public String findGene(String dna, int flag){
        int startIndex = dna.indexOf("ATG",flag );
        if (startIndex == -1) {
            return "";
        } 
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex),tgaIndex);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);        
    }
    public void testFindGene(){
        String dna = "ATGxxxyyyTAG";
        String result = findGene(dna, 0);
        System.out.println(result);
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while ( true ){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex ) + currentGene.length();
        }
    }
    public void countGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        int count = 0;
        for (String dna: fr.words()){
            int startIndex = 0;
            while ( true ){
                String currentGene = findGene(dna, startIndex);
                if(currentGene.isEmpty()){
                    break;
                }
                if(currentGene.length() > 60 ){
                    count = count + 1;                
                }
                    startIndex = dna.indexOf(currentGene, startIndex ) + currentGene.length();
            }
        }
        System.out.println(count);
    }
}
