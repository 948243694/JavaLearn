package com.fjnu.JavaSubject02;

public class PepperoniPizza extends Pizza {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		System.out.println("准备PepperoniPizza材料");
	}

	@Override
	protected void bake() {
		// TODO Auto-generated method stub
		System.out.println("烘焙PepperoniPizza");
	}

	@Override
	protected void cut() {
		// TODO Auto-generated method stub
		System.out.println("切割PepperoniPizza");
	}

	@Override
	protected void box() {
		// TODO Auto-generated method stub
		System.out.println("PepperoniPizza装包");
	}

}
