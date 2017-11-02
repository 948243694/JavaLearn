## 模拟实现FCFS（先来先服务）算法 ##

**FCFS类（先来先服务算法类）：**

```
package com.fjnu.JavaSubject03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.fjnu.JavaSubject03.SJF.TaskComparator;
/**
 * 先来先服务算法类:
 * static LinkedList<Task> TaskQueue : 用来存放从文件中读取的作业集的静态Linklist容器
 * private LinkedList<Task> waitQueue : 该对象的作业等待队列
 * private int sumTime : 当前的时间
 * private boolean isFree : 判断当前对象是否空闲，若为空闲则接收作业，若不空闲则处理作业
 * private Task now : 用于存储当前作业
 * private String name : 当前队列的名字。用于区分多个队列
 * @author 张永君
 *
 */
public class FCFS {
	static LinkedList<Task> TaskQueue = null;
	private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private boolean isFree;
	private Task now;
	private String name;
	
	/**
	 * 
	 * @return 返回当前等待队列的作业个数
	 */
	public int getWaitQueueSize(){
		return waitQueue.size();
	}

	/**
	 * 先来先服务算法类构造函数
	 * 若静态容器为空，则加载作业集
	 * 初始化队列名字以及是否空闲
	 * @param name 当前队列的名字
	 */
	public FCFS(String name) {
		if (this.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
		isFree = true;
	}

	/**
	 * 开始处理函数
	 * 静态容器非空是，该对象的等待队列从中获取作业
	 * 如果该对象空闲，则开始加载作业
	 * 否则，开始处理当前作业
	 */
	public void startUp() {
		if(TaskQueue.size()!=0){
			waitQueue.offer(TaskQueue.poll());
		}
		if (isFree) {
			begin();
		} else {
			work();
		}
		
	}

	/**
	 * 加载作业函数
	 * 等待队列非空时从中加载当前作业并将其从队列中删除
	 * 对象状态调整为非空闲
	 * 填充作业的值
	 */
	private void begin() {
		if(waitQueue.size()!=0){
			this.now = this.waitQueue.poll();
			isFree = false;
			now.setStartingTime(sumTime);
			now.setFinishingTime(now.getStartingTime() + now.getServiceTime());
			now.setTurnAroundTime(now.getFinishingTime() - now.getArrivalTime());
			now.setWeightTurnAround(now.getTurnAroundTime() * 1.0
					/ now.getServiceTime());
			System.out.println(this.name + ":" + now);
		}
	}

	/**
	 * 处理作业函数
	 * 当前时间加一
	 * 当当前时间等于完成时间时
	 * 对象状态改为空闲
	 */
	private void work() {
		// TODO Auto-generated method stub
		sumTime++;
		if (sumTime == now.getFinishingTime()) {
			isFree = true;
		}
	}

	/**
	 * 加载文件作业集队列
	 */
	private void loadTaskQueue() {
		this.TaskQueue = readFile();
	}

	/**
	 * 从文件中读取数据并填充作业集容器
	 * @return 作业集容器
	 */
	private LinkedList<Task> readFile() {
		System.out.println("readFile");
		LinkedList<Task> queue = new LinkedList<Task>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("D:\\JavaIOTest\\serviceTime.txt");
			br = new BufferedReader(fr);
			String line = "";
			String[] infos = null;
			while ((line = br.readLine()) != null) {
				Task temp = new Task();
				infos = line.split(" ");
				temp.setTaskID(Integer.parseInt(infos[0]));
				temp.setArrivalTime(Integer.parseInt(infos[1]));
				temp.setServiceTime(Integer.parseInt(infos[2]));
				// System.out.println(temp);
				queue.offer(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return queue;

	}

}
```

----------

**SJF（短作业优先算法类）：**

```
package com.fjnu.JavaSubject03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 短作业优先算法类
 * 除了多出一个排序的内部类，其他都与FCFS一致
 * @author 张永君
 *
 */
public class SJF {
	
	static LinkedList<Task> TaskQueue = null;
	private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private boolean isFree;
	private Task now;
	private String name;
	
	public int getWaitQueueSize(){
		return waitQueue.size();
	}
	/**
	 * 内部类TaskComparator定义等待队列的排序规矩
	 * @author 张永君
	 *
	 */
	public class TaskComparator implements Comparator<Task> {

		@Override
		public int compare(Task o1, Task o2) {
			// TODO Auto-generated method stub
			return (o1.getServiceTime()-o2.getServiceTime());
		}
		
	}

	public SJF(String name) {
		if (this.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
		isFree = true;
	}

	public void startUp() {
		if(TaskQueue.size()!=0){
			waitQueue.offer(TaskQueue.poll());
		}
		if (isFree) {
			begin();
		} else {
			work();
		}
	}
	
	

	private void begin() {
		Collections.sort(waitQueue, new TaskComparator());
		if(waitQueue.size()!=0){
			this.now = this.waitQueue.poll();
			isFree = false;
			now.setStartingTime(sumTime);
			now.setFinishingTime(now.getStartingTime() + now.getServiceTime());
			now.setTurnAroundTime(now.getFinishingTime() - now.getArrivalTime());
			now.setWeightTurnAround(now.getTurnAroundTime() * 1.0
					/ now.getServiceTime());
			System.out.println(this.name + ":" + now);
		}
	}

	private void work() {
		// TODO Auto-generated method stub
		sumTime++;
		if (sumTime == now.getFinishingTime()) {
			isFree = true;
		}

	}

	private void loadTaskQueue() {
		this.TaskQueue = readFile();
	}

	private LinkedList<Task> readFile() {
		System.out.println("readFile");
		LinkedList<Task> queue = new LinkedList<Task>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("D:\\JavaIOTest\\serviceTime.txt");
			br = new BufferedReader(fr);
			String line = "";
			String[] infos = null;
			while ((line = br.readLine()) != null) {
				Task temp = new Task();
				infos = line.split(" ");
				temp.setTaskID(Integer.parseInt(infos[0]));
				temp.setArrivalTime(Integer.parseInt(infos[1]));
				temp.setServiceTime(Integer.parseInt(infos[2]));
				// System.out.println(temp);
				queue.offer(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return queue;

	}

}

```

