package com.fjnu.JavaSubject01;

class Demo01 {
	/**
	 * 输出从公元1990年到2007年所有闰年的年号，每输出两个年号换一行
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
	 * 输出闰年的年份
	 * @param i   输出的年份
	 * @param mark    标记是否需要换行
	 */
	private void printLeapYear(int i, int mark) {
		System.out.print(i+"  ");
		if(mark==2){
			System.out.println();
			mark=0;
		}
		
	}

	/**
	 * 判断是否为闰年
	 * @param i   判断的年份
	 * @return   放回Boolean值
	 */
	private boolean isLeap(int i) {
		// TODO Auto-generated method stub
		if((i%4==0&&i%100!=0)||i%400==0){
			return true;
		}
		return false;
	}
}
