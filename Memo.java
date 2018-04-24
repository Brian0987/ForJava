package com.JinhyungJeong.memo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * 메모
 * 
 * @author jinhyung
 *
 */
public class Memo {
	public static final String MEMO_DIR = "/temp/memo/";
	// 종료 커맨드를 상수로 정의
	public static final String EXIT = "/exit";

	// 생성자에서 메모를 저장할 디렉토리를 생성해준다
	public Memo() {
		File dir = new File(MEMO_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	// 1.명령어를 출력하는 함수
	public void showCommand() {
		System.out.println("아래 명령 번호를 입력하세요 ---");
		System.out.println("1.쓰기, 2.읽기, 3.수정, 4.삭제, 0.프로그램 종료");
	}

	// 쓰기
	public void write(Scanner scanner) {
		System.out.println("---쓰기 모드---(종료 커맨드는 /exit)");
		// 전체글을 저장할 변수
		StringBuilder content = new StringBuilder();

		// 키보드 입력을 받아야 한다.
		while (true) {
			String line = scanner.nextLine();
			if (line.equals(EXIT)) {
				break;
			} else {
				content.append(line + "\r\n");
			}
		}

		// 작성한 내용이 있으면 파일로 쓴다
		if (!content.toString().equals("")) {
			// 가. 현재 시간 가져와서 파일명으로 만든다.
			long now = System.currentTimeMillis();
			// 나. 년월일_시분초.txt 파일로 포멧팅
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String filename = sdf.format(now) + ".txt";
			// 다. 내용을 저장할 파일경로 설정
			Path path = Paths.get(MEMO_DIR, filename);
			try {
				// 라.파일 쓰기
				Files.write(path, content.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("메모를 등록하였습니다");
		}
	}

	// 파일 목록 보기
	public void list(Scanner scanner) {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		// 파일에 번호를 붙여 출력
		int fNum = 0;
		for (String filename : fileList) {
			fNum++;
			System.out.println(fNum + "." + filename);
		}
		// 파일 유무 확인
		if (fNum != 0) {
			System.out.println("파일의 번호를 선택해주십시오");
			String memoNum = scanner.nextLine();
			// 입력된 번호 확인
			if (!isNum(memoNum) || memoNum.contains(".")) {
				System.out.println("명령어가 잘못되었습니다!");
			} else {
				int n = Integer.parseInt(memoNum);

				if (n > fNum || n < 1) {
					System.out.println("존재하지 않는 파일입니다!");
				} else {
					// 파일 읽기
					File aFile = new File(MEMO_DIR + fileList[n - 1]);
					try (FileReader fr = new FileReader(aFile)) {
						int one = 0;
						String fullText = new String();
						while (true) {
							one = fr.read();
							if (one == -1)
								break;
							fullText = fullText + (char) one;
						}

						System.out.println(fullText);

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("저장된 메모가 없습니다");
		}
	}

	// 파일 수정하기
	public void update(Scanner scanner) {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		// 파일에 번호를 붙여 출력
		int fNum = 0;
		for (String filename : fileList) {
			fNum++;
			System.out.println(fNum + "." + filename);
		}
		// 파일 유무 확인
		if (fNum != 0) {
			System.out.println("파일의 번호를 선택해주십시오");
			String memoNum = scanner.nextLine();
			// 입력된 번호 확인
			if (!isNum(memoNum) || memoNum.contains(".")) {
				System.out.println("명령어가 잘못되었습니다!");
			} else {
				int n = Integer.parseInt(memoNum);

				if (n > fNum || n < 1) {
					System.out.println("존재하지 않는 파일입니다!");
				} else {
					System.out.println("---쓰기 모드---(종료 커맨드는 /exit)");
					// 전체글을 저장할 변수
					StringBuilder content = new StringBuilder();
					// 키보드 입력을 받아야 한다.
					while (true) {
						String line = scanner.nextLine();
						if (line.equals(EXIT)) {
							break;
						} else {
							content.append(line + "\r\n");
						}
					}
					// 내용을 저장할 파일경로 설정
					Path path = Paths.get(MEMO_DIR + fileList[n - 1]);
					try {
						// 파일 쓰기
						Files.write(path, content.toString().getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("메모를 수정하였습니다");

				}
			}
		} else {
			System.out.println("저장된 메모가 없습니다");
		}
	}

	// 파일 삭제하기
	public void delete(Scanner scanner) {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		// 파일에 번호를 붙여 출력
		int fNum = 0;
		for (String filename : fileList) {
			fNum++;
			System.out.println(fNum + "." + filename);
		}
		// 파일 유무 확인
		if (fNum != 0) {
			System.out.println("파일의 번호를 선택해주십시오");
			String memoNum = scanner.nextLine();
			// 입력된 번호 확인
			if (!isNum(memoNum) || memoNum.contains(".")) {
				System.out.println("명령어가 잘못되었습니다!");
			} else {
				int n = Integer.parseInt(memoNum);

				if (n > fNum || n < 1) {
					System.out.println("존재하지 않는 파일입니다!");
				} else {
					// 파일 삭제
					File aFile = new File(MEMO_DIR + fileList[n - 1]);

					if (aFile.delete()) {
						System.out.println(fileList[n - 1] + "가 삭제되었습니다");
					} else {
						System.out.println(fileList[n - 1] + "를 삭제하는데 실패했습니다");
					}
				}
			}
		} else {
			System.out.println("저장된 메모가 없습니다");
		}
	}

	// String이 실수인지 확인하는 함수
	public static boolean isNum(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
