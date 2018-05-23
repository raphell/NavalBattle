package fr.battleship;
import java.io.FileWriter;
import java.io.IOException;

import luciano.raphael.Game;


public class TestIA {
	
	public static void main(String[] args){
		int scoreEasyAgainstMedium = 0;
		int scoreMediumAgainstEasy = 0;
		
		int scoreMediumAgainstHard = 0;
		int scoreHardAgainstMedium = 0;
		
		int scoreEasyAgainstHard = 0;
		int scoreHardAgainstEasy = 0;
		
		boolean invert = false;
		Game game ;
		
		//TODO voir ou gerer l'inversion du joueur qui commence, ici ou dans game ?
		
		
		for(int i=0 ; i<100 ; i++){
			if (invert){					//in order to change the beginning IA
				game = new Game(2,3);
				if(game.playAGame()==1){	//easy win
					scoreEasyAgainstMedium++;
				}
				else{ 						//medium win
					scoreMediumAgainstEasy++;
				}
				invert = !invert;
			}
			else{
				game = new Game(3,2);
				if(game.playAGame()==1){	//medium win
					scoreMediumAgainstEasy++;
				}
				else{ 						//easy win
					scoreEasyAgainstMedium++;
				}
				invert = !invert;
			}
		}
		
		for(int i=0 ; i<100 ; i++){
			if(invert){
				game = new Game(3,4);
				if(game.playAGame()==1){	//medium win
					scoreMediumAgainstHard++;
				}
				else{ 						//hard win
					scoreHardAgainstMedium++;
				}
				invert = !invert;
			}
			else{
				game = new Game(4,3);
				if(game.playAGame()==1){	//hard win
					scoreHardAgainstMedium++;
				}
				else{ 						//medium win
					scoreMediumAgainstHard++;
				}
				invert = !invert;
			}
		}
		
		for(int i=0 ; i<100 ; i++){
			if(invert){
				game = new Game(2,4);
				if(game.playAGame()==1){	// easy win
					scoreEasyAgainstHard++;
				}
				else{ 						// hard win
					scoreHardAgainstEasy++;
				}
				invert = !invert;
			}
			else{
				game = new Game(4,2);
				if(game.playAGame()==1){	// hard win
					scoreHardAgainstEasy++;
				}
				else{ 						// easy win
					scoreEasyAgainstHard++;
				}
				invert = !invert;
			}
			
		}
		
		FileWriter writer = null;
		try{
			writer = new FileWriter("ai_proof.csv");
			
			String titleOfCSV = "AI name,score,AI Name2,score2\n";
			String easyVmedium = "AI Level Beginner,"+scoreEasyAgainstMedium+",Level Medium,"+scoreMediumAgainstEasy+"\n";
			String easyVhard = "AI Level Beginner,"+scoreEasyAgainstHard+",Level Hard,"+scoreHardAgainstEasy+"\n";
			String mediumVhard = "AI Medium,"+scoreMediumAgainstHard+",Level Hard,"+scoreHardAgainstMedium+"\n";
			
	        writer.append(titleOfCSV);
	        writer.append(easyVmedium);
	        writer.append(easyVhard);
	        writer.append(mediumVhard);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				writer.flush();
				writer.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
        System.out.println("Your CSV file is created !");  
	}

}