
/**
 * Write a description of AllGenesBreak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class AllGenesBreak {
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        int stopIndex_TGA = findStopCodon(dna, startIndex, "TGA");
        int stopIndex_TAG = findStopCodon(dna, startIndex, "TAG");
        int stopIndex_TAA = findStopCodon(dna, startIndex, "TAA");
        // int stopIndex = Math.min(stopIndex_TGA, Math.min(stopIndex_TAG, stopIndex_TAA));
        // if (stopIndex == dna.length()){
        //    return "";
        // }
        int minIndex = 0;
        if (stopIndex_TGA == -1 || 
            (stopIndex_TAG!= -1 && stopIndex_TAG < stopIndex_TGA )){
            minIndex = stopIndex_TAG;
        } else {
            minIndex = stopIndex_TGA;
        }
        if (minIndex == -1 || 
            (stopIndex_TAA != -1 && stopIndex_TAA < minIndex ) ){
            minIndex = stopIndex_TAA;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currentIndex != -1){
            if((currentIndex - startIndex) % 3 == 0){
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }
        // return dna.length();
        return -1;
    }
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while ( true ){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna );
        StorageResource genes = getAllGenes(dna);
        for(String s: genes.data()){
            System.out.println(s);
        }
    }
    public void test(){
        testOn("ATGATCTAAATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
