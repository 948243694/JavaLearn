package com.fjnu.JavaSubject02;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ChocolateBoiler a =ChocolateBoiler.getChocolateBoiler();
		System.out.println(a.isEmpty()+","+a.isBoiled());
		a.fill();
		System.out.println(a.isEmpty()+","+a.isBoiled());
		a.boil();
		System.out.println(a.isEmpty()+","+a.isBoiled());
		a.drain();
		System.out.println(a.isEmpty()+","+a.isBoiled());*/
		
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore store = new PizzaStore(factory);
		store.orderPizza("pepperoni");
	}

}