----------

**SJF_TP（短作业优先_占位算法实现类）：**

```
package com.fjnu.JavaSubject03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.fjnu.JavaSubject03.SJF.TaskComparator;

/**
 * 短作业优先算法类
 * @author 张永君
 *
 */
public class SJF_TP {
	static LinkedList<Task> TaskQueue = null;
	private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private Task now;
	private String name;
	
	public int getWaitQueueSize(){
		return waitQueue.size();
	}
	
	public class TaskComparator implements Comparator<Task> {

		@Override
		public int compare(Task o1, Task o2) {
			// TODO Auto-generated method stub
			return (o1.getRemainingTine()-o2.getRemainingTine());
		}
		
	}

	public SJF_TP(String name) {
		if (this.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
	}

	/**
	 * 静态作业集容器非空时，将其中一个作业添加到等待队列中，并准备处理队列
	 */
	public void startUp() {
		if(TaskQueue.size()!=0){
			waitQueue.offer(TaskQueue.poll());
		}
		begin();
	}
	
	

	/**
	 * 准备处理函数
	 * 每次准备处理时先对等待队列进行排序，剩余时间最少的作业放在前头
	 * 如果等待队列非空则从前头获取一个作业
	 * 如果剩余时间等于服务时间（作业之前未被处理过），则将当前时间设置它的开始时间
	 * 最后开始处理
	 */
	private void begin() {
		Collections.sort(waitQueue, new TaskComparator());
		if(waitQueue.size()!=0){
			this.now = this.waitQueue.peek();
			if(now.getServiceTime()==now.getRemainingTine())
				now.setStartingTime(sumTime);
			work();
		}
	}

	/**
	 * 开始处理函数
	 * 剩余时间减一，当前时间加一
	 * 如果剩余时间为零，则填充作业其他时间信息
	 * 输出作业并将其从等待队列移除
	 */
	private void work() {
		// TODO Auto-generated method stub
		now.setRemainingTine(now.getRemainingTine()-1);
		sumTime++;
		if(now.getRemainingTine()==0) {
			now.setFinishingTime(sumTime);
			now.setTurnAroundTime(now.getFinishingTime() - now.getArrivalTime());
			now.setWeightTurnAround(now.getTurnAroundTime() * 1.0
					/ now.getServiceTime());
			System.out.println(this.name + ":" + now);
			waitQueue.remove();
		}

	}

	private void loadTaskQueue() {
		this.TaskQueue = readFile();
	}

	private LinkedList<Task> readFile() {
		System.out.println("readFile");
		LinkedList<Task> queue = new LinkedList<Task>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("D:\\JavaIOTest\\serviceTime.txt");
			br = new BufferedReader(fr);
			String line = "";
			String[] infos = null;
			while ((line = br.readLine()) != null) {
				Task temp = new Task();
				infos = line.split(" ");
				temp.setTaskID(Integer.parseInt(infos[0]));
				temp.setArrivalTime(Integer.parseInt(infos[1]));
				temp.setServiceTime(Integer.parseInt(infos[2]));
				temp.setRemainingTine(Integer.parseInt(infos[2]));
				// System.out.println(temp);
				queue.offer(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return queue;

	}

}
```

----------

**Task类**

```
package com.fjnu.JavaSubject03;

import java.text.DecimalFormat;
/**
 * Task对象类
 * @author 张永君
 *
 */
public class Task {
	private int taskID;
	private int arrivalTime; //到达时间
	private int serviceTime; //服务时间
	private int startingTime; //开始时间
	private int finishingTime; //完成时间=开始时间+服务时间
	private int turnAroundTime; //周转时间=完成时间-达到时间
	private double weightTurnAround; //带权周转时间=周转时间/服务时间
	private int RemainingTine;
	public int getTaskID() {
		return taskID;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public int getStartingTime() {
		return startingTime;
	}
	public int getFinishingTime() {
		return finishingTime;
	}
	public int getTurnAroundTime() {
		return turnAroundTime;
	}
	public double getWeightTurnAround() {
		return weightTurnAround;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public void setStartingTime(int startingTime) {
		this.startingTime = startingTime;
	}
	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}
	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}
	public void setWeightTurnAround(double weightTurnAround) {
		this.weightTurnAround = weightTurnAround;
	}
	public int getRemainingTine() {
		return RemainingTine;
	}
	public void setRemainingTine(int remainingTine) {
		RemainingTine = remainingTine;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return "Task [taskID=" + taskID + ", arrivalTime=" + arrivalTime
				+ ", serviceTime=" + serviceTime + ", startingTime="
				+ startingTime + ", finishingTime=" + finishingTime
				+ ", turnAroundTime=" + turnAroundTime + ", weightTurnAround="
				+ df.format(weightTurnAround) + "]";
	}
	


}
```

**Main函数：**

```
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
```
