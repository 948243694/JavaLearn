package com.fjnu.JavaSubject02;

public class ClamPizza extends Pizza {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		System.out.println("׼��ClamPizza����");
	}

	@Override
	protected void bake() {
		// TODO Auto-generated method stub
		System.out.println("�決ClamPizza");
	}

	@Override
	protected void cut() {
		// TODO Auto-generated method stub
		System.out.println("�и�ClamPizza");
	}

	@Override
	protected void box() {
		// TODO Auto-generated method stub
		System.out.println("ClamPizzaװ��");
	}

}
