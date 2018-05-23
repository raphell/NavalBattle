
public interface Player {
	
	public final int NUMBER_OF_CARRIER = 1;
	public final int NUMBER_OF_BATTLESHIP = 1;	
	public final int NUMBER_OF_CRUISER = 1;
	public final int NUMBER_OF_SUBMARINE = 1;
	public final int NUMBER_OF_DESTROYER = 1;
	
	public final int LENGTH_OF_CARRIER = 5;
	public final int LENGTH_OF_BATTLESHIP = 4;	
	public final int LENGTH_OF_CRUISER = 3;
	public final int LENGTH_OF_SUBMARINE = 3;
	public final int LENGTH_OF_DESTROYER = 2;

	public final int TOTAL_NUMBER_OF_SHIP = NUMBER_OF_CARRIER+NUMBER_OF_BATTLESHIP+NUMBER_OF_CRUISER+NUMBER_OF_SUBMARINE+NUMBER_OF_DESTROYER;
	
	public GridForShip getShipGrid();
	
	public Grid getShotGrid();
	
	public void shipConstructor(int numberToCreate, int lenght, String type);
	
	public String isPlayerTouched(Shot t);
	
	public boolean lost();
	
	public int[] askShipCoordinates(int taille);
	
	public Shot shoot();
	
	public void displayGrids();
	
	public abstract void addShotToFinder( Shot t);

	public void reinitFinder();
}
