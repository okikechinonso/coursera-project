/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    
    // this print total number of births, girls and boys
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int countB=0, countG=0;
        
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                countB++;
            }
            else {
                totalGirls += numBorn;
                countG++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls
        +" The number of boys burn is " +countG);
        System.out.println("male boys = " + totalBoys
        + " The number of boys burn is " +countB);
    }
    // this get the rank name
    public int getRank(int year, String name, String gender){
        int genderCount = 0;
        int totalBirth = 0;
        int rank = 1;
        FileResource fr = new FileResource("data/yob"+year+".csv");
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
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
        int count = getRank(2014,"Jacob","M");
        System.out.println(count);
    }
}
