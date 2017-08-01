
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        System.out.println("read data for "+list.size()+" quakes");
        
        /*
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        */
        
        /*
        sortByLargestDepth(list);
        for(QuakeEntry qe: list){
            System.out.println(qe);
        }
        */
       
        //sortByMagnitudeWithBubbleSort(list);
        int pass = sortByMagnitudeWithBubbleSortWithCheck(list);
        System.out.println("needed number of pass: " + pass);
        //sortByMagnitudeWithCheck(list);
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
       
       int maxIndex = from;
       
       for(int i = from+1; i<quakeData.size(); i++ ){
          if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
              maxIndex = i;
          } 
       }
       
       
       return maxIndex;
       
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for(int i=0; i< in.size(); i++){
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry max = in.get(maxIdx);
            QuakeEntry current = in.get(i);
            in.set(i,max);
            in.set(maxIdx, current);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i=1; i<quakeData.size()-numSorted; i++){
            if(quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude()){
                QuakeEntry left = quakeData.get(i-1);
                QuakeEntry right = quakeData.get(i);
                quakeData.set(i-1, right);
                quakeData.set(i,left);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        System.out.println("origin: ");
        for(QuakeEntry qe: in){
            System.out.println(qe);
        }
        for(int i=0; i<in.size()-1 ; i++){
           onePassBubbleSort(in, i);
           System.out.println("number of pass: " + (i+1));
           for(QuakeEntry qe: in){
               System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=1; i<quakes.size(); i++){
            if(quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public int sortByMagnitudeWithBubbleSortWithCheck (ArrayList<QuakeEntry> in){
        int pass = 0;
        while(!checkInSortedOrder(in)){
            onePassBubbleSort(in, pass);
            pass += 1;
        }
        return pass;
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int swap = 0;
        while(!checkInSortedOrder(in)){
            int minIdx = getSmallestMagnitude(in,swap);
            QuakeEntry qi = in.get(swap);
            QuakeEntry qmin = in.get(minIdx);
            in.set(swap,qmin);
            in.set(minIdx,qi);
            swap += 1;
        }
        System.out.println("numbers of swap: " + swap);
    }
}
