
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow: parser){            
            largestSoFar = getlargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largestSoFar = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largestSoFar.get("TemperatureF") + " at " + largestSoFar.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getlargestOfTwo(current,  largestSoFar);
        }
        return largestSoFar;
    }
    public CSVRecord getlargestOfTwo(CSVRecord current, CSVRecord largestSoFar){
        if(largestSoFar == null){
                largestSoFar = current;
            } else {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if(currentTemp > largestTemp){
                    largestSoFar = current;
                }
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
}
