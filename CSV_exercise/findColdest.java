
/**
 * Write a description of findColdest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class findColdest {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest = null;
        for(CSVRecord currentRow: parser){
            if(coldest == null){
                coldest = currentRow;
            }else{
                double current = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestSoFar = Double.parseDouble(coldest.get("TemperatureF"));
                if (current < coldestSoFar && current != -9999){
                    coldest = currentRow;
                }
            }
        }
        return coldest;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("the coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
    }
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String filePath = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(coldest == null){
                coldest = currentRow;
                filePath = f.getName();
            }else{
                double current = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestSoFar = Double.parseDouble(coldest.get("TemperatureF"));
                if (current < coldestSoFar && current != -9999){
                    coldest = currentRow;
                    filePath = f.getName();
                }
            }
        }
        return filePath;
    }
    public void testFileWithColdestTemperature(){
        String filePath = fileWithColdestTemperature();
        System.out.println("Coldest day was in file" + filePath);
        FileResource fr = new FileResource("nc_weather/2013/" + filePath);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was: " + coldest.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were: ");
        parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
        
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowest = null;
        for(CSVRecord currentRow: parser){
            if(lowest == null){
                lowest = currentRow;
            }else{
                    if(! currentRow.get("Humidity").equals("N/A")){
                    double current = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestSoFar = Double.parseDouble(lowest.get("Humidity"));
                    if (current < lowestSoFar ){
                        lowest = currentRow;
                    }                
                }
            }
        }
        return lowest;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = lowestHumidityInFile(parser);
            if(lowest == null){
                lowest = currentRow;
            }else{
                if(currentRow.get("Humidity") != "N/A"){
                    double current = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestSoFar = Double.parseDouble(lowest.get("Humidity"));
                    if (current < lowestSoFar ){
                        lowest = currentRow;
                    }                
                }
            }
        }
        return lowest;
    }    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser){
        double totalTemp = 0;
        int count = 0;
        for(CSVRecord record: parser){
            double currentRow = Double.parseDouble(record.get("TemperatureF"));
            if(currentRow != -999){
                totalTemp = totalTemp + currentRow;
                count = count + 1;
            }
        }
        return totalTemp/count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is: " + avg );
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double total_temp = 0;
        int count = 0;
        for(CSVRecord record : parser){           
            double current_temp = Double.parseDouble(record.get("TemperatureF"));
            double current_hum = Integer.parseInt(record.get("Humidity"));
            if (! record.get("Humidity").equals("N/A") && current_hum >= value){
                total_temp = total_temp + current_temp;
                count = count + 1;
            }
        }
        return total_temp/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average temperature in file is: " + avg );
    }
}    
