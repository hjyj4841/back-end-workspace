package com.kh.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.kh.set.model.Person;

/*
 * TreeSet
 * - 저장과 동시에 자동 오름차순 정렬(HashSet + 정렬)
 * - HashSet보다 데이터 추가, 삭제에 시간이 더 걸림
 */

public class B_TreeSet {

	public void method1() {
		Set<String> set = new TreeSet<>();
		
		set.add("이제훈");
		set.add("구교환");
		set.add("홍사빈");
		set.add("이제훈");
		set.add("이제훈");
		
		System.out.println(set);
		System.out.println(set.size());
		System.out.println("구교환이 포함되어 있는가? " + set.contains("구교환"));
		
		set.remove("홍사빈");
		System.out.println(set);
		
		set.clear();
		System.out.println("비어있는지? " + set.isEmpty());
	}
	
	public void method2() {
		TreeSet<Person> set = new TreeSet<>();
		
		set.add(new Person("이제훈", 40));
		set.add(new Person("구교환", 41));
		set.add(new Person("홍사빈", 27));
		set.add(new Person("구교환", 41));
		set.add(new Person("구교환", 41));
		
		// 객체의 주소값을 가지고 비교하기 때문에 모두 다른 객체로 인식해서 중복 제거 X
		//	--> 객체에 hashCode, equals 메서드 재정의 자동 생성
		for(Person p : set) System.out.println(p);
		
		System.out.println("-----------");
		/*
		 * Iterator
		 * - 컬렉션에 저장된 요소를 접근하는데 사용하는 인터페이스
		 * - iterator()를 호출해서 Iterator를 구현한 객체를 얻어서 사용
		 */
		Iterator<Person> it = set.iterator();
		
		while(it.hasNext()) { // 읽어올 요소가 있는지 확인
			System.out.println(it.next()); // 다음 요소를 읽어옴
		}
		
	}
	
	public static void main(String[] args) {
		B_TreeSet a = new B_TreeSet();
//		a.method1();
		a.method2();
	}

}
