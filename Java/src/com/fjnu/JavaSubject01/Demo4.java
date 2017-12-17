package com.fjnu.JavaSubject01;

class Demo4 {
	int a;
	int b;
	int c;
	public void test() {
		for(int i=100;i<1000;i++)
			if(isNum(i))
				System.out.println(i);
				
	}
	private boolean isNum(int i) {
		// TODO Auto-generated method stub
		int temp=i;
		a=i/100;
		i%=100;
		b=i/10;
		c=i%10;
		if(a*a*a+c*c*c+b*b*b==temp)
			return true;
		return false;
	}
}
