
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class ParsingExportData {
    int count = 0;
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        System.out.println();
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        System.out.println();
        
        parser = fr.getCSVParser();
        numberOfExporters(parser, "cocoa");
        System.out.println(count);
        System.out.println();
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record: parser){
            String country_csv = record.get("Country");
            if(country_csv.contains(country)){
                // System.out.println(country_csv);
                // System.out.println(record.get("Exports"));
                // System.out.println(record.get("Value (dollars)"));
                String result = country_csv + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return result;
            } 
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportitem1) && export.contains(exportitem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void numberOfExporters(CSVParser parser, String exportItem){
        for (CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                count = count + 1;
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                String country = record.get("Country");
                System.out.println(country + ": " + value);
            }
        }
        
    }
}
