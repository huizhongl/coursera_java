
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Part2 {
    public String findSimpleGene(String dna, String startCondon, String stopCondon) {
        String result = "";
        int startIndex = dna.indexOf(startCondon);
        if (startIndex == -1){
            startIndex = dna.indexOf(startCondon.toLowerCase());
            if (startIndex == -1){
                return "";
            }
            
        }
        int endIndex = dna.indexOf(stopCondon, startIndex );
        if (endIndex == -1){
            endIndex = dna.indexOf(stopCondon.toLowerCase(), startIndex);
            if (endIndex == -1){
                return "";
            }
        }
        if ( (endIndex - startIndex) % 3 == 0 ){
            result = dna.substring(startIndex, endIndex + 3);
        }
        return result;
    }
    public void testSimpleGene() {
        String dna1 = "ATTTTAA";
        System.out.println("The dna1 is " + dna1);
        String gene1 = findSimpleGene(dna1, "ATG", "TAA");
        System.out.println("The gene1 is " + gene1);
        
        String dna2 = "ATTATGTTTTTTA";
        System.out.println("The dna1 is " + dna2);
        String gene2 = findSimpleGene(dna2,"ATG", "TAA");
        System.out.println("The gene1 is " + gene2);
       
        
        String dna3 = "ATTATGTTTTTTAA";
        System.out.println("The dna1 is " + dna3);
        String gene3 = findSimpleGene(dna3, "ATG", "TAA");
        System.out.println("The gene1 is " + gene3);
        
        String dna4 = "ATTATGTTTTTGTAA";
        System.out.println("The dna1 is " + dna4);
        String gene4 = findSimpleGene(dna4, "ATG", "TAA");
        System.out.println("The gene1 is " + gene4);
        
        String dna5 = "gatgctataat";
        System.out.println("The dna1 is " + dna5);
        String gene5 = findSimpleGene(dna5, "ATG", "TAA");
        System.out.println("The gene1 is " + gene5);
    }
}
