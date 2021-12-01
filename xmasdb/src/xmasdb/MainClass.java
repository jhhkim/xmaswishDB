package xmasdb;

import java.util.Scanner;

import dto.XmasDto;
import dao.XmasDao;

public class MainClass {

	public static void main(String[] args) {
		//데이터 사용자가 입력
		Scanner name = new Scanner(System.in);
		System.out.print("이름: ");
		String n = name.next();
		
		Scanner age = new Scanner(System.in);
		System.out.print("나이: ");
		int a = age.nextInt();
		
		Scanner gift = new Scanner(System.in);
		System.out.print("받고싶은 선물: ");
		String g = gift.next();
		
		//DB삽입
		XmasDto dto = new XmasDto();
		dto.setName(n);
		dto.setAge(a);
		dto.setGift(g);
		
		XmasDao dao = new XmasDao();
		dao.insertXmasInfo(dto);
		
		//전체 데이터 보기
		dao.selectAll();
		
	}

}
