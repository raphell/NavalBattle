
public class Game {
	private Player player1 ;
	private Player player2;
	
	Game(int j1, int j2){
		System.out.println("Joueur 1 : rentrez les coordonnees de vos bateaux");
		switch (j1) {
	        case 1:  this.player1 = new HumanPlayer();
	                 break;
	        case 2:  this.player1 = new BotPlayerLevel1();
	                 break;
	        case 3:  this.player1 = new BotPlayerLevel2();
	                 break;
	        case 4:  this.player1 = new BotPlayerLevel3();
	                 break;
		}
		
		System.out.println("Joueur 2 : rentrez les coordonnees de vos bateaux");
		switch (j2) {
			case 1:  this.player2 = new HumanPlayer();
			        break;
			case 2:  this.player2 = new BotPlayerLevel1();
			        break;
			case 3:  this.player2 = new BotPlayerLevel2();
			        break;
			case 4:  this.player2 = new BotPlayerLevel3();
			        break;
		}
	}
	
	public int playAGame(){
		while(!(this.isFinished())){
			roundOfPlay(player1,player2);
			if(!(this.isFinished())){
				roundOfPlay(player2,player1);
			}
		}
		System.out.println("END OF THE GAME");
		if (player1.lost()){
			return 2;
		}
		else{
			return 1;
		}
	}
	
	
	private void roundOfPlay( Player jActif, Player jPassif){
		if (jActif == this.player1){
			System.out.println("/////////////////         PLAYER  1       /////////////////////////////////////");
		}
		else{
			System.out.println("/////////////////         PLAYER  2       /////////////////////////////////////");
		}
		
		jActif.displayGrids() ;
		
		Shot tir = jActif.shoot() ;
		String result = jPassif.isPlayerTouched(tir);
		if (result=="touche" ){
			jPassif.getShipGrid().enemyShipShotToGrid(tir);
			jActif.getShotGrid().shipShotToGrid(tir);
			System.out.println("It's a hit !");
			jActif.addShotToFinder(tir);
		}
		else if (result.matches("sank a [A-Z][a-z]*")){
			System.out.println("You "+result);
			jActif.getShotGrid().shipShotToGrid(tir);
			jPassif.getShipGrid().enemyShipShotToGrid(tir);
			jActif.reinitFinder();
		}
		else if(result=="loupe"){
			System.out.println("It's a miss");
			jActif.getShotGrid().failShotToGrid(tir);
			jPassif.getShipGrid().enemyFailShotToGrid(tir);
		}
	}
	
	
	boolean isFinished(){
		if (player1.lost() || player2.lost()){
			return true ;
		}
		else{
			return false;
		}
	}

}
