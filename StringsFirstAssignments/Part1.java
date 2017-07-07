
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex );
        if (endIndex == -1){
            return "";
        }
        if ( (endIndex - startIndex) % 3 == 0 ){
            result = dna.substring(startIndex, endIndex + 3);
        }
        return result;
    }
    public void testSimpleGene() {
        String dna1 = "ATTTTAA";
        System.out.println("The dna1 is " + dna1);
        String gene1 = findSimpleGene(dna1);
        System.out.println("The gene1 is " + gene1);
        
        String dna2 = "ATTATGTTTTTTA";
        System.out.println("The dna1 is " + dna2);
        String gene2 = findSimpleGene(dna2);
        System.out.println("The gene1 is " + gene2);
       
        
        String dna3 = "ATTATGTTTTTTAA";
        System.out.println("The dna1 is " + dna3);
        String gene3 = findSimpleGene(dna3);
        System.out.println("The gene1 is " + gene3);
        
        String dna4 = "ATTATGTTTTTGTAA";
        System.out.println("The dna1 is " + dna4);
        String gene4 = findSimpleGene(dna4);
        System.out.println("The gene1 is " + gene4);
    }
}
