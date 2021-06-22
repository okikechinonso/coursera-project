/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBirths {    
    // this print total number of births, girls or boys
    public int totalBirths (FileResource fr,String gender) {
        int totalBirths = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));            
            if (rec.get(1).equals(gender)) {
                totalBirths += numBorn;  
            }            
        }
        return totalBirths;
    }
    public int getBirthNum(FileResource fr,String name, String gender){
        int birthNum = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            String csvName = rec.get(0);
            String csvGender = rec.get(1);
            if(csvName.equals(name) && csvGender.equals(gender)){
                birthNum = Integer.parseInt(rec.get(2));
            }
        }
        
        return birthNum;
    }
    // this get the rank name
    public int getRank(int year, String name, String gender){        
        int totalBirth = 0;
        int rank = 1;
        
        FileResource fr = yearFile(year);
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int currBirth = Integer.parseInt(rec.get(2));
            String csvName = rec.get(0);
            String csvGender = rec.get(1);           
            
            if(csvName.equals(name) && csvGender.equals(gender)){
                totalBirth = Integer.parseInt(rec.get(2));
            }
            if(csvGender.equals(gender) && totalBirth<currBirth) rank++;                        
            
        }
        //check if the a certain name exist
        if(totalBirth==0){
            return -1;
        }
        
    return rank; 
    }
    public int getRankMany(FileResource fr, int year, String name, String gender){        
        int totalBirth = 0;
        int rank = 1;
        
        fr = yearFile(year);
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int currBirth = Integer.parseInt(rec.get(2));
            String csvName = rec.get(0);
            String csvGender = rec.get(1);           
            
            if(csvName.equals(name) && csvGender.equals(gender)){
                totalBirth = Integer.parseInt(rec.get(2));
            }
            if(csvGender.equals(gender) && totalBirth<currBirth) rank++;                        
            
        }
        //check if the a certain name exist
        if(totalBirth==0){
            return -1;
        }
        
    return rank; 
    }
    public String getName(int year, int rank, String gender){
        int count = 0;
         FileResource fr = yearFile(year);
         for (CSVRecord rec : fr.getCSVParser(false)){            
            String csvGender = rec.get(1);
            
            if(csvGender.equals(gender)){
                count++;
                if(count == rank){
                    return rec.get(0);
                }
                
            }            
        }
        return "NO NAME";
    }
    public String whatIsNameInYear(String name, int year, 
                                    int newYear, String gender){
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);         
        return newName;
    }
    public int yearOfHighestRank(String name, String gender){
        String fileName,yearString;
         
        int year= 0,prevYear = 0;
        int index = 0;
        int currRank = 0, prevRank=0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            
            fileName = f.getName();
            yearString = fileName.substring(3,7);
            year =Integer.parseInt(yearString);            
            
            FileResource fr = new FileResource(f); 
            
            currRank = getRankMany(fr, year, name, gender);            
            
            if(prevRank == 0){                
                prevRank=currRank;
                prevYear=year;                               
            }
            else{                
                if(currRank<prevRank){                       
                        prevYear = year;                       
                        prevRank=currRank;
                    }                
                else{                 
                        if(currRank > prevRank){                    
                            prevRank = prevRank;
                            prevYear = prevYear;
                        }
                        else{
                            if(currRank > prevRank && prevRank == prevRank){
                                prevRank=prevRank;
                            }
                            else{
                                return prevYear;
                            }
                        }
                    return prevYear;
                }
                return prevYear;
            }
            
        }
        return -1;
    }
    public double getAverageRank(String name, String gender){
        String fileName;
        String yearString;
        int year= 0;
        int currRank = 0;
        int rankSum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            count++;
            fileName = f.getName();
            yearString = fileName.substring(3,7);
            year =Integer.parseInt(yearString);            
            
            FileResource fr = new FileResource(f); 
            
            currRank = getRankMany(fr, year, name, gender);
            rankSum += currRank;
        }
        return (double)rankSum/count;
    }
    public int getTotalBirthRankedHigher(int num, String name, String gender){
        String fileName = "";
        String copyFile;
        String yearString;
        int year= 0;
        int currRank = 0;
        int prevRank = 0;
        int count = 0;
        int currBirth = 0;
        
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            
            fileName = f.getName();
            
            yearString = fileName.substring(3,7);
            year =Integer.parseInt(yearString);            
            
            FileResource fr = new FileResource(f);            
            
            currRank = getRankMany(fr, year, name, gender);
            
            if(currRank != 0 ){
                currBirth = getBirthNum (fr,name, gender);
            }         
            
            
        }
        System.out.println("currBirth"+currBirth);
        FileResource frs = new FileResource("data/yob"+num+".csv");
        
        for (CSVRecord rec : frs.getCSVParser(false)){
            int birth = Integer.parseInt(rec.get(2));
            String csvGender = rec.get(1);
            if( birth > currBirth && csvGender.equals(gender)){
                count += birth;
            }
        }
        return count;
    }
    public FileResource yearFile(int year){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        return fr;
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource("data/yob2014.csv");
        int num = getRank( 1960, "Emily", "F");
        String name = getName(1982, 450,"M");        
        
        System.out.println(num);
                
    }
}
