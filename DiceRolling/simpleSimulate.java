import edu.duke.*;
import java.util.Random;
/**
 * Write a description of simpleSimulate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class simpleSimulate {
    public void simpleSimulate(int rolls){
        Random rand = new Random();
        int count_2 = 0;
        int count_12 = 0;
        for(int i = 0; i < rolls; i++ ){
            int dic1 = rand.nextInt(6) + 1;
            int dic2 = rand.nextInt(6) + 1;
            int total = dic1 + dic2;
            if(total == 2){
                count_2 += 1;
            }
            if(total == 12){
                count_12 += 1;
            }
        }
        System.out.println("2's=\t" + count_2 + "\t" + 100.0 * count_2/ rolls);
        System.out.println("12's=\t" + count_12 + "\t" + 100.0 * count_12/ rolls);        
    }
    public void simulate(int rolls){
        Random rand = new Random();
        int [] count = new int[13];
        for(int i = 0; i < rolls; i++ ){
            int dic1 = rand.nextInt(6) + 1;
            int dic2 = rand.nextInt(6) + 1;
            int total = dic1 + dic2;
            count[total] += 1;
        }
        for(int i = 2; i < count.length; i++ ){
            System.out.println(i + "'s=\t" + count[i] + "\t" + 100.0 * count[i]/rolls);
        }
    }
}