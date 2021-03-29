package com.atguigu.java8;

public class SubClass implements MyFun, MyInterface{

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

	@Override
	public Integer getValue(Integer num) {
		return null;
	}
}
