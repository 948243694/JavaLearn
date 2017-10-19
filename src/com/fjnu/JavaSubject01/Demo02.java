package com.fjnu.JavaSubject01;

import java.util.ArrayList;
import java.util.Scanner;

class Demo02 {
	public void test() {
		double temp;
		ArrayList<Double> score = new ArrayList<Double>();
		System.out.println("请输入一组数据(负数或大于100停止输入):"); 
		Scanner s = new Scanner(System.in); 
		temp=s.nextDouble();
		while(temp > 0 && temp <100) {
			score.add(temp);
			temp=s.nextDouble();
		}
		ToGradeScore(score);
	}

	/**
	 * 
	 * @param score  分数数据
	 */
	private void ToGradeScore(ArrayList<Double> score) {
		// TODO Auto-generated method stub
		for(int i=0;i<score.size();i++) {
			printScore(score.get(i));
		}
		
	}


	/**
	 * 输出分数划分的值
	 * @param d   分数
	 */
	private void printScore(double d) {
		// TODO Auto-generated method stub
		if(d>=90) {
			System.out.println(d+"-->优"); 
		}else if(d>=80){
			System.out.println(d+"-->良"); 
		}else if(d>=70){
			System.out.println(d+"-->中"); 
		}else if(d>=60){
			System.out.println(d+"-->及格"); 
		}else{
			System.out.println(d+"-->不及格"); 
		}
			
	}
}
