
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
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le: records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                counts.put(ip,1);
             } else{
                 int value = counts.get(ip);
                 counts.put(ip,value+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(String s: counts.keySet()){
             int value = counts.get(s);
             if(value > max ){
                 max = value;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> visit = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for(String s: counts.keySet()){
             int value = counts.get(s);
             if(value == max){
                 visit.add(s);
             }
         }
         return visit;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDay(){
         HashMap<String,ArrayList<String>> ipMap = new HashMap<String,ArrayList<String>>();
         for(LogEntry le: records){
            String date = (le.getAccessTime()).toString();
            date = date.substring(4,10);
            String ip = le.getIpAddress();
            if(!ipMap.containsKey(date)){
                ArrayList<String> ip_list = new ArrayList<String>();
                ip_list.add(ip);
                ipMap.put(date, ip_list);
            } else {
                ArrayList<String> ip_list = ipMap.get(date);
                ip_list.add(ip);
                ipMap.put(date, ip_list);
            }
         }

         return ipMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipMap){
         int max = 0;
         String day = "";
         for(String s: ipMap.keySet()){
            ArrayList<String> ip_list = ipMap.get(s);
            if(ip_list.size() > max){
                max = ip_list.size();
                day = s;
            }
         }
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipMap, String day){
         // max = 0;
         ArrayList<String> result = new ArrayList<String>();
         for(String s: ipMap.keySet()){
            HashMap<String, Integer> ipMap2 = new HashMap<String,Integer>();
            ArrayList<String> ip_list = ipMap.get(s);
            if(day.equals(s)){
                for(int k=0; k<ip_list.size(); k++){
                    String ip = ip_list.get(k);
                    if(!ipMap2.containsKey(ip)){
                        ipMap2.put(ip,1);
                    } else {
                        int value = ipMap2.get(ip);
                        ipMap2.put(ip, value+1);
                    }
                }
                //max = mostNumberVisitsByIP(ipMap2);
                result = iPsMostVisits(ipMap2);
            }
         }
         return result;
     }
}
