
/**
 * Write a description of UniqueIPTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueIPTester {
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }
}