import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> list;
    public MatchAllFilter(){
        list = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        list.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f: list){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        StringBuilder sb = new StringBuilder();
        for(Filter f: list){
            sb.append(f.getName());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public void testMatchAllFilter(){
       EarthQuakeParser parser = new EarthQuakeParser(); 
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       String source = "data/nov20quakedatasmall.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       
       MatchAllFilter maf = new MatchAllFilter();
       Filter f1 = new MagnitudeFilter(0.0, 2.0);
       Filter f2 = new DepthFilter(-100000.0, -10000.0);
       Filter f3 = new PhraseFilter("any", "a");
       maf.addFilter(f1);
       maf.addFilter(f2);
       maf.addFilter(f3);
       EarthQuakeClient2 qec2 = new EarthQuakeClient2();
       ArrayList<QuakeEntry> result = qec2.filter(list, maf);
       for(QuakeEntry qe: result){
           System.out.println(qe);
       }
       
       String filter_used = maf.getName();
       System.out.println("Filter used are: " + filter_used);
    }
}

