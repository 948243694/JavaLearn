package com.fjnu.JavaSubject01;

import java.util.ArrayList;
import java.util.Scanner;

class Demo02 {
	public void test() {
		double temp;
		ArrayList<Double> score = new ArrayList<Double>();
		System.out.println("������һ������(���������100ֹͣ����):"); 
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
	 * @param score  ��������
	 */
	private void ToGradeScore(ArrayList<Double> score) {
		// TODO Auto-generated method stub
		for(int i=0;i<score.size();i++) {
			printScore(score.get(i));
		}
		
	}


	/**
	 * ����������ֵ�ֵ
	 * @param d   ����
	 */
	private void printScore(double d) {
		// TODO Auto-generated method stub
		if(d>=90) {
			System.out.println(d+"-->��"); 
		}else if(d>=80){
			System.out.println(d+"-->��"); 
		}else if(d>=70){
			System.out.println(d+"-->��"); 
		}else if(d>=60){
			System.out.println(d+"-->����"); 
		}else{
			System.out.println(d+"-->������"); 
		}
			
	}
}
