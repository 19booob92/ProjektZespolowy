package com.pwr.Editor;



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PackageCoder {
	static private int key=9284; 
	public static void main(String[] argv)
	{
		//decodeAllFilesInDirectoryExceptSound("./doZakodowania/");
		codeAllFilesInDirectoryExceptSound("./doZakodowania/");
	}
	//Zakodowuje wszystkie pliki w wybranym folderze
	static public void codeAllFilesInDirectory(String src)
	{
		File folder = new File(src);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        codeFile(file.getPath());
		        
		    }
		}
	}
	//rozkodowuje wszystkie pliki we wskazanym folderze
	static public void decodeAllFilesInDirectory(String src)
	{
		File folder = new File(src);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        decodeFile(file.getPath());
		    }
		}
	}
	
	//Zakodowuje wszystkie pliki w wybranym folderze pomijajac dzwieki mp3 wav i ogg
		static public void codeAllFilesInDirectoryExceptSound(String src)
		{
			File folder = new File(src);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
			    if (file.isFile()) 
			    if(!file.getName().substring(file.getName().length()-3, file.getName().length()).toLowerCase().equals("mp3") &&
			    		!file.getName().substring(file.getName().length()-3, file.getName().length()).toLowerCase().equals("wav") &&
			    		!file.getName().substring(file.getName().length()-3, file.getName().length()).toLowerCase().equals("ogg")
			    		){
			        System.out.println("Coded: "+file.getName());
			        codeFile(file.getPath());
			        
			    }
			}
		}
		//rozkodowuje wszystkie pliki we wskazanym folderze pomijajac dzwieki mp3 wav i ogg
		static public void decodeAllFilesInDirectoryExceptSound(String src)
		{
			File folder = new File(src);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
			    if (file.isFile()) 
			    	  if(!file.getName().substring(file.getName().length()-3, file.getName().length()).toLowerCase().equals("mp3") &&
					    		!file.getName().substring(file.getName().length()-3, file.getName().length()).toLowerCase().equals("wav") &&
					    		!file.getName().substring(file.getName().length()-3, file.getName().length()).toLowerCase().equals("ogg")
					    		){
			        System.out.println("Encoded: "+file.getName());
			        decodeFile(file.getPath());
			    }
			}
		}
		
		
	static public void codeFile(String src)
	{
		byte[] fileBytes;
		try {
			fileBytes = read(new File(src));
			for(int n=0;n<fileBytes.length;n++)
			{
				fileBytes[n]+=key;
			}
			write(src, fileBytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static public void decodeFile(String src)
	{
		byte[] fileBytes;
		try {
			fileBytes = read(new File(src));
			for(int n=0;n<fileBytes.length;n++)
			{
				fileBytes[n]-=key;
			}
			write(src, fileBytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static public ByteArrayInputStream encodeFile(String src)
	{
		byte[] fileBytes;
		ByteArrayInputStream bis = null;
		try {
			fileBytes = read(new File(src));
			for(int n=0;n<fileBytes.length;n++)
			{
				fileBytes[n]-=key;//config.xmlcoded
			}
			bis = new ByteArrayInputStream(fileBytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bis;
	}
	static public byte[] read(File file) throws IOException, FileNotFoundException {
	    byte []buffer = new byte[(int) file.length()];
	    InputStream ios = null;
	    try {
	        ios = new FileInputStream(file);
	        if ( ios.read(buffer) == -1 ) {
	            throw new IOException("end of file");
	        }        
	    } finally { 
	        try {
	             if ( ios != null ) 
	                  ios.close();
	        } catch ( IOException e) {
	        }
	    }

	    return buffer;
	}
	static public void write(String path, byte[] fileBytes)
	{
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		    stream.write(fileBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
