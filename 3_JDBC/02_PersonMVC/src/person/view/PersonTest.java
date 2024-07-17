package person.view;

import java.sql.SQLException;
import java.util.Scanner;

import person.controller.PersonController;
import person.model.Person;

public class PersonTest {

	PersonController pc = new PersonController();
	Person person = new Person();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		PersonTest pt = new PersonTest();
		
		// 이 부분은 테스트 용도로만
		try {
//			pt.addPerson();
//			pt.updatePerson();
//			pt.removePerson();
			pt.searchAllPerson();
			pt.searchPerson();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("숫자 입력하세요.");
		}

	}
	
	// 각 Controller에 메서드들 연결하는 건 각 메서드들에서 구현
	
	// person 테이블에 데이터 추가 - INSERT
	public void addPerson() throws SQLException {
		System.out.print("이름 입력 : ");
		person.setName(sc.nextLine());
		System.out.print("나이 입력 : ");
		person.setAge(Integer.parseInt(sc.nextLine()));
		System.out.print("주소 입력 : ");
		person.setAddr(sc.nextLine());
		
		// ~~님 회원가입 완료!
		System.out.println(pc.addPerson(person));
	}
	
	// person 테이블에 데이터 수정 - UPDATE
	public void updatePerson() throws SQLException {
		System.out.print("수정할 회원의 ID : ");
		person.setId(Integer.parseInt(sc.nextLine()));
		
		System.out.print("수정할 이름 : ");
		person.setName(sc.nextLine());
		System.out.print("수정할 나이 : ");
		person.setAge(Integer.parseInt(sc.nextLine()));
		System.out.print("수정할 주소 : ");
		person.setAddr(sc.nextLine());
		
		// ~~님, 정보 수정 완료!
		System.out.println(pc.updatePerson(person));
	}
	
	// person 테이블에 데이터 삭제 - DELETE
	public void removePerson() throws NumberFormatException, SQLException {
		System.out.print("삭제할 회원의 id : ");
		
		// ~~님, 회원탈퇴 완료!
		System.out.println(pc.removePerson(Integer.parseInt(sc.nextLine())));
	}
	
	// person 테이블에 있는 데이터 전체 보여주기 - SELECT
	public void searchAllPerson() throws SQLException {
		for(Person person : pc.searchAllPerson()) System.out.println(person);
	}
	
	// person 테이블에 데이터 한개만 가져오기 - SELECT
	public void searchPerson() throws NumberFormatException, SQLException {
		System.out.print("정보를 가져올 ID : ");
		
		System.out.println(pc.searchPerson(Integer.parseInt(sc.nextLine())));
	}

}
