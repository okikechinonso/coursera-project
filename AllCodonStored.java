
/**
 * Write a description of AllCodonStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class AllCodonStored {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
        while(currIndex !=-1){
            int diff = currIndex-startIndex;
            if(diff %3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaStr.length();
    }
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        
        //look for minimum index
        //int temp = Math.min(taaIndex,tgaIndex);
        //int minIndex = Math.min(temp,tagIndex);
        int minIndex = 0; 
        if(taaIndex == -1 || 
            (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex =tgaIndex;        
        }
        else{
             minIndex=taaIndex;
        }
        if(minIndex == -1 || 
            (tagIndex != -1 && tagIndex < minIndex)){
            minIndex =tagIndex;        
        }
        
        
        if(minIndex == dna.length()){
            return "";
        }
        
        if(dna.length() == minIndex){
            return "";
        }
        
        return dna.substring(startIndex,minIndex+3);
    }
    public int countCTG(String dna){
        int ctgIndex = dna.indexOf("CTG",0);
        int count = 0;
        while(ctgIndex != -1){
            count++;
            ctgIndex = dna.indexOf("CTG",ctgIndex+3);
        }
        return count;
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
    public double countCg(String dna){
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
        
        return  (double)countC/countG;
    }
    public void processGenes(StorageResource sr){
        int countGene = 0;
        int greaterThanSixty = 0;
        int countCg =0;
        int geneLongest =0;
        
        
        //finding gene with lenght greater than 9
        for(String s : sr.data()){
            countGene++;
            if(s.length()>60){                
                greaterThanSixty++;
            }
            //findding the gene with the cg ratio greater than 0.35
            double cg = countCg(s);
            if(cg>0.35){                
                countCg++;
            }            
            //finding the longest gene            
            int currLength = s.length();
            int prevLength =0;
            if(geneLongest==0){
                geneLongest = currLength;
            }
            else{
                if(currLength>geneLongest){
                    geneLongest = currLength;                    
                }
                else{
                    prevLength = currLength;
                }
            }            
        }
        System.out.println("The number of gene is "+countGene);
        System.out.println("The number of gene greater 60 is "+ greaterThanSixty);
        System.out.println("The number of gene with CG ratio greater 0.35 is "+countCg);
        System.out.println("The longest gene has a length"+geneLongest);
        
    }
    public String mystery(String dna){
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if(pos==-1){
            return dna;
        }
        while (count<3){
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T",startPos);
            if(pos==-1){
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }
    public void test (){
        URLResource page = 
        new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String source = page.asString();                 
        StorageResource gene = getAllGenes(source);
        processGenes(gene);
        int CTG = countCTG(source);
        System.out.println("The number of CTG in the DNA is "+CTG);
        String dna = "ATGCCCTAG";
        String mystery = mystery(dna);
        System.out.println("the mystery method"+mystery);
    }
 }

