# JavaLearn
## JavaSubject01(实验二)
***
### 判断闰年
1.题目:

编写Java程序，输出从公元1990年到2007年所有闰年的年号，每输出两个年号换一行。
判断年号是否为闰年的条件是：
（1）若年号能被4整除，而不能被100整除，则是闰年；
（2）若年号能被400整除也是闰年。

2.代码：
```
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
```
***
### 百分制成绩转化为等级成绩
1.题目:

实现方法ToGradeScore，将百分制成绩转化为等级成绩。要求对一组数据，实现批量转化。
等级与百分制对照
优：[90,100]
良：[89,80]
中：[79,70]
及格：[69,60]
不及格：[0,59]

2.代码：
```
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
```
***
### 打印图案
1.题目:

利用for循环编写一个程序，将如下图案分别打印输出。
```
   *
  ***
 *****
*******
 *****
  ***
   *
```

2.代码：
```
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
```
***
### 水仙花数
1.题目:

编写程序找出所有的水仙花数；水仙花数是三位数，它的各位数字的立方和等于这个三位数本身。

2.代码：
```
class Demo4 {
	int a;
	int b;
	int c;
	public void test() {
		for(int i=100;i<1000;i++)
			if(isNum(i))
				System.out.println(i);
				
	}
	/**
	 * 判断是否为水仙花数
	 * @param i   被判断的数字
	 * @return   
	 */
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
```

## JavaSubject02(实验三)
### 单例模式——使用单例模式完成下面描述的类
1.题目：

Choc-O-Holic公司有一个巧克力锅炉，用来把巧克力和牛奶融合在一起生产巧克力棒。定义这个巧克力锅炉类为ChocolateBoiler
ChocolateBoiler有两个私有的成员变量，empty和boiled，用来判断锅炉是否为空，以及锅炉内混合物是否已煮沸。注意两个成员变量恰当的初始值。
private boolean empty;
private boolean boiled;
ChocolateBoiler有三个方法来控制锅炉生产巧克力棒。
public void fill() {…} 向锅炉填满巧克力和牛奶的混合物。首先要判断锅炉是否为空，只有空的锅炉才能填充巧克力和牛奶（填充过程打印一条语句即可）。填充之后empty为false
public void boil() {…} 将炉内煮沸。首先判断标志位，只有锅炉是满的，并且没有煮过，才能进行该操作（煮沸操作打印一条语句即可）。煮沸后boiled标志位设置为true。
public void drain() {…} 排出煮沸的巧克力和牛奶。首先要进行标志位判断，只有锅炉是满的，并且锅炉已经煮沸之后，才能排出混合物（排出混合物的动作打印一条语句即可），排出混合物之后设置empty为true。
isEmpty和isBoiled方法来获取empty和boiled标志位的值

2.代码:
```
public class ChocolateBoiler {
	private static ChocolateBoiler onlyChocolateBoiler;
	private boolean empty;
	private boolean boiled;
	private ChocolateBoiler() {
		empty = true;
		boiled =false;
	}
	public static ChocolateBoiler getChocolateBoiler() {
		if(onlyChocolateBoiler == null) {
			onlyChocolateBoiler = new ChocolateBoiler();
		}
		return onlyChocolateBoiler;
	}
	
	public void fill() {
		if(empty) {
			System.out.println("正在向锅炉中添加巧克力和牛奶混合物");
			empty = false;
		}
		else {
			System.out.println("锅炉已满，填装失败");
		}
	}
	
	public void boil() {
		if(!empty && !boiled) {
			System.out.println("正在将锅炉煮沸");
			boiled = true;
		}
	}
	
	public void drain() {
		if(!empty && boiled) {
			System.out.println("正在排出煮沸的巧克力和牛奶混合物");
			boiled = false;
			empty = true;
		}
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return empty;
	}
	
	public boolean isBoiled() {
		// TODO Auto-generated method stub
		return boiled;
	}	
}
```




