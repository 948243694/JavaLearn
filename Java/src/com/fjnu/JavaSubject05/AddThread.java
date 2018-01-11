package com.fjnu.JavaSubject05;

public class AddThread {

	public static int sum;
	private static Thread threadA;
	private static Thread threadB;
	private static Thread threadC;
	private static Thread threadD;
	
	public static void main(String[] args) {
		threadA = new Thread(new Runnable(){
			int count = 1;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int subsum=0;
				while(count<=25){
					subsum += count;
					count++;
					try {
						Thread.sleep(100);
						threadB.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("threadA:"+ subsum );
				sum+=subsum;
				System.out.println("sum:"+sum);
			}
		});
		threadA.start();
		threadB = new Thread(new Runnable(){
			int count = 26;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int subsum=0;
				while(count<=50){
					subsum += count;
					count++;
					try {
						Thread.sleep(100);
						threadC.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("threadB:"+ subsum );
				sum+=subsum;
			}
		});
		threadB.start();
		threadC = new Thread(new Runnable(){
			int count = 51;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int subsum=0;
				while(count<=75){
					subsum += count;
					count++;
					try {
						Thread.sleep(100);
						threadD.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("threadC:"+ subsum );
				sum+=subsum;
			}
		});
		threadC.start();
		threadD = new Thread(new Runnable(){
			int count = 76;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int subsum=0;
				while(count<=100){
					subsum += count;
					count++;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("threadD:"+ subsum );
				sum+=subsum;

			}
		});
		threadD.start();
		
	}

}
