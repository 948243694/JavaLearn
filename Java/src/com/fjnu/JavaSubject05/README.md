## JAVA���߳� ##

> ��ʵ�����еĵ���ģʽ���и��죬ʹ��֧�ֶ��̣߳��������̰߳�ȫ�ġ�

 - Դ����

```
package com.fjnu.JavaSubject05;

import com.fjnu.JavaSubject02.ChocolateBoiler;



public class ThreadTest1 implements Runnable {
	private String name;

	public ThreadTest1(String name) {
		this.name = name;

	}

	public static void main(String[] args) {
		Thread test1 = new Thread(new ThreadTest1("test1"));
		Thread test2 = new Thread(new ThreadTest1("test2"));
		Thread test3 = new Thread(new ThreadTest1("test3"));
		Thread test4 = new Thread(new ThreadTest1("test4"));
		test1.start();
		test2.start();
		test3.start();
		test4.start();
	}

	public void run() {
		ChocolateBoiler a =ChocolateBoiler.getChocolateBoiler();
		System.out.println(a.isEmpty()+","+a.isBoiled());
		a.fill();
		System.out.println(a.isEmpty()+","+a.isBoiled());
		a.boil();
		System.out.println(a.isEmpty()+","+a.isBoiled());
		a.drain();
		System.out.println(a.isEmpty()+","+a.isBoiled());
	}
}
```


----------



> ����4���̷ֶ߳����1~100
�߳�1����1~25֮�ͣ��߳�2����26~50֮�ͣ��Դ�����
Ҫ���߳�1���֮��ִ���߳�2��֮��ִ���߳�3�����ִ���߳�4
��ӡÿ����ͽ�����Լ������ܽ�������ֱ��ӡ��һ����ͽ�����ڶ�����ͽ������������ͽ�������Ķ���ͽ�������յ���ͽ��


 - Դ����

```
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
```

- �����ͼ


![](https://github.com/948243694/JavaLearn/blob/master/src/com/fjnu/JavaSubject05/AddThread.png)

