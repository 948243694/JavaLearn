package com.fjnu.JavaSubject02;

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
			System.out.println("�������¯������ɿ�����ţ�̻����");
			empty = false;
		}
		else {
			System.out.println("��¯��������װʧ��");
		}
	}
	
	public void boil() {
		if(!empty && !boiled) {
			System.out.println("���ڽ���¯���");
			boiled = true;
		}
	}
	
	public void drain() {
		if(!empty && boiled) {
			System.out.println("�����ų���е��ɿ�����ţ�̻����");
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
