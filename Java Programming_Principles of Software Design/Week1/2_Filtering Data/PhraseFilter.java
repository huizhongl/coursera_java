
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String searchType, String word){
        where = searchType;
        phrase = word;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        int idx = title.indexOf(phrase);
        if(where.equals("start")){
            if(idx == 0){
                return true;
            } 
        }
        
        if(where.equals("end")){
            if(title.indexOf(phrase,title.length()-phrase.length()) == title.length() - phrase.length()){
                return true;
            } 
        }
        
        if(where.equals("any")){
            if(idx != -1){
                return true;
            } 
        }
        return false;
    }
    
    public String getName(){
        return "Phrase";
    }
}
