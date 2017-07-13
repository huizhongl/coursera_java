import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of MiniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniProject {
    public void printNames(){
        int total = 0;
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)){
            System.out.println("Name " + rec.get(0) + 
                               "Gender " + rec.get(1) +
                               "Num Born " + rec.get(2));           
        }
        System.out.println("total number of babys is: " + total);
    }
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int total_boys_name = 0;
        int total_girls_name = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths = totalBirths + numBorn;
            if(rec.get(1).equals("M")){
                totalBoys = totalBoys + numBorn;
                total_boys_name += 1;
            } else {
                totalGirls = totalGirls + numBorn;
                total_girls_name += 1;
            }
        }
        System.out.println("total births= " + totalBirths);
        System.out.println("total boys= " + totalBoys);
        System.out.println("total girls= " + totalGirls);
        System.out.println("total number of boys name= " + total_boys_name);
        System.out.println("total number of girls name= " + total_girls_name);
        System.out.println("total number of name= " + (total_boys_name + total_girls_name));
    }
    public void testTotalBirth(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank(int year, String name, String gender){
        String fName = "us_babynames_by_year/yob" + year + ".csv";
        int girls_num = 0;
        int rank = -1;
        FileResource fr = new FileResource(fName);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser){
            if (rec.get(1).equals("F")){
                girls_num += 1;
            }
        }
        parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            if (gender.equals("F") && rec.get(1).equals(gender)){
                if(name.equals(rec.get(0))){
                    rank = (int)parser.getCurrentLineNumber();
                }
            } 
            if (gender.equals("M") && rec.get(1).equals(gender)){
                if (name.equals(rec.get(0))){
                    rank = (int)(parser.getCurrentLineNumber() - girls_num);
                }
            } 
        }
        return rank;
    }
    public void testGetRank(){
        int rank = getRank(1971,"Frank","M");
        System.out.println("the rank is: " + rank );
        
    }
    public String getName(int year, int rank, String gender){
        String fName = "us_babynames_by_year/yob" + year + ".csv";
        String name = null;
        int girls_num = 0;
        FileResource fr = new FileResource(fName);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            if (rec.get(1).equals("F")){
                girls_num += 1;
            }
        }
        parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser){
            if (gender.equals("F") && rec.get(1).equals(gender)){
                if(rank == parser.getCurrentLineNumber()){
                    name = rec.get(0);
                }
            } 
            if (gender.equals("M") && rec.get(1).equals(gender)){
                if(rank == parser.getCurrentLineNumber() - girls_num ){
                    name = rec.get(0);
                }
            } 
        }
        return name;
    }
    public void testGetName(){
        String name = getName(1982, 450, "M");        
        System.out.println("name is: " + name);
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName( newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if he/she was born in " + newYear);
    }
    public void testWhatIsNameInYear(){       
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestYear = -1;
        int rank = 0;
        for(File f: dr.selectedFiles()){            
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            String fName = f.getName();
            int year = yearFromFileName(fName);  
            int currentRank = getRank(year, name, gender);
            if (rank == 0 && currentRank != -1){
                rank = currentRank;
                highestYear = year;
            } else {
                if (currentRank < rank && currentRank != -1){
                    rank = currentRank;
                    highestYear = year;
                }
            }
        }
        return highestYear;
    }
    public int yearFromFileName(String fName){
        int startIndex = fName.indexOf("yob");
        String year = fName.substring(startIndex + 3, startIndex + 7);
        int year_int = Integer.parseInt(year);
        return year_int;
    }
    public void testYearOfHighestRank(){
        int highestYear = yearOfHighestRank("Mich","M");
        System.out.println("His/Her highest rank is: " + highestYear);
    }    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;
        for(File f: dr.selectedFiles()){            
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            String fName = f.getName();
            int year = yearFromFileName(fName);  
            int currentRank = getRank(year, name, gender);
            if(currentRank != -1){
                totalRank = totalRank + currentRank;
                count = count + 1;
            }            
        }
        if (count == 0){
            return -1;
        }
        double avg = (double)totalRank / count;
        double truncate_avg = Math.floor(avg * 100) / 100;
        return  truncate_avg;
    }
    public void testGetAverageRank(){
        double avg = getAverageRank("Robert", "M");
        System.out.println("the average rank is: " + avg);
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fName = "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fName);
        CSVParser parser = fr.getCSVParser(false);
        int rank = getRank(year, name, gender);
        int total_birth_num = 0;
        parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser){
            int currentRank = getRank(year, rec.get(0), rec.get(1));
            if(rec.get(1).equals(gender) && currentRank < rank && rank != -1){
                total_birth_num = total_birth_num + Integer.parseInt(rec.get(2));
            }
        }
        return total_birth_num;
    }
    public void testGetTotalBirthsRankedHigher(){
        int total = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("total births of ranked higher is: " + total);
    }
}
