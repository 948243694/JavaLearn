## JAVA多线程 ##

> 对实验三中的单例模式进行改造，使其支持多线程，并且是线程安全的。

 - 源代码

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



> 利用4个线程分段求和1~100
线程1计算1~25之和；线程2计算26~50之和；以此类推
要求线程1完成之后执行线程2，之后执行线程3，最后执行线程4
打印每段求和结果，以及最后的总结果。即分别打印第一段求和结果，第二段求和结果，第三段求和结果，第四段求和结果，最终的求和结果


 - 源代码

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

- 结果截图


![](https://github.com/948243694/JavaLearn/blob/master/src/com/fjnu/JavaSubject05/AddThread.png)

