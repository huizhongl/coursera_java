
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private double maxDistance;
    public DistanceFilter(Location location, double max){
        loc = location;
        maxDistance = max;
    }
    public boolean satisfies(QuakeEntry qe){
        if(qe.getLocation().distanceTo(loc) < maxDistance){
            return true;
        } else {
            return false;
        }
    }
    
    public String getName(){
        return "Distance";
    }
}
