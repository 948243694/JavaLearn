## ģ��ʵ��FCFS�������ȷ����㷨 ##

**FCFS�ࣨ�����ȷ����㷨�ࣩ��**

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
 * �����ȷ����㷨��:
 * static LinkedList<Task> TaskQueue : ������Ŵ��ļ��ж�ȡ����ҵ���ľ�̬Linklist����
 * private LinkedList<Task> waitQueue : �ö������ҵ�ȴ�����
 * private int sumTime : ��ǰ��ʱ��
 * private boolean isFree : �жϵ�ǰ�����Ƿ���У���Ϊ�����������ҵ����������������ҵ
 * private Task now : ���ڴ洢��ǰ��ҵ
 * private String name : ��ǰ���е����֡��������ֶ������
 * @author ������
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
	 * @return ���ص�ǰ�ȴ����е���ҵ����
	 */
	public int getWaitQueueSize(){
		return waitQueue.size();
	}

	/**
	 * �����ȷ����㷨�๹�캯��
	 * ����̬����Ϊ�գ��������ҵ��
	 * ��ʼ�����������Լ��Ƿ����
	 * @param name ��ǰ���е�����
	 */
	public FCFS(String name) {
		if (this.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
		isFree = true;
	}

	/**
	 * ��ʼ������
	 * ��̬�����ǿ��ǣ��ö���ĵȴ����д��л�ȡ��ҵ
	 * ����ö�����У���ʼ������ҵ
	 * ���򣬿�ʼ����ǰ��ҵ
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
	 * ������ҵ����
	 * �ȴ����зǿ�ʱ���м��ص�ǰ��ҵ������Ӷ�����ɾ��
	 * ����״̬����Ϊ�ǿ���
	 * �����ҵ��ֵ
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
	 * ������ҵ����
	 * ��ǰʱ���һ
	 * ����ǰʱ��������ʱ��ʱ
	 * ����״̬��Ϊ����
	 */
	private void work() {
		// TODO Auto-generated method stub
		sumTime++;
		if (sumTime == now.getFinishingTime()) {
			isFree = true;
		}
	}

	/**
	 * �����ļ���ҵ������
	 */
	private void loadTaskQueue() {
		this.TaskQueue = readFile();
	}

	/**
	 * ���ļ��ж�ȡ���ݲ������ҵ������
	 * @return ��ҵ������
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

**SJF������ҵ�����㷨�ࣩ��**

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
 * ����ҵ�����㷨��
 * ���˶��һ��������ڲ��࣬��������FCFSһ��
 * @author ������
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
	 * �ڲ���TaskComparator����ȴ����е�������
	 * @author ������
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

**SJF_TP������ҵ����_ռλ�㷨ʵ���ࣩ��**

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
 * ����ҵ�����㷨��
 * @author ������
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
	 * ��̬��ҵ�������ǿ�ʱ��������һ����ҵ��ӵ��ȴ������У���׼���������
	 */
	public void startUp() {
		if(TaskQueue.size()!=0){
			waitQueue.offer(TaskQueue.poll());
		}
		begin();
	}
	
	

	/**
	 * ׼��������
	 * ÿ��׼������ʱ�ȶԵȴ����н�������ʣ��ʱ�����ٵ���ҵ����ǰͷ
	 * ����ȴ����зǿ����ǰͷ��ȡһ����ҵ
	 * ���ʣ��ʱ����ڷ���ʱ�䣨��ҵ֮ǰδ������������򽫵�ǰʱ���������Ŀ�ʼʱ��
	 * ���ʼ����
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
	 * ��ʼ������
	 * ʣ��ʱ���һ����ǰʱ���һ
	 * ���ʣ��ʱ��Ϊ�㣬�������ҵ����ʱ����Ϣ
	 * �����ҵ������ӵȴ������Ƴ�
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

**Task��**

```
package com.fjnu.JavaSubject03;

import java.text.DecimalFormat;
/**
 * Task������
 * @author ������
 *
 */
public class Task {
	private int taskID;
	private int arrivalTime; //����ʱ��
	private int serviceTime; //����ʱ��
	private int startingTime; //��ʼʱ��
	private int finishingTime; //���ʱ��=��ʼʱ��+����ʱ��
	private int turnAroundTime; //��תʱ��=���ʱ��-�ﵽʱ��
	private double weightTurnAround; //��Ȩ��תʱ��=��תʱ��/����ʱ��
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

**Main������**

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
