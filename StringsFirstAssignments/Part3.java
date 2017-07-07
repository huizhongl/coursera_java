
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1) {
            return false;
        }
        int stopIndex = stringb.indexOf(stringa, startIndex);
        if (stopIndex == -1) {
            return false;
        }
        startIndex = stringb.indexOf(stringa, stopIndex + stringa.length());
        if (startIndex != -1){
            stopIndex = stringb.indexOf(stringa, startIndex);
            if (stopIndex != -1) {
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }       
    }
    public void testing() {
        String stringa_1 = "by";
        String stringb_1 = "A story by Abby Long";
        boolean result_1 = twoOccurrences(stringa_1, stringb_1);
        String rresult_1 = lastPart(stringa_1, stringb_1);
        System.out.println(result_1);
        System.out.println("The part of the string after " + stringa_1 + " in " + stringb_1+ " is " + rresult_1);
        
        String stringa_2 = "zoo";
        String stringb_2 = "forest";
        boolean result_2 = twoOccurrences(stringa_2, stringb_2);
        String rresult_2 = lastPart(stringa_2, stringb_2);
        System.out.println(result_2);
        System.out.println("The part of the string after " + stringa_2 + " in " + stringb_2+ " is " + rresult_2);
        
        String stringa_3 = "nana";
        String stringb_3 = "banana";
        boolean result_3 = twoOccurrences(stringa_3, stringb_3);
        System.out.println(result_3);
    }
    public String lastPart(String stringa, String stringb){
        String result = "";
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){
            result = stringb;
        } else {
            result = stringb.substring(startIndex + stringa.length());
        }
        return result;
    }
}
