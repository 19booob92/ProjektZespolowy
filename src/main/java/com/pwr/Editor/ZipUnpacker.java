package com.pwr.Editor;

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
	private ArrayList<String> fileList;
	private boolean unzipped;
	
	public ZipUnpacker(String inputZip)
	{
		this.inputZip=inputZip;
		outputFolder="temp";
		fileList = new ArrayList();
		unzipped=false;
	}
	
	 public void unZip(){
		 
	     byte[] buffer = new byte[1024];
	     unzipped=true;
	     try{
	 
	    	folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	 
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(inputZip));
	    	ZipEntry ze = zis.getNextEntry();
	 
	    	while(ze!=null){
	 
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	           fileList.add(outputFolder + File.separator + fileName);
	 
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
		 for(int i=0;i<fileList.size();i++)
		 {
			 File file = new File(fileList.get(i));
			 file.delete();
		 }
		 if(folder.exists())
		 {
			 folder.delete();
		 }
	 }
}
