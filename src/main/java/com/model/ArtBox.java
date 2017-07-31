package com.model;

public class ArtBox {
	
	private int id;
	private String name;
	private int age;
	private double cost;
	
	public ArtBox (String name, int age, double cost){
		this.name = name;
		this.age = age;
		this.cost = cost;
	}
	
	public ArtBox (int id, String name, int age, double cost){
		this(name, age, cost);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ArtBox [name=" + name + ", age=" + age + ", cost=" + cost + "]";
	}
}
