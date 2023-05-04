//package src;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator{

    

    //ArrayList<Float> cost= new ArrayList<>();  
    //cost.add(1.0f);  

    public static void main(String[] args) {
        //Graph g= new Graph();

        
      Random costs= new Random();

      try{

        //String vertexName= "vertex"+Integer.toString(w)

        FileWriter writer = new FileWriter("DatasetExample", true ) ;
        for(int i=0; i<50;i++){
            //if(i==i){continue;}
            for(int j=0; j<50 ; j++){
                if(i==j){
                    continue;
                }
                if(costs.nextInt(2)==0){
                    int weight = costs.nextInt(80) + 1;
                       // vertices.get(v).add(new Path(vi, weight));
                       String twoVertices= "vertex"+i+" "+"vertex"+j;
                       writer.write(twoVertices+" "+weight+"\n");
                       //writer.close();
                }
            }//writer.close();
        }writer.close();}//writer.close
        catch(IOException e){System.out.println("An error occured");
    e.printStackTrace();}  
    
    }

    
    
        
      
    
    
}