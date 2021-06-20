
/**
 * Write a description of findGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class findGeneWhile {
    public String findGene(String dna){
        int startIndex = dna.indexOf("atg");
        int currIndex = dna.indexOf("taa",startIndex+3);
        
        while(currIndex != -1){
            if((currIndex-startIndex)%3 ==0){
                return dna.substring(startIndex,currIndex+3);
            }
            else{
                currIndex = dna.indexOf("taa",currIndex +1);
            }
        }
        return "";
    }
    public void test(){
        String gene = findGene("aatgcggtaattaatcg");
        System.out.println(gene);     
        
    }
}
