
import java.util.ArrayList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Queen8 {
	Queen[] queen;
	int totalCount = 0;
	public Queen8(){
		
	}
	public Queen8(Queen[] queen){
		this.queen = queen;
	}
	public int hillClimb(){
		int conflict = h(queen);
		boolean success = false;
		int[] min = new int[1];
		min[0] = conflict;
		int count = 0;
		if(conflict == 0){
			totalCount = count;
			success = true;
		}
		else{
			while(true){
				
				ArrayList<Queen> nextAvailableMove = new ArrayList<Queen>();
				nextAvailableMove = generateNextState(queen,min);
				int size = nextAvailableMove.size()-1;
				int nextQueen = 0 + (int)(Math.random()*size);
				if(min[0] == 0){
					this.queen = moveQueen(queen,nextAvailableMove.get(nextQueen));
					totalCount = count;
					
					success = true;
					break;
				}
				if(conflict == min[0] && min[0] != 0){
					totalCount = count;
					
					break;
				}
				else{
					this.queen = moveQueen(queen,nextAvailableMove.get(nextQueen));
					conflict = min[0];
					count++;
					
				}
			}
		}
		if (!success) {
			count = -count;
		}
		return count;
	}
	public Queen[] moveQueen(Queen[] queens, Queen queenToMove){
		for(int i=0;i<queens.length;i++){
			if(queens[i].y == queenToMove.y){
				queens[i].x = queenToMove.x;
			}
		}
		return queens;
	}
	public int randomHillClimb(){
		int sum=0;
		while(hillClimb() < 0){
			this.queen = Test.createEightQueen();
			sum +=totalCount;
		}
		sum +=totalCount;
		
		return sum;
	}
	public int simulateAneal() {
		double[] schedule = new double[25000];
		int sum = 0;
		int conflict;
		for(int i=0;i<schedule.length;i++){
			schedule[i] = 35 - (35.0/25000.0)*(i+1);
			
			conflict = h(queen);
			double T = schedule[i];
			

			if(T == 0){
				if(conflict != 0){
				
					sum = -(i+1);
				}
				else{
				
					sum = i+1;
				}
				
				break;
			}
			
			if(conflict == 0){
				
				sum = i+1;
				break;
			}
			
			Queen[] newTemp = new Queen[8];
			for(int a=0;a<8;a++){
				newTemp[a] = new Queen(queen[a].x,queen[a].y); 
			}
			Queen[] newQueens = randomGetStates(newTemp);
			
			int E = h(queen) - h(newQueens);
			if(E > 0){
				this.queen = newQueens;
			}
			else{
				double value = E/(double)T;
				double posibility = Math.expm1(value)+1.0;
				if(Math.random() < posibility){
					this.queen = newQueens;
				}	
			}
		}
		return sum;
	}
	public Queen[] randomGetStates(Queen[] queens){
		int queenPosY = 0 + (int)(Math.random()*8);
		int queenPosX = 0 + (int)(Math.random()*8);
		
		while(true){
			if(queens[queenPosY].x == queenPosX){
				queenPosX = 0 + (int)(Math.random()*7);
			}
			else{
				queens[queenPosY].y = queenPosY;
				queens[queenPosY].x = queenPosX;
				break;
			}
		}
		return queens;
		
	}
	
	public int h(Queen[] queens){
		int Totalconflict = 0;
		for(int i=0;i<queens.length;i++){
			for(int j=i+1;j<queens.length;j++){
				if(queens[i].x == queens[j].x || Math.abs(queens[i].x - queens[j].x) == Math.abs(queens[i].y - queens[j].y)){
					Totalconflict++;
				}
			}
		}
		return Totalconflict;
	}
	public ArrayList<Queen> generateNextState(Queen[] queens,int[] min){
		
		int[][] board = new int[8][8];
		ArrayList<Queen> minQueen = new ArrayList<Queen>();
		for(int j=0;j<board.length;j++){
			for(int i=0;i<board[j].length;i++){
				if(i != queens[j].x && queens[j].y == j){
					Queen tempQueen = new Queen(i,j);
					Queen[] newTemp = new Queen[8];
					for(int a=0;a<8;a++){
						newTemp[a] = new Queen(queens[a].x,queens[a].y); 
					}
					Queen[] moveQueen = moveQueen(newTemp, tempQueen);
					board[i][j] = h(moveQueen);
					
					if(min[0] > board[i][j]){
						min[0] = board[i][j];
					}
				}
				else{
					board[i][j] = -1;
				}	
			}
		}
		
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				if(board[i][j] == min[0]){
					Queen minQueen2 = new Queen(i,j);
					minQueen.add(minQueen2);
				}
			}
		}
		return minQueen;
	}
}

