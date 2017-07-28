
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        // TO DO
        if(howMany > quakeData.size()){
            howMany = quakeData.size();
        }
        for(int k=0; k < howMany; k++){
            double min = 0;
            QuakeEntry min_qe = null;
            for(QuakeEntry qe: copy){
                Location loc = qe.getLocation();
                double dist = loc.distanceTo(current);
                if(min == 0){
                    min = dist;
                    min_qe = qe;
                }else if(dist < min){
                    min = dist;
                    min_qe = qe;
                }
            }
            ret.add(min_qe);
            copy.remove(min_qe);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,30);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
}
