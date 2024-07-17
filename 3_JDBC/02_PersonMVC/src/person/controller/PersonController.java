package person.controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import config.ServerInfo;
import person.model.Person;

public class PersonController {
	private Properties p = new Properties();
	private Connection conn;
	
	// 리턴 타입이나 매개변수(파라미터) 자유롭게 변경 가능
	// 메서드 추가 가능 <-- 추천
	/*
	 * 1. 드라이버 로딩
	 * 2. DB 연결
	 * 3. PreparedStatement - 쿼리
	 * 4. 쿼리 실행
	 */
	
	// 고정적인 반복 -- DB 연결, 자원 반납 -> 공통적인 메서드 정의.. 메서드마다 호출해서 사용
	public void dataBaseConnect() {
		try {
			p.load(new FileInputStream("src/config/jdbc.properties"));
			
			Class.forName(ServerInfo.DRIVER_NAME);
			conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 5. 자원 반납
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		closeAll(ps, conn);
	}
	
	// 변동적인 반복 -- 비즈니스 로직 DAO(Database Access Object)
	
	// person 테이블에 데이터 추가 - INSERT
	public String addPerson(Person person) throws SQLException {
		String result = "회원가입 실패";
		dataBaseConnect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("insert"));
		
		ps.setString(1, person.getName());
		ps.setInt(2, person.getAge());
		ps.setString(3, person.getAddr());
		
		if(ps.executeUpdate() == 1) result = person.getName() + "님, 회원가입 완료!";
		
		closeAll(ps, conn);
		return result;
	}
	
	// person 테이블에 데이터 수정 - UPDATE
	public String updatePerson(Person person) throws SQLException {
		String result = "회원 수정 실패";
		dataBaseConnect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("update"));
		
		ps.setString(1, person.getName());
		ps.setInt(2, person.getAge());
		ps.setString(3, person.getAddr());
		ps.setInt(4, person.getId());
		
		if(ps.executeUpdate() == 1) result = person.getName() + "님, 정보 수정 완료!";
		
		closeAll(ps, conn);
		return result;
	}
	
	// person 테이블에 데이터 삭제 - DELETE
	public String removePerson(int id) throws SQLException {
		String result = "회원 삭제 실패";
		String name = searchPerson(id).getName();
		
		dataBaseConnect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("delete"));
		
		ps.setInt(1, id);
		
		if(ps.executeUpdate() == 1) result = name + "님, 회원탈퇴 완료!";
		
		closeAll(ps, conn);
		return result;		
	}
	
	// person 테이블에 있는 데이터 전체 보여주기 - SELECT
	public ArrayList<Person> searchAllPerson() throws SQLException {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		dataBaseConnect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("selectAll"));
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
//			--- 생성자 방식 ---
			persons.add(new Person(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"), rs.getString("ADDR")));
//			--- setter 방식 ---
//			Person person = new Person();
//			person.setId(rs.getInt("ID"));
//			person.setName(rs.getString("NAME"));
//			person.setAge(rs.getInt("AGE"));
//			person.setAddr(rs.getString("ADDR"));
//			persons.add(person);
		}
				
		closeAll(ps, conn);
		return persons;
	}
	
	// person 테이블에 데이터 한개만 가져오기 - SELECT
	public Person searchPerson(int id) throws SQLException {
		Person person = null;
		
		dataBaseConnect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("select"));
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			person = new Person(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"), rs.getString("ADDR"));
		}
		
		closeAll(rs, ps, conn);
		return person;
	}
}
