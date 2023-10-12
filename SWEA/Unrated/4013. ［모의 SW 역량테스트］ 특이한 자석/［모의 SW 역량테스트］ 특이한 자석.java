

import java.util.Scanner;

// 4013. [모의 SW 역량테스트] 특이한 자석
public class Solution {
	static int K;
	static int[][] mg; // 자석
	static int[] arr; // 각 톱니의 회전배열 0: 그대로, 1: 시계, -1: 반시계

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			K = sc.nextInt();
			mg = new int[4][8];

			for (int i = 0; i < mg.length; i++) {
				for (int j = 0; j < mg[i].length; j++) {
					mg[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < K; i++) {
				int k = sc.nextInt() - 1;
				int d = sc.nextInt();
				arr = new int[4];
				rotate(k, d);
			}

			// 회전이 끝난 후 점수 계산
			int score = cal();
			System.out.println("#" + tc + " " + score);
		}
	}

	private static void rotate(int k, int d) {
		arr[k] = d;
		// 오른쪽
				for(int i=k+1 ; i<4 ; i++) {
					if(mg[i][6]==mg[i-1][2]) {
						break;
					}
					else arr[i]= -arr[i-1];
				}
				
				//왼쪽
				for(int i=k-1 ; i>=0 ; i--) {
					if(mg[i+1][6] == mg[i][2]) {
						break;
					}
					else arr[i] = -arr[i+1];
				}
				
				// 돌리기
				for(int i=0; i<4; i++) {
					if(arr[i]==0) continue;
					// 시계
					else if(arr[i] == 1) {
						int tmp=mg[i][7];
						for(int j=7 ; j>0 ;j--) {
							mg[i][j]=mg[i][j-1];
						}
						mg[i][0]=tmp;
					}
					// 반시계
					
					else if(arr[i] == -1) {
						
						int tmp=mg[i][0];
						for(int j=0; j<7 ; j++) {
							mg[i][j]=mg[i][j+1];
						}
						mg[i][7]=tmp;
					}
				}
	}

	private static int cal() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (mg[i][0] == 1) {
				score += (1 << i); // 2의 i제곱을 더함
			}
		}
		return score;
	}
}
