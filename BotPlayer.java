
import java.lang.Math;

//todo : faire des constantes pr le nombre de bateaux � cr�er

public abstract class BotPlayer implements Player {
	private BotGridForShip shipGrid;
	private Grid shotGrid;
	private int nbrOfShipAlive;
	
	
	BotPlayer(){
		this.shipGrid = new BotGridForShip(TOTAL_NUMBER_OF_SHIP);
		this.shotGrid = new Grid();
		this.nbrOfShipAlive = this.shipGrid.getNumberMaxOfShips() ;

		this.shipConstructor(NUMBER_OF_CARRIER,LENGTH_OF_CARRIER,"Carrier");
		this.shipConstructor(NUMBER_OF_BATTLESHIP,LENGTH_OF_BATTLESHIP,"Battleship");
		this.shipConstructor(NUMBER_OF_CRUISER,LENGTH_OF_CRUISER,"Cruiser");
		this.shipConstructor(NUMBER_OF_SUBMARINE,LENGTH_OF_SUBMARINE,"Submarine");
		this.shipConstructor(NUMBER_OF_DESTROYER,LENGTH_OF_DESTROYER,"Destroyer");
	}
	
	public BotGridForShip getShipGrid(){
		return this.shipGrid ;
	}
	
	public Grid getShotGrid(){
		return this.shotGrid ;
	}
	
	public String isPlayerTouched(Shot t){
		String shotResult = shipGrid.isTouched(t);
		if (shotResult.matches("sank a [A-Z][a-z]*")){
			this.setNbrOfShipAlive(this.getNbrOfShipAlive()-1) ;
		}
		return shotResult ;
	}
	
	private int getNbrOfShipAlive() {
		return this.nbrOfShipAlive;
	}

	private void setNbrOfShipAlive(int i) {
		this.nbrOfShipAlive=i;
		
	}

	public boolean lost(){
		if (this.nbrOfShipAlive==0){
			return true ;
		}
		else{
			return false ;
		}
	}
	
	public void shipConstructor(int numberToCreate, int lenght,String type) {

		boolean validShip = false ;
		int[]coordinates = new int[4];
		Ship ship;
		for (int i=0 ; i<numberToCreate ; i=i+1){
			coordinates = this.askShipCoordinates(lenght);
			ship = new Ship(coordinates, type);
			System.out.println("X1: "+coordinates[0]);
			System.out.println("Y1: "+coordinates[1]);
			System.out.println("X2: "+coordinates[2]);
			System.out.println("Y2: "+coordinates[3]);
			validShip = this.shipGrid.addToGrid(ship, ship.getLongueur());			//on essaye de l'integrer a la grille
			while(!validShip){
				coordinates = this.askShipCoordinates(lenght);
				ship = new Ship(coordinates, type);
				validShip = this.shipGrid.addToGrid(ship, ship.getLongueur());			//et on reessaye d'integrer
			}
			this.shipGrid.displayGrid();
		}
	}
	
	
	
	public int[] askShipCoordinates(int taille){
		int result[] = new int[4];
		int y1 = 0;
		int y2 = 0;
		int x1 = 0;
		int x2 = 0;
		System.out.println("Random creation of a ship, with a lenght of "+ taille +" for BOT player");
		int sens = (int)(Math.random()*2);
		if (sens==0){		//vertical ship
			x1 = (int)(Math.random()*10);
			x2 = x1;
			
			y1 = (int)(Math.random()*(10-taille+1));
			y2 = y1+taille-1;
		}
		else{		//horizontal ship
			y1 = (int)(Math.random()*10);
			y2 = y1;
			
			x1 = (int)(Math.random()*(10-taille+1));
			x2 = x1+taille-1;
		}
		result[0]= x1 ;
		result[1]= y1 ;
		result[2]= x2 ;
		result[3]= y2 ;
		return result ;
	}
	
	
	
	//shoot() : asks the player for shooting coordinates and return them, abstract because defined in the "BotPlayerLevelX" classes
	public abstract Shot shoot();
	
	public abstract void addShotToFinder( Shot t);
	
	public abstract void reinitFinder();

	public void displayGrids() {
		System.out.println(" ");
		System.out.println("       your ships                               your shots");
		System.out.println(" ");
		System.out.println("  0 1 2 3 4 5 6 7 8 9                      0 1 2 3 4 5 6 7 8 9");
		for (int i=0 ; i<10 ; i=i+1){
			this.getShipGrid().displayShipGridLine(i);
			System.out.print("                   ");
			this.getShotGrid().displayGridLine(i);
			System.out.println(" ");
		}
		System.out.println(" ");
		System.out.println("# : ship         X : hit        o : Miss");
		System.out.println(" ");
		
	}
	
	
	
}
