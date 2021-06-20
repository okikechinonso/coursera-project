
/**
 * Write a description of AllCodonBreak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodonBreak {
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
        return -1;
    }
    public String findGene(String dna,int where){
        int startIndex = dna.indexOf("atg",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"taa");
        int tagIndex = findStopCodon(dna,startIndex,"tag");
        int tgaIndex = findStopCodon(dna,startIndex,"tga");
        
        //look for minimum index
       // int temp = Math.min(taaIndex,tgaIndex);
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
        
        return dna.substring(startIndex,minIndex+3);
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
               break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                            currentGene.length();
        }
    }
    public void test(){
         printAllGenes("atggggtttaaataataatag");
        
    }
}
