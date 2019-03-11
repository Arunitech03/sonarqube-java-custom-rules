package org.sonar.samples.java.checks;

import java.io.File;
import java.io.IOException;

public class SonarTestFile {
	static int b4 = 12141;
	 public static void main(String[] args)
	   {
		 int aid=10;
		 char s23 ='q';
		 long n = 112423452;
		 
		 try {
	     File fileid = new File("C:\\newfile.txt");
	     /*If file gets created then the createNewFile() 
	      * method would return true or if the file is 
	      * already present it would return false
	      */
             boolean fvar = fileid.createNewFile();
	     if (fvar){
	          System.out.println("File has been created successfully");
	     }
	     else{
	          System.out.println("File already present at the specified location");
	     }
    	} catch (IOException e) {
    		System.out.println("Exception Occurred:");
	        e.printStackTrace();
	  }
		 Test tid = new Test();
		 tid.Meth1();
		 
		 SonarTestFile s=new SonarTestFile();
		 System.out.println("S value:" +s.toString());
	   }
}
class Test{
	void Meth1()
	{
		System.out.println("Meth1");
	}
}