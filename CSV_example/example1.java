
/**
 * Write a description of example1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class example1 {
    public void listExperters(CSVParser parser, String exportOfInterest){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExperters(parser, "coffee");
    }
}
