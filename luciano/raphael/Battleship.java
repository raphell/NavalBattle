package luciano.raphael;
import java.util.Scanner;
//todo : penser a close les scanner

public class Battleship {

	public static void main(String[] args) {
		int j2Mode ;
		int winner = 0 ;
		Game game ;
		boolean whoBegin = true ;
		Scanner reader = new Scanner(System.in);
		
		System.out.println("To play a two player game, type 1");
		System.out.println("To play against an easy AI, type 2");
		System.out.println("To play against an medium AI, type 3");
		System.out.println("To play against an hard AI, type 4");
		j2Mode = ((int)reader.next().charAt(0))-48;
		//if (mode.getClass){
			
		//}
		while(j2Mode<1 || j2Mode>4){
			System.out.println("YOU DIDN'T TYPE 1, 2, 3 OR 4, but you can try again");
			System.out.println("To play a two player game, type 1");
			System.out.println("To play against an easy AI, type 2");
			System.out.println("To play against an medium AI, type 3");
			System.out.println("To play against an hard AI, type 4");
			j2Mode = ((int)reader.next().charAt(0))-48;
		}
		
		while(j2Mode!=5){
			if(whoBegin){
				game = new Game(1, j2Mode);
				winner = game.playAGame();
				whoBegin = false;
			}
			else{
				game = new Game(j2Mode, 1 );
				winner = game.playAGame();
				whoBegin = true;
			}
			
			System.out.println("The winner is the player "+winner);
			
			System.out.println("To play again a two player game, type 1");
			System.out.println("To play again against an easy AI, type 2");
			System.out.println("To play again against an medium AI, type 3");
			System.out.println("To play again against an hard AI, type 4");
			System.out.println("To stop the game, type 5");
			j2Mode = ((int)reader.next().charAt(0))-48;
			while(j2Mode<1 || j2Mode>5){
				System.out.println("YOU DIDN'T TYPE 1, 2, 3, 4 OR 5, but you can try again");
				System.out.println("To play a two player game, type 1");
				System.out.println("To play again against an easy AI, type 2");
				System.out.println("To play again against an medium AI, type 3");
				System.out.println("To play again against an hard AI, type 4");
				System.out.println("To stop the game, type 5");
				j2Mode = ((int)reader.next().charAt(0))-48;
			}
		}
		
		reader.close();
	}
		//TestIA truc = new TestIA();
		
}
