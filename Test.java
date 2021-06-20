
/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Test {
    public static double countCg(String dna){
        int cIndex = dna.indexOf("C",0);
        int countC = 0;
        while(cIndex != -1){
            countC++;
            cIndex = dna.indexOf("C",cIndex+1);
        }
        
        int gIndex = dna.indexOf("G",0);
        int countG = 0;
         while(gIndex != -1){
            countG++;
            gIndex = dna.indexOf("G",gIndex+1);
        }
        System.out.println(countC);
        System.out.println(countG);
        
        return  (double)countC/countG;
    }
    public static int countCTG(String dna){
        int ctgIndex = dna.indexOf("CTG",0);
        int count = 0;
        while(ctgIndex != -1){
            count++;
            ctgIndex = dna.indexOf("CTG",ctgIndex+3);
        }
        return count;
    }
    public static void main(String args[]){  
        URLResource page = 
        new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
		String source = page.asString();
        String str = page.asString();
        double cgRatio = countCg(str); 
        int count = countCTG(str);
        System.out.println(count);
    }
}
