package com.fjnu.JavaSubject02;

public class CheesePizza extends Pizza {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		System.out.println("׼��CheesePizza����");
	}

	@Override
	protected void bake() {
		// TODO Auto-generated method stub
		System.out.println("�決CheesePizza");
	}

	@Override
	protected void cut() {
		// TODO Auto-generated method stub
		System.out.println("�и�CheesePizza");
	}

	@Override
	protected void box() {
		// TODO Auto-generated method stub
		System.out.println("CheesePizzaװ��");
	}

}
