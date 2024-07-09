package com.kh._abstract;

import com.kh._abstract.step1.BasketBall;
import com.kh._abstract.step1.FootBall;
import com.kh._abstract.step1.Sports;
import com.kh._abstract.step2.BulgogiPizza;
import com.kh._abstract.step2.PineApplePizza;
import com.kh._abstract.step2.Pizza;
import com.kh._abstract.step2.PotatoPizza;

/*
 * 추상 클래스(Abstract Class)
 * 
 * [접근제어자] abstract class 클래스명 { }
 * 
 * - 미완성 클래스로 추상 메서드를 포함한 클래스
 * - 미완성 클래스이기 때문에 new 연산자를 통해서 객체 생성 X
 * - 일반적인 멤버(변수, 메서드) 포함 가능 (변수 + 메서드 + 추상 메서드)
 * - 객체 생성을 할 수 없지만 참조형 변수 타입으로는 사용 가능
 * - 상속 관계로 구성되어 있으면 다형성 적용 가능
 * 
 * 추상 메서드(Abstract Method)
 * 
 * [접근제어자] abstract 반환타입 메서드명(매개변수, ...);
 * 
 * - 미완성 메서드로 중괄호({})가 구현되지 않는 메서드
 * - 추상 클래스를 상속받는 자식 클래스에서 "반드시" 오버라이딩(재정의)해야 한다.
 * - 오버라이딩(재정의) 해주지 않으면 컴파일 에러 발생
 * - 자식 클래스에 강제성을 부여하는 방법
 */

public class Application {

	public static void main(String[] args) {
		
		// 다형성으로 인해서 부모 타입으로 가능
		Sports b = new BasketBall(9);
		Sports f = new FootBall(11);
//		Sports s s= new Sports(5); -> 추상 클래스 객체 생성 X
		
		b.rule();
		f.rule();
		System.out.println();
		
		// 피자 관련된 출력 과정
		Pizza[] pizza = {
			new BulgogiPizza(30000, "피자헛"),
			new PotatoPizza(20000, "도미노피자"),
			new PineApplePizza(10000, "잭슨피자")
		};
		
		for(Pizza p : pizza) {
			p.makePizza();
		}
		
		/*
		 * 피자헛의 불고기 피자 가격은 30000원
		 * 피자 반죽과 함께 도우를 빚다.
		 * 토핑은 불고기를 포함시킨다.
		 * 피자를 180도에서 10분간 구운다.
		 * 피자를 8등분으로 자른다.
		 * 피자를 포장한다.
		 * 
		 * 도미노피자의 포테이토 피자 가격은 20000원
		 * 피자 반죽과 함께 도우를 빚다.
		 * 토핑은 감자를 포함시킨다.
		 * 피자를 180도에서 10분간 구운다.
		 * 피자를 8등분으로 자른다.
		 * 피자를 포장한다.
		 * 
		 * 잭슨피자의 파인애플 피자 가격은 10000원
		 * 피자 반죽과 함께 도우를 빚다.
		 * 토핑은 파인애플을 포함시킨다.
		 * 피자를 180도에서 10분간 구운다.
		 * 피자를 8등분으로 자른다.
		 * 피자를 포장한다.
		 */
	}
}
