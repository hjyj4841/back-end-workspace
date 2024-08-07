package com.kh.example.practice3;

import java.util.Scanner;

import com.kh.example.practice3.controller.EmployeeController;
import com.kh.example.practice3.model.Employee;

public class Application {
	Scanner sc = new Scanner(System.in);
	Employee viewEmployee = new Employee();
	EmployeeController employeeController = new EmployeeController();
	
	public static void main(String[] args) {
		Application app = new Application();
		
		while(true) {
			switch(app.employeeMenu()) {
				case 1:
					app.insertEmp();
					break;
				case 2:
					app.updateEmp();
					break;
				case 3:
					app.printEmp();
					break;
				case 9:
					System.out.println("프로그램을 종료 합니다.");
					return;
				default :
					System.err.println("번호를 잘못 입력했습니다.");
			}
		}
	}
	
	// 메인 메뉴 출력
	public int employeeMenu() {
		System.out.println("1. 사원 정보 추가");
		System.out.println("2. 사원 정보 수정");
		System.out.println("3. 사원 정보 출력");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 번호를 누르세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	// 저장할 데이터 입력
	public void insertEmp() {
		System.out.print("사원 번호 : ");
//		int empNo = Integer.parseInt(sc.nextLine());
		viewEmployee.setEmpNo(Integer.parseInt(sc.nextLine()));
		System.out.print("사원 이름 : ");
//		String name = sc.nextLine();
		viewEmployee.setName(sc.nextLine());
		System.out.print("사원 성별 : ");
//		char gender = sc.nextLine().charAt(0);
		viewEmployee.setGender(sc.nextLine().charAt(0));
		System.out.print("전화 번호 : ");
//		String phone = sc.nextLine();
		viewEmployee.setPhone(sc.nextLine());
		
		while(true) {
			System.out.print("추가 정보를 더 입력하시겠습니까?(y/n) : ");
			switch(sc.nextLine().charAt(0)) {
				case 'y':
					System.out.print("사원 부서 : ");
//					String dept = sc.nextLine();
					viewEmployee.setDept(sc.nextLine());
					System.out.print("사원 연봉 : ");
//					int salary = Integer.parseInt(sc.nextLine());
					viewEmployee.setSalary(Integer.parseInt(sc.nextLine()));
					System.out.print("보너스 율 : ");
//					double bonus = Double.parseDouble(sc.nextLine());
					viewEmployee.setBonus(Double.parseDouble(sc.nextLine()));

					employeeController.add(viewEmployee.getEmpNo(), 
							viewEmployee.getName(), 
							viewEmployee.getGender(), 
							viewEmployee.getPhone(), 
							viewEmployee.getDept(),
							viewEmployee.getSalary(), 
							viewEmployee.getBonus());
					return;
				case 'n':
					employeeController.add(viewEmployee.getEmpNo(), 
							viewEmployee.getName(), 
							viewEmployee.getGender(), 
							viewEmployee.getPhone());
					return;
				default :
					System.err.println("번호를 잘못 입력했습니다.");
			}
		}
	}
	
	// 수정할 데이터 입력
	public void updateEmp() {
		while(true) {
			System.out.println("사원의 어떤 정보를 수정하시겠습니까?");
			System.out.println("1. 전화 번호");
			System.out.println("2. 사원 연봉");
			System.out.println("3. 보너스 율");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.print("전화 번호 입력 : ");
					employeeController.modify(sc.nextLine());
					break;
				case 2:
					System.out.print("사원 연봉 입력 : ");
					employeeController.modify(Integer.parseInt(sc.nextLine()));
					break;
				case 3:
					System.out.print("보너스 율 입력 : ");
					employeeController.modify(Double.parseDouble(sc.nextLine()));
					break;
				case 9:
					return;
				default:
					System.err.println("번호를 잘못 입력했습니다.");
			}
		}
	}
	
	// 데이터 출력
	public void printEmp() {
		System.out.println(employeeController.info());
	}

}
