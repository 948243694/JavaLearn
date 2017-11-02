package com.fjnu.JavaSubject03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.fjnu.JavaSubject03.SJF.TaskComparator;


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

	public void startUp() {
		if(TaskQueue.size()!=0){
			waitQueue.offer(TaskQueue.poll());
		}
		begin();
	}
	
	

	private void begin() {
		Collections.sort(waitQueue, new TaskComparator());
		if(waitQueue.size()!=0){
			this.now = this.waitQueue.peek();
			if(now.getServiceTime()==now.getRemainingTine())
				now.setStartingTime(sumTime);
			work();
		}
	}

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
