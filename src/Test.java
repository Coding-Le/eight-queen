import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String searchType;
		System.out.print("Please select the method you want to use, 1 for Climb-Hill, \n");
		System.out.print("2 for Random-Climb-Hill, 3 for SimulateAneal, please input the number\n");
		System.out.print("The size of sample is 10^5~10^6\n");
		while (true) // We will run with 8puzzle
		{
			searchType = null;
			searchType = scan.nextLine();
			if (searchType.equals("1"))
			{
				int total_cost = 0;
				int success_num = 0;
				int fail_num = 0;
				for (int i = 0; i < 10000; i++) {
					Queen[] eightQueen = createEightQueen();
					Queen8 queen = new Queen8(eightQueen);
					int temp = queen.hillClimb();
				    if(temp > 0) {
				    	success_num++;
				    	total_cost += temp;
				    } else {
				    	fail_num++;
				    }
				}
				double ave_cost = total_cost;
				double solve_rate = success_num;
				solve_rate /= (success_num+fail_num);
				System.out.print("The solve rate for Climb_Hill is" + solve_rate + "\n");
				if (solve_rate > 0) {
					ave_cost /= success_num;
					System.out.print("The average cost for Climb_Hill is" + ave_cost+"\n");
				}
				System.out.print("\n");
			}
			// Use AStarSearch.java with Manhattan Distance
			else if (searchType.equals("2"))
			{
				int total_cost = 0;
				int times = 0;
				for (int i = 0; i < 10000; i++) {
					Queen[] eightQueen = createEightQueen();
					Queen8 queen = new Queen8(eightQueen);
					int temp = queen.randomHillClimb();
					total_cost = total_cost+temp;
					times++;
				}
				double ave_cost = total_cost;
				ave_cost /= times;
				System.out.print("The average cost for Random_Climb_Hill is" + ave_cost+"\n");
				System.out.print("\n");
			}
			else if (searchType.equals("3"))
			{
				int total_cost = 0;
				int success_num = 0;
				int fail_num = 0;
				for (int i = 0; i < 1000; i++) {
					Queen[] eightQueen = createEightQueen();
					Queen8 queen = new Queen8(eightQueen);
					int temp = queen.simulateAneal();
				    if(temp > 0) {
				    	success_num++;
				    	total_cost += temp;
				    } else {
				    	fail_num++;
				    }
				}
				double ave_cost = total_cost;
				ave_cost /= success_num;
				System.out.print("The average cost for SimulateAneal is" + ave_cost+"\n");
				double solve_rate = success_num;
				solve_rate /= (success_num+fail_num);
				System.out.print("The solve rate for SimulateAneal is" + solve_rate + "\n");
				System.out.print("\n");
			}
		}
	}


	public static Queen[] createEightQueen()
	{
		int queenPos;
		Queen[] queens = new Queen[8];
		for (int i = 0; i < 8; i++)
		{
			queenPos = 0 + (int)(Math.random()*8);
			queens[i] = new Queen(queenPos,i);
		}
		return queens;
	}

}
