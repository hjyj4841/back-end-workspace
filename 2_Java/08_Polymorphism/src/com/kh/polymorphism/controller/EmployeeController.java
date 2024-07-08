package com.kh.polymorphism.controller;

import com.kh.polymorphism.model.child.Engineer;
import com.kh.polymorphism.model.parent.Employee;

public class EmployeeController {
	
	// 이름으로 사람 찾기
	public Employee findEmployeeByName(String name, Employee[] empArr) {
		for(Employee e : empArr) {
			if(name.equals(e.getName())) {
				return e;
			}
		}
		return null;
	}
	
	// 찾는 사람의 연봉은?
	public int getAnnalSalary(Employee e) {
		if(e == null) {
			return 0;
		}else if(e instanceof Engineer){ // 특정 자식 객체 찾는 방법
			Engineer engineer = (Engineer)e; // 부모 -> 자식 : 강제 형 변환
			return e.getSalary() * 12 + engineer.getBonus();
		}
		return e.getSalary() * 12;
	}
	
	// 전체 사람들의 연봉 총합은?
	public int getTotalSalary(Employee[] empArr) {
		int sum = 0;
		
		for(Employee employee : empArr) {
			sum += getAnnalSalary(employee);
		}
		
		return sum;
	}
	
}
