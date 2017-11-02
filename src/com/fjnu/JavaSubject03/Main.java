package com.fjnu.JavaSubject03;



public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		//TODO Auto-generated method stub
		SJF_TP test = new SJF_TP("Test1");
		SJF_TP test2 = new SJF_TP("Test2");
		//FCFS test = new FCFS("Test1");
		//FCFS test2 = new FCFS("Test2");
		do{
			test.startUp();
			test2.startUp();
		}while(test.getWaitQueueSize()!=0 ||test2.getWaitQueueSize()!=0 || SJF_TP.TaskQueue.size()!=0);
		
		
	}

}
