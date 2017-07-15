
/**
 * Write a description of CharacterDemo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterDemo {
    public void digitTest(){
        String test = "dFEd232&&#^^1wdq2eqas";
        for(int i = 0; i < test.length(); i++ ){
            char ch = test.charAt(i);
            if(Character.isDigit(ch)){
                System.out.println(ch + " is a digit.");
            }
            if(Character.isLetter(ch)){
                System.out.println(ch + " is a letter.");
            }
            if(ch == '#'){
                System.out.println(ch + " #hashtag");
            }
        }
    }
    public void conversionTest(){
        String test = "AVBadwad123&^%#";
        for(int i = 0; i < test.length(); i++){
            char ch = test.charAt(i);
            char chl = Character.toLowerCase(ch);
            char chu = Character.toUpperCase(ch);
            System.out.println(ch + " " + chl + " " + chu);
        }
    }
}
