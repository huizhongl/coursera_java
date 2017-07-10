
/**
 * Write a description of Part3_new here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class Part3_new {
    int count_9 = 0;
    int count_35 = 0;
    int max = 0;
    StorageResource ssr = new StorageResource();
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = (fr.asString()).toUpperCase();
    // dna = dna.toUpperCase();
    public int countCTG(String dna){
        int currentIndex = dna.indexOf("CTG");
        int count_CTG = 0;
        while(true ){
            if(currentIndex == -1 ){
                break;
            }
            count_CTG = count_CTG + 1;
            currentIndex = dna.indexOf("CTG", currentIndex + 3);
        }
        return count_CTG;
    }
    public float cgRatio(String dna) {
        int count = 0;
        int currentIndex = dna.indexOf("C");
        while( true ){ 
            if(currentIndex == -1){
                break;
            }
            count = count + 1;
            currentIndex = dna.indexOf("C",currentIndex + 1);
        }
        currentIndex = dna.indexOf("G");
        while( true ){ 
            if(currentIndex == -1){
                break;
            }
            count = count + 1;
            currentIndex = dna.indexOf("G",currentIndex + 1);
        }
        return (float)count/dna.length();
    }
    public void processGenes(StorageResource sr){
        System.out.println("print all the strings in sr longer than 9");

        for (String g: sr.data()){
            if (g.length() > 60){
                System.out.println(g);
                System.out.println("CG: " + cgRatio(g));
                count_9 = count_9 + 1;
            }
            if(cgRatio(g) > 0.35){
                count_35 = count_35 + 1;
            }
            if (g.length() > max){
                max = g.length();
            }
            
        }       
    }
    public void testProcessGenes(){

        // String dna = "AAATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        System.out.println("String is: " + dna);
        ssr = printAllGenes(dna);
       
        processGenes(ssr);
        System.out.println("number of strings(longer than 9): " + count_9);
        System.out.println("number of strings(cgRate larger than 0.35): " + count_35);
        System.out.println("times of CTG: " + countCTG(dna));
        System.out.println("the length of longest gene is: " + max);
    }
    
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            if((currentIndex - startIndex) % 3 == 0){
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 3);
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
    public StorageResource printAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while ( true ){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex ) + currentGene.length();
        }
        return geneList;
    }
        public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna );
        StorageResource genes = printAllGenes(dna);
        for (String g: genes.data()){
            System.out.println(g);
        }
    }
    public void test(){
        testOn("ATGATCTAAATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}

