import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*
        for(QuakeEntry qe: list){
            System.out.println(qe);
        }
        */
        System.out.println("read data for "+list.size()+" quakes");
        int maxIndex = indexOfLargest(list);
        // System.out.println("the max index is: " + maxIndex + " and has magnitude " + list.get(maxIndex).getMagnitude() );
        ArrayList<QuakeEntry> answer = getLargest(list, 50);
        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIndex = 0;
        for(int k=0; k < data.size(); k++ ){
           double mag = data.get(k).getMagnitude();
           if(mag > data.get(maxIndex).getMagnitude()){
               maxIndex = k;
           }
        }
        return maxIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(howMany > quakeData.size()){
            howMany = quakeData.size();
        }
        ArrayList<QuakeEntry> copy = quakeData;
        for(int k=0; k < howMany; k++){
           int maxIndex = indexOfLargest(copy); 
           answer.add(copy.get(maxIndex));
           copy.remove(maxIndex);
        }
        return answer;
    }
}
