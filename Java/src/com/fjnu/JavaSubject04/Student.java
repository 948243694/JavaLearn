package com.fjnu.JavaSubject04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import com.fjnu.JavaSubject03.Task;

public class Student implements Serializable {
	private static final long serialVersionUID = 1234L;
	private String studentID;
	private String name;
	private String sex;
	
	public String getStudentID() {
		return studentID;
	}
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", name=" + name + ", sex="
				+ sex + "]";
	}
	
	
}
