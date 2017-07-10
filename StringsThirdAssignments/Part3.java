
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part3 {
    FileResource fr = new FileResource("brca1line.fa");
    String dna = fr.asString();
    StorageResource sr = findAllGenes(dna);
    public String findGene(String dna, int flag){
        int startIndex = dna.indexOf("ATG", flag);
        if (startIndex == -1){
            return "";
        }
        int taa_currentIndex = findStopCodon(dna, startIndex, "TAA");
        int tag_currentIndex = findStopCodon(dna, startIndex, "TAG");
        int tga_currentIndex = findStopCodon(dna, startIndex, "TGA");
        int minCurrentIndex = Math.min(Math.min(taa_currentIndex,tag_currentIndex),tga_currentIndex);
        if(minCurrentIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minCurrentIndex);
    }
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while( currentIndex != -1){
            if(( currentIndex - startIndex )% 3 == 0){
                return currentIndex;   
            }else{
                currentIndex = dna.indexOf(stopCodon, currentIndex + 3);
            }
        }
        return dna.length();
    }
    public void testFindStopCodon(){
       String dna = "ATGTTTTGA";
       System.out.println(findStopCodon(dna, 0, "TTG"));
    }
    public StorageResource findAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }
    public void testOn(String dna){
        // System.out.println("Testing printAllGenes on " + dna );
        StorageResource genes = findAllGenes(dna);
        for (String g: genes.data()){
            System.out.println(g);  
        }
    }
    public void test(){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        testOn(dna);
    }
    public void processGenes(String dna){
        StorageResource sr = findAllGenes(dna);
        for(String g: sr.data()){
            System.out.println(g);
        }
    }
}
