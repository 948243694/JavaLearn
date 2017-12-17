package com.fjnu.JavaSubject02;

public class CheesePizza extends Pizza {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		System.out.println("准备CheesePizza材料");
	}

	@Override
	protected void bake() {
		// TODO Auto-generated method stub
		System.out.println("烘焙CheesePizza");
	}

	@Override
	protected void cut() {
		// TODO Auto-generated method stub
		System.out.println("切割CheesePizza");
	}

	@Override
	protected void box() {
		// TODO Auto-generated method stub
		System.out.println("CheesePizza装包");
	}

}
