//package src;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
/*
 * generates the datasets of vertex, edge and the edge cost
 * uses a for loop to create the vertex names
 * randomly joins the two vertices using the random function
 * 
 */
public class DataGenerator{

    

    //ArrayList<Float> cost= new ArrayList<>();  
    //cost.add(1.0f);  
    /*
     * main class
     * uses random function to randomly join edges
     */
    public static void main(String[] args) {
        //Graph g= new Graph();

        
      Random costs= new Random();

      try{

        //String vertexName= "vertex"+Integer.toString(w)

        FileWriter writer = new FileWriter("Dataset.10.20.txt") ;
        for(int i=0; i<11;i++){
            //if(i==i){continue;}
            for(int j=0; j<11 ; j++){
                if(i==j){
                    continue;
                }
                if(costs.nextInt(2)==0){
                    int weight = costs.nextInt(20) + 1;
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