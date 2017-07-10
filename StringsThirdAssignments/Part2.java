
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
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
    public void test_cgRatio(){
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    public int countCTG(String dna){
        int currentIndex = dna.indexOf("CTG");
        int count = 0;
        while(true ){
            if(currentIndex == -1 ){
                break;
            }
            count = count + 1;
            currentIndex = dna.indexOf("CTG", currentIndex + 3);
        }
        return count;
    }
    public void test_CTG(){
        String dna = "XXXCTGxxCTGCT";
        //String dna = "XXXXCTGCTGXXXCTXXGXXCTGCTGC";
        System.out.println(countCTG(dna));
    }
}
