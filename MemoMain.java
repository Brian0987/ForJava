package com.JinhyungJeong.memo;

import java.util.Scanner;

/**
 * 간편 메모장
 * 
 * @author jinhyung
 *
 */
public class MemoMain {

	public static void main(String[] args) {

		// 1.키보드 입력으로 명령어를 먼저 받는다
		// *프로그램이 시작하면 명령어 번호를 보여준다
		// 1.쓰기 2.읽기 3.수정 4.삭제
		Memo memo = new Memo();

		Scanner scanner = new Scanner(System.in);

		boolean runFlag = true;

		while (runFlag) {
			memo.showCommand();
			String cmd = scanner.nextLine();
			switch (cmd) {
			case "1":// 쓰기
				memo.write(scanner);
				break;
			case "2":// 읽기
				memo.list(scanner);
				break;
			case "3":// 수정
				memo.update(scanner);
				break;
			case "4":// 삭제
				memo.delete(scanner);
				break;
			case "0":
				runFlag = false;
				break;
			default:
				System.out.println("명령어가 잘못되었습니다!");
			}
		}

		System.out.println("프로그램이 종료되었습니다!");
	}

}
