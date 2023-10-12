
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static char[][] arr;
	static ArrayList<Point> teacher;
	static ArrayList<Point> blank;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static LinkedList<Point> sel;
	static boolean check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new char[N][N];
		teacher = new ArrayList<Point>();
		blank = new ArrayList<Point>();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = sc.next().charAt(0);
				if (arr[i][j] == 'T') {
					teacher.add(new Point(i, j));
				} else if (arr[i][j] == 'X') {
					blank.add(new Point(i, j));
				}
			}
		}

		sel = new LinkedList<Point>();
		re(0, 0);
		if(check) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
	}

	private static void re(int k, int idx) {
		// basis
		if (idx == 3) {
			for (int i = 0; i < 3; i++) {
				Point p = sel.get(i);
				arr[p.r][p.c] = 'O';
			}
			check = isOk();
			for (int i = 0; i < 3; i++) {
				Point p = sel.get(i);
				arr[p.r][p.c] = 'X';
			}
			return;
		}
		// inductive
		for (int i = k; i < blank.size() && !check; i++) {
			sel.add(blank.get(i));
			re(i + 1, idx + 1);
			sel.remove(idx);
		}

	}

	private static boolean isOk() {
		
		for (int i = 0; i < teacher.size(); i++) {
			Point p = teacher.get(i);
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;
				while(nr>=0&&nr<N&&nc>=0&&nc<N) {
					if(arr[nr][nc] == 'S') {
						return false;
					}else if(arr[nr][nc] == 'O') {
						break;
					}
					nr = nr+dr[d];
					nc = nc+dc[d];
				}
			}
		}
		return true;
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

	private static void print(char[][] arr2) {
		for (char[] cs : arr2) {
			System.out.println(Arrays.toString(cs));
		}

	}
}
