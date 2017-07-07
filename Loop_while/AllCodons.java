
/**
 * Write a description of AllCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class AllCodons {
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
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
            minIndex = stopIndex_TAG;
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
    public void testSimple(){
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna);
        if (! gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("error");
        }
        System.out.println("tests finished");
    }
}
