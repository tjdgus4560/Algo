
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int[][] map;
	static boolean[][] v;
	static int N;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<Point> process;
	static int maxP;
	static int minL;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			process = new ArrayList<Point>();
			N = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						process.add(new Point(i, j));
					}
				}
			}
			maxP = Integer.MIN_VALUE;
			minL = Integer.MAX_VALUE;

			for (int i = 0; i < process.size(); i++) {
				v = new boolean[N][N];
				for (int j = 0; j < process.size(); j++) {
					Point p = process.get(j);
					v[p.r][p.c] = true;
				}
				dfs(i, 0, 0);
			}

			System.out.println("#" + tc + " " + minL);
		}
	}

	private static void print(int[][] map2) {
		// TODO Auto-generated method stub
		for (int[] is : map2) {
			System.out.println(Arrays.toString(is));
		}
	}

	private static void dfs(int pNum, int selectedCount, int lineCount) {
		// basis
		// 프로세스 다 골랐다면 상황에 맞는 처리
		if (pNum == process.size()) {
			if (maxP < selectedCount) {
				minL = lineCount;
				maxP = selectedCount;
			} else if (maxP == selectedCount && lineCount < minL) {
				minL = lineCount;
			}
			return;
		}

		// 만약 더이상 진행해도 이전의 최대프로세스 개수에 못미치면 리턴
		if (maxP > 0 && process.size() - pNum + selectedCount < maxP) {
			return;
		}

		// inductive
		// 프로세스가 연결이 가능한지를 체크하며 가능 여부에 따라 재귀 처리
		
		Point p = process.get(pNum);
		if (p.r == 0 || p.r == N - 1 || p.c == 0 || p.c == N - 1) {
			// 프로세스가 외곽에 위치해 있으면 프로세스 카운트만 증가
			dfs(pNum + 1, selectedCount + 1, lineCount);
		} else {
			for (int d = 0; d < 4; d++) {
				// 연결이 가능한지 체크
				boolean check = connectCheck(pNum, d);
				if (check) {
					// 선연결O, 프로세스 선택O 인상태로 다음 프로세스 탐색
					lineCount += connectLine(pNum, d, true); // true : 연결
					dfs(pNum + 1, selectedCount + 1, lineCount);
					lineCount -= connectLine(pNum, d, false); // false : 전선 해제
				} else {
					// 선연결X, 프로세스 선택X 인상태로 다음 탐색
					dfs(pNum + 1, selectedCount, lineCount);
				}

			}
		}

	}

	private static boolean connectCheck(int pNum, int d) {
		Point p = process.get(pNum);
		int nr = p.r + dr[d];
		int nc = p.c + dc[d];

		while (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
			if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
				return true;
			}
			nr += dr[d];
			nc += dc[d];
		}
		return false;
	}

	private static int connectLine(int pNum, int d, boolean signal) {
		int cnt = 0;
		Point p = process.get(pNum);
		int nr = p.r + dr[d];
		int nc = p.c + dc[d];

		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			// 시그널에 따른 선 삽입 삭제
			if (signal == true) {
				v[nr][nc] = true;
				cnt++;
			} else {
				v[nr][nc] = false;
				cnt++;
			}
			nr += dr[d];
			nc += dc[d];
		}
		return cnt;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}

}
