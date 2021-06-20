
/**
 * Write a description of Assignments here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Assignments {
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
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
        while(currIndex !=1){
            int diff = currIndex-startIndex;
            if(diff %3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    public String findGene(String dna,int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        
        //look for minimum index
       // int temp = Math.min(taaIndex,tgaIndex);
        //int minIndex = Math.min(temp,tagIndex);
        int minIndex = 0;
        if(taaIndex == -1 || 
            (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex =tgaIndex;        
        }
        else{
            taaIndex = minIndex;
        }
        if(minIndex == -1 || 
            (tagIndex != -1 && tagIndex < minIndex)){
            minIndex =tagIndex;        
        }
        
        
        if(minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex,minIndex+3);
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
               break;
            }
            
            geneList.add(currentGene);            
            
            startIndex = dna.indexOf(currentGene, startIndex) + 
                            currentGene.length();
        }
        return geneList;
    }
    public void test(){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        for(String s: sr.data()){
            System.out.println(s);
        }
        
    }
}
