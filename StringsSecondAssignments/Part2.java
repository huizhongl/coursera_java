
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int currentIndex = stringb.indexOf(stringa);
        int count = 0;
        while (currentIndex != -1){
            count = count + 1;
            currentIndex = stringb.indexOf(stringa, currentIndex + stringa.length());
        }
        return count;
    }
    public void testHowmany(){
        String stringa = "AA";
        String stringb = "ATAAAA";
        System.out.println("stringa : " + stringa);
        System.out.println("stringb : " + stringb);
        System.out.println(howMany(stringa,stringb));
    }
}
