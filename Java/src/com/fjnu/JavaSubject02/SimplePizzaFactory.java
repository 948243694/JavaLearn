package com.fjnu.JavaSubject02;

public class SimplePizzaFactory {
	
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		if(type.equals("cheese")) {
			pizza = new CheesePizza();
		}else if(type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		}else {
			pizza = new ClamPizza();
		}
		return pizza;
		
	}
}
