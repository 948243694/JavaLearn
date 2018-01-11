package com.fjnu.JavaSubject04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class BufferTest {
	public static void main(String [] args) throws IOException{
		long sysDateStart = System.currentTimeMillis();
		IOchar();
		long sysDateEnd = System.currentTimeMillis();
		System.out.println("use Time:"+(sysDateEnd-sysDateStart));
		long sysDateStart1 = System.currentTimeMillis();
		IOBufferedChar();
		long sysDateEnd1 = System.currentTimeMillis();
		System.out.println("use Time with Buffer:"+(sysDateEnd1-sysDateStart1));
	}
	
	public static void IOchar() throws IOException{
		File inFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\src");
		File outFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\dest");
		Reader finS = new FileReader(inFile);
		Writer foutS = new FileWriter(outFile);
		int ch;
		while((ch = finS.read())!=-1) {
			foutS.write(ch);
		}
		foutS.close();
		finS.close();
	}
	
	public static void IOBufferedChar() throws IOException{
		File inFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\src");
		File outFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\dest");
		Reader finS = new FileReader(inFile);
		Writer foutS = new FileWriter(outFile);
		BufferedReader bfinS = new BufferedReader(finS);
		BufferedWriter bfoutS = new BufferedWriter(foutS);
		int ch;
		while((ch = bfinS.read())!=-1) {
			bfoutS.write(ch);
		}
		bfoutS.close();
		bfinS.close();
		foutS.close();
		finS.close();
	} 
}
