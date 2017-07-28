import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe: quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        
        for(QuakeEntry qe: quakeData){
            Location loc = qe.getLocation();
            if(loc.distanceTo(from) < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        double magMin = 5.0;
        ArrayList<QuakeEntry> bigQuake = filterByMagnitude(list, magMin);
        for(QuakeEntry qe: bigQuake){
            System.out.println(qe);
        }
        System.out.println("Found " + bigQuake.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        double distMax = 1000000.00;
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list,distMax,city);
        for(QuakeEntry qe: answer){
            System.out.println(qe.getLocation().distanceTo(city)/1000 + " " +  qe.getInfo());
        }
        System.out.println("Found " + answer.size()+ " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        double minDepth = -10000.0;
        double maxDepth = -8000.0;
        ArrayList<QuakeEntry> answer = filterByDepth(list,minDepth,maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size()+ " quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase){
       ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       for(QuakeEntry qe: quakeData){
           String title = qe.getInfo();
           int idx = title.indexOf(phrase);
           if(where.equals("start")){
               if(idx == 0){
                   answer.add(qe);
               }
           }
           if(where.equals("end")){
               if(title.indexOf(phrase, title.length()- phrase.length()- 1) == title.length()-phrase.length()){
                   answer.add(qe);
               }
           }
           if(where.equals("any")){
               if(idx != -1){
                   answer.add(qe);
               }
           }
       }
       return answer;
    }
    
    public void quakeByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        String phrase = "Creek";
        String where = "any";
        ArrayList<QuakeEntry> answer = filterByPhrase(list,where,phrase);
        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size()+ " quakes that match that criteria");
    }
}
