package com.fjnu.JavaSubject02;

public class ClamPizza extends Pizza {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		System.out.println("准备ClamPizza材料");
	}

	@Override
	protected void bake() {
		// TODO Auto-generated method stub
		System.out.println("烘焙ClamPizza");
	}

	@Override
	protected void cut() {
		// TODO Auto-generated method stub
		System.out.println("切割ClamPizza");
	}

	@Override
	protected void box() {
		// TODO Auto-generated method stub
		System.out.println("ClamPizza装包");
	}

}
