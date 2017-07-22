
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String s: fr.lines()){
             
             LogEntry le = WebLogParser.parseEntry(s);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
         }
         return uniqueIPs.size();
     }
     public void printAllHigherThanNum(int num){
         ArrayList<LogEntry> le_htn = new ArrayList<LogEntry>();
         ArrayList<Integer> uniqueStatus = new ArrayList<Integer>();
         for(LogEntry le: records){
             int statusCode = le.getStatusCode();
             if(statusCode > num){
                 le_htn.add(le);
                 if(!uniqueStatus.contains(statusCode)){
                     uniqueStatus.add(statusCode);
                 }
             }
         }
         
         for(int k = 0; k< uniqueStatus.size(); k++){
             System.out.println("The unique statusCode is: " + uniqueStatus.get(k));
         }
         
         for(LogEntry le: le_htn){
            System.out.println(le);
         }
     }
     
     public void uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIP_day = new ArrayList<String>();
         for(LogEntry le: records){
             String date = (le.getAccessTime()).toString();
             //System.out.println(date);
             int index = date.indexOf(someday);
             String ip = le.getIpAddress();
             if(index != -1){
                if(!uniqueIP_day.contains(ip)){
                    uniqueIP_day.add(ip);
                } 
             }
         }
         System.out.println("The number of unique IP is: " + uniqueIP_day.size());
         for(int k=0; k < uniqueIP_day.size(); k++ ){
             System.out.println(uniqueIP_day.get(k));
         }
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIP_range = new ArrayList<String>();
         for(LogEntry le: records){
             int status = le.getStatusCode();
             String ip = le.getIpAddress();
             if(status >= low && status <= high){
                 if(!uniqueIP_range.contains(ip)){
                     uniqueIP_range.add(ip);
                 }
             }
         }
         return  uniqueIP_range.size();
     }
}
