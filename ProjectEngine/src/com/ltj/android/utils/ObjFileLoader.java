package com.ltj.android.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.res.AssetManager;

public class ObjFileLoader {


    private float[] verticesArr;
    private float[] normalsArr;
    private short[] indicesArray;
    


    private ArrayList<Float> vertices= new ArrayList<Float>();
    private ArrayList<Float> normals = new ArrayList<Float>();

    private ArrayList<Short> indices= new ArrayList<Short>();

    
    public ObjFileLoader(String path, AssetManager asset, boolean packed) throws IOException{
        InputStream file= asset.open(path);

         BufferedReader read= new BufferedReader(new InputStreamReader(file));

         String currentLine;


         while((currentLine= read.readLine())!=null){

             if(currentLine.startsWith("v ")){
             
                vertices.add( Float.valueOf(currentLine.split(" ")[2]));
             
               vertices.add( Float.valueOf(currentLine.split(" ")[3]));
             
                 vertices.add( Float.valueOf(currentLine.split(" ")[4]));
           
             } else if(currentLine.startsWith("f ")){
          
                 indices.add( (short) Short.parseShort(currentLine.split(" ")[1].split("/")[0]));
                 
                 indices.add( (short) Short.parseShort(currentLine.split(" ")[2].split("/")[0]));
                 
                 indices.add( (short) Short.parseShort(currentLine.split(" ")[3].split("/")[0]));

             } else if(currentLine.startsWith("vn ")){
                 
                 normals.add(  Float.valueOf(currentLine.split(" ")[2]));
             
                 normals.add( Float.valueOf(currentLine.split(" ")[3]));
             
                 normals.add( Float.valueOf(currentLine.split(" ")[4]));

             }
            




         }
         if (packed){
        	 verticesArr = new float[vertices.size() + normals.size()];
        	 int counter = 0;
    		 Iterator<Float> it1 = vertices.iterator();
    		 Iterator<Float> it2 = normals.iterator();
    		 Iterator<Float> currentIt = it1;
        	 for (int i = 0; i < verticesArr.length; i++){
        		 
        		 verticesArr[i] = currentIt.next();
        		 
        		 counter++;
        		 if (counter == 3){
        			 counter = 0;
        			 if (currentIt == it1){
        				 currentIt = it2;
        			 } else {
        				 currentIt = it1;
        			 }
        		 }
        	 }
         } else {
        	 //create two arrays
        	 verticesArr= new float[vertices.size()];
        	 for (int i = 0; i < verticesArr.length;i++){
        		 verticesArr[i] = vertices.get(i);
        	 }
        	 normalsArr= new float[normals.size()];
        	 for (int i = 0; i < normalsArr.length;i++){
        		 normalsArr[i] = normals.get(i);
        	 }
         }
         indicesArray= new short[indices.size()];
         for (int i = 0; i < indicesArray.length;i++){
        	//obj starts with 1 so subtract 1
        	 indicesArray[i] = (short) (indices.get(i)-1);
         }
         

       




    }

	public float[] getVerticesArr() {
		return verticesArr;
	}

	public float[] getNormalsArr() {
		return normalsArr;
	}

	public short[] getIndicesArray() {
		return indicesArray;
	}



}