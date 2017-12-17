package com.fjnu.JavaSubject01;

class Demo01 {
	/**
	 * ����ӹ�Ԫ1990�굽2007�������������ţ�ÿ���������Ż�һ��
	 */
	public void test() {
		int startYear = 1990;
		int endYear = 2007;
		int mark=0;
		for(int i=startYear;i<=endYear;i++) {
			if(isLeap(i)){
				printLeapYear(i,++mark);
			}
		}
	}
	/**
	 * �����������
	 * @param i   ��������
	 * @param mark    ����Ƿ���Ҫ����
	 */
	private void printLeapYear(int i, int mark) {
		System.out.print(i+"  ");
		if(mark==2){
			System.out.println();
			mark=0;
		}
		
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * @param i   �жϵ����
	 * @return   �Ż�Booleanֵ
	 */
	private boolean isLeap(int i) {
		// TODO Auto-generated method stub
		if((i%4==0&&i%100!=0)||i%400==0){
			return true;
		}
		return false;
	}
}
