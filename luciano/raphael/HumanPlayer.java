package luciano.raphael;
import java.util.Scanner;

public class HumanPlayer implements Player{
	
	private GridForShip shipGrid;
	private Grid shotGrid;
	private int nbrOfShipAlive;
	
	
	HumanPlayer(){
		this.shipGrid = new GridForShip(TOTAL_NUMBER_OF_SHIP);
		this.shotGrid = new Grid();
		this.nbrOfShipAlive = this.shipGrid.getNumberMaxOfShips() ;

		this.shipConstructor(NUMBER_OF_CARRIER,LENGTH_OF_CARRIER,"Carrier");
		this.shipConstructor(NUMBER_OF_BATTLESHIP,LENGTH_OF_BATTLESHIP,"Battleship");
		this.shipConstructor(NUMBER_OF_CRUISER,LENGTH_OF_CRUISER,"Cruiser");
		this.shipConstructor(NUMBER_OF_SUBMARINE,LENGTH_OF_SUBMARINE,"Submarine");
		this.shipConstructor(NUMBER_OF_DESTROYER,LENGTH_OF_DESTROYER,"Destroyer");
	}
	
	public GridForShip getShipGrid(){
		return this.shipGrid ;
	}
	
	public Grid getShotGrid(){
		return this.shotGrid ;
	}
	
	private int getNbrOfShipAlive(){
		return this.nbrOfShipAlive;
	}
	
	private int setNbrOfShipAlive(int nbr){
		return this.nbrOfShipAlive = nbr;
	}
	
	
	
	 public void shipConstructor(int numberToCreate, int lenght, String type) {
		boolean validShip = false ;
		int[]coordonnees = new int[4];
		Ship ship;
		for (int i=0 ; i<numberToCreate ; i=i+1){
			coordonnees = this.askShipCoordinates(lenght);
			ship = new Ship(coordonnees, type);
			validShip = this.shipGrid.addToGrid(ship, ship.getLongueur());			//on essaye de l'integrer a la grille
			while(!validShip){
				System.out.println("Error, a ship is already there");  //si ca marche pas on redemande
				coordonnees = this.askShipCoordinates(lenght);
				ship = new Ship(coordonnees, type);
				validShip = this.shipGrid.addToGrid(ship, ship.getLongueur());			//et on reessaye d'integrer
			}
			this.shipGrid.displayGrid();
		}
	}
	
	



	public String isPlayerTouched(Shot t){
		String shotResult = shipGrid.isTouched(t);
		if (shotResult.matches("sank a [A-Z][a-z]*")){
			this.setNbrOfShipAlive(this.getNbrOfShipAlive()-1) ;
		}
		return shotResult ;
	}

	
	
	
	public boolean lost(){
		if (this.getNbrOfShipAlive()==0){
			return true ;
		}
		else{
			return false ;
		}
	}
	
	
	public int[] askShipCoordinates(int taille){
		int result[] = new int[4];
		int y1 = 0;
		int y2 = 0;
		int x1 = 0;
		int x2 = 0;
		Scanner reader = new Scanner(System.in);
		boolean validSquare = false ;
		
		System.out.println("create a ship, with a lenght of " + taille);
		
		while (!validSquare){
			System.out.println("Enter the Y coordinate for the first square of the ship (between A and J)");		
			y1 = (int)reader.next().charAt(0);	
			while (y1<97 || y1>106){
				System.out.println("Error for the Y coordinate (must be between A and J)");
				y1 = reader.next().charAt(0);																	
			}
			y1 = y1-97;
			
			System.out.println("Enter the X coordinate  for the first square of the ship (between 0 and 9)");
			x1 = ((int)reader.next().charAt(0))-48;
			while (x1<0 || x1>9){
				System.out.println("Error for the X coordinate (must be between 0 and 9)");
				x1 = ((int)reader.next().charAt(0))-48;
			}

			System.out.println("Enter the Y coordinate for the first square of the ship (between A and J)");
			y2 = (int)reader.next().charAt(0);																	
			while (y2<97 || y2>106){
				System.out.println("Error for the Y coordinate (must be between A and J)");
				y2 = reader.next().charAt(0);																
			}
			y2 = y2-97;
			
			System.out.println("Enter the X coordinate  for the first square of the ship (between 0 and 9)");
			x2 = ((int)reader.next().charAt(0))-48;
			while (x2<0 || x2>9){
				System.out.println("Error for the X coordinate (must be between 0 and 9)");
				x2 = ((int)reader.next().charAt(0))-48;
			}
			
			if(((x1==x2) && (y2-y1+1==taille || y1-y2+1==taille)) || ((y1==y2) && (x2-x1+1==taille || x1-x2+1==taille)) ){
				validSquare = true ;
			}
			else{
				System.out.println("Error,coordinates aren't valid");
			}
		}
		//reader.close();
		result[0]= x1 ;
		result[1]= y1 ;
		result[2]= x2 ;
		result[3]= y2 ;
		return result ;
	}
	
	
	
	//shoot() : asks the player for shooting coordinates and return them
	
	public Shot shoot(){
		int x = 0;
		int y = 0;
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the Y coordinate for your shot (from A to J)");
		y = (int)reader.next().charAt(0);																								
		while (y<97 || y>106){		//if Y coordinates not valid
			System.out.println("Error for the Y coordinate (must be between A and J)");
			y = (int)reader.next().charAt(0);																		
		}
		y = y-97;
		
		System.out.println("Enter the X coordinate for your shot (between A and J)");
		x = ((int)reader.next().charAt(0))-48;
		while ( x<0 || x>9 ){					//if X coordinates not valid
			System.out.println("Error for the X coordinate (must be between 0 and 9)");
			x = ((int)reader.next().charAt(0))-48;																
		}
		
		//reader.close();
		return new Shot(x,y);
	}

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
	
	public void addShotToFinder(Shot t) {}

	public void reinitFinder() {}
	
}
