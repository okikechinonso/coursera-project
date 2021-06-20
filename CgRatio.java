
/**
 * Write a description of CgRatio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class CgRatio {
    public double countCg(String dna){
        int cIndex = dna.indexOf("c",0);
        int countC = 0;
        while(cIndex != -1){
            countC++;
            cIndex = dna.indexOf("c",cIndex+1);
        }
        
        int gIndex = dna.indexOf("g",0);
        int countG = 0;
         while(gIndex != -1){
            countG++;
            gIndex = dna.indexOf("g",gIndex+1);
        }
        System.out.println(countC);
        System.out.println(countG);
        
        return  (double)countC/countG;
    }
    public int countCTG(String dna){
        int ctgIndex = dna.indexOf("ctg",0);
        int count = 0;
        while(ctgIndex != -1){
            count++;
            ctgIndex = dna.indexOf("ctg",ctgIndex+3);
        }
        return count;
    }
    public void processGenes(StorageResource sr){
        int count = 0;    
        
        //finding gene with lenght greater than 9
        for(String s : sr.data()){
            if(s.length()>9){
                System.out.println(s);
                count++;
            }            
        }
        System.out.println(
                "The number of gene with greater than Nine is "+count);
                
        //findding the gene with the cg ratio greater than 0.35;
        count =0;
        for(String s: sr.data()){
            double cg = countCg(s);
            if(cg>0.35){
                System.out.println(s);
                count++;
            }            
        }
        System.out.println(
                "The gene with CG-Ration greater than 0.35 is "+count);
                
        //finding the longest gene
        int geneLongest =0;
        for(String s: sr.data()){
            int currLength = s.length();
            if(geneLongest==0){
                geneLongest = currLength;
            }
            else{
                if(currLength>geneLongest){
                    geneLongest = currLength;
                }
                else{
                    System.out.println(s);
                }
            }
        }
    }
    public void test(){
        URLResource page = 
        new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String source = page.asString();  
        
        double cIndex = 
            countCg("cccatggggttctgtaaataactgcctagataataggagagagagagagactgttt");
        int count = 
            countCTG("cccatggggttctgcttaaataactgcctagataataggagagagagagagactgttt");
        System.out.println(cIndex);
        System.out.println("ctgratio " +count);
    }
}
