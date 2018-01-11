package com.fjnu.JavaSubject04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.fjnu.JavaSubject03.Task;
import com.fjnu.JavaSubject03.SJF.TaskComparator;

public class FlattingStudent {
	
	static public ArrayList<Student> studentQueue = new ArrayList<Student>();
	

	
	public static void main(String[] args) {
		//inflattenStudent(Student.studentQueue);
		readStudent();
		SerializableStudent();
		inSerializableStudent();
		
	}
	
	
	

	private static void inSerializableStudent(){
		// TODO Auto-generated method stub
		File inFile = new File("Student.obj");
		FileInputStream finS = null;
		ObjectInputStream oin = null;
			try {
				finS = new FileInputStream(inFile);
				oin = new ObjectInputStream(finS);
				int cout = 0;
				Student get = null;
				while(( get =(Student)oin.readObject())!=null) {
						System.out.print(++cout+":  ");
						System.out.println(get);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
			}
	}




	private static void readStudent() {
		// TODO Auto-generated method stub
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\list");
				br = new BufferedReader(fr);
				String line = "";
				String[] infos = null;
				while ((line = br.readLine()) != null) {
					Student temp = new Student();
					infos = line.split(" ");
					temp.setStudentID(infos[0]);
					temp.setName(infos[1]);
					temp.setSex(infos[2]);
					studentQueue.add(temp);		
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					br.close();
					fr.close();
					Collections.sort(studentQueue, new StudentComparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

	}




	private static void SerializableStudent() {
		// TODO Auto-generated method stub
		File outFile = new File("Student.obj");
		FileOutputStream foutS = null;
		ObjectOutputStream oout = null;
		try {
			foutS = new FileOutputStream(outFile);
			oout = new ObjectOutputStream(foutS);
			for(Student temp : studentQueue) {
				oout.writeObject(temp);	
				oout.flush();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
