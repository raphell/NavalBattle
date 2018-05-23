
public class Ship {

	private int lenght;
	private Square squares[];
	private String typeOfShip;
	

	Ship(int[] coordinates, String type){			//prend les deux cases en entree (sous la forme x1,y1,x2,y2), verification de coherence deja faite
		
		int xBox = coordinates[2];
		int yBox = coordinates[3];
		int i=0;
		
		this.typeOfShip = type;
																			//creation of the ship's squares
		if (coordinates[0]==coordinates[2]){					//si les X sont egaux
			if (coordinates[1]<coordinates[3]){
				this.lenght = coordinates[3] - coordinates[1] +1;
				this.squares = new Square[lenght];
				while(coordinates[1]<=yBox){
					squares[i]= new Square(xBox, yBox);
					yBox = yBox-1;
					i=i+1;
				}
			}
			else{
				this.lenght = coordinates[1] - coordinates[3] +1;
				this.squares = new Square[lenght];
				while(yBox<=coordinates[1]){
					squares[i]= new Square(xBox, yBox);
					yBox = yBox+1;
					i=i+1;
				}
			}
		}
		else{									//si les Y sont egaux
			if (coordinates[0]<coordinates[2]){
				this.lenght = coordinates[2] - coordinates[0] +1;
				this.squares = new Square[lenght];
				while(xBox>=coordinates[0]){
					squares[i]= new Square(xBox, yBox);
					xBox = xBox-1;
					i=i+1;
				}
			}
			else{
				this.lenght = coordinates[0] - coordinates[2] +1;
				this.squares = new Square[lenght];
				while(xBox<=coordinates[0]){
					squares[i]= new Square(xBox, yBox);
					xBox = xBox+1;
					i=i+1;
				}
			}
		}
	}

	public int getLongueur() {
		return this.lenght;
	}

	public Square[] getShipBoxes() {
		return this.squares;
	}

	boolean isShipTouched(Shot t)
	{
		boolean result = false;
		for (int i=0 ; i<lenght ; i=i+1) {
			if (squares[i].matches(t.getShotX(), t.getShotY())) {
				result = true;
			}
		}
		return result;
	}

	
	public String getTypeOfShip(){
		return this.typeOfShip;
	}

	
	boolean isShipDestroyed(){
		for(Square c : squares){
			if(!c.isBoxDestroyed())
				return false ;
		}
		return true;
	}


}
