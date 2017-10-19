package com.fjnu.JavaSubject01;

public class Demo03 {
	private int num = 7;
	public void setNum(int i) {
		num=i;
	}
	public void test() {
		for(int i=0;i<num;i++) {
			for(int j=0;j<num;j++) {
				if(i<=num/2 && j<=num/2 && i+j>=num/2) {
					System.out.print("*");
				}else if(i>num/2 && j>num/2 && i+j<=9) {
					System.out.print("*");
				}else if((i>num/2  || j>num/2 ) && Math.abs(i-j)<=3 && i+j<=9){
					System.out.print("*");
				}else {
					System.out.print(" ");
				}	
			}
			System.out.println();
		}
	}
}
