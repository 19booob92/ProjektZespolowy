package com.pwr.Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUnpacker {
	private String outputFolder;
	private String inputZip;
	private File folder;


	
	public ZipUnpacker(String inputZip)
	{
		this.inputZip=inputZip;
		outputFolder="temp";
	}
	
	 public void unZip(){
		 
	     byte[] buffer = new byte[1024];
	     try{
	 
	    	folder = new File(outputFolder);
	    	if(folder.exists())
	    	{
	    		File [] fileList = folder.listFiles();
	    		for(int i=0;i<fileList.length;i++)
	    		{
	    			fileList[i].delete();
	    		}
	    		folder.delete();
	    	}
	    		folder.mkdir();
	 
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(inputZip));
	    	ZipEntry ze = zis.getNextEntry();
	 
	    	while(ze!=null){
	 
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	 
	            new File(newFile.getParent()).mkdirs();
	 
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	 
	        zis.closeEntry();
	    	zis.close();
	 
	 
	    }catch(IOException ex){
	       ex.printStackTrace(); 
	    }
	   }
	 
	 public void close()
	 {
		 if(folder.exists())
		 {
	    	File [] fileList = folder.listFiles();
	    	for(int i=0;i<fileList.length;i++)
	    	{
	    		fileList[i].delete();
	    	}
			 folder.delete();
		 }
	 }
}
