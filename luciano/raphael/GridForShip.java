package luciano.raphael;

public class GridForShip extends Grid {
	
	
	protected final int TOTAL_NUMBER_OF_BOAT;
	protected Ship shipTab[];
	protected int numberOfShips;
	
	GridForShip(int nbrOfShip){
		super();
		this.numberOfShips = 0;
		this.TOTAL_NUMBER_OF_BOAT = nbrOfShip;
		this.shipTab = new Ship[TOTAL_NUMBER_OF_BOAT];
	}
	
	
	public String isTouched(Shot t) {
		String shotResult = "loupe" ;
		int hitShipIndex = 0 ;
		for (int i=0 ; i<TOTAL_NUMBER_OF_BOAT ; i=i+1){
			if ( this.shipTab[i].isShipTouched(t) ){
				hitShipIndex = i;
				shotResult = "touche";
			}
		}
		if (shotResult =="touche"){
			if( this.shipTab[hitShipIndex].isShipDestroyed() ){
				shotResult = "sank a "+this.shipTab[hitShipIndex].getTypeOfShip() ;
			}
		}
		return shotResult ;
	}
	
	public int getNbrOfShip(){
		return this.numberOfShips;
	}
	
	public void setNbrOfShip(int i){
		this.numberOfShips = i;
	}
	
	void shipToGrid(int x, int y){	
		this.shotTab[y*10+x] = 1 ;
	}
	
	void enemyFailShotToGrid(Shot t){
		int indice = t.getShotX() + t.getShotY()*10 ;
		if(this.shotTab[indice]!=2){
			this.shotTab[indice] = 4 ;
		}
	}
	
	void enemyShipShotToGrid(Shot t){
		int indice = t.getShotX() + t.getShotY()*10 ;
		this.shotTab[indice] = 2 ;
	}
	
	
	
	boolean addToGrid(Ship ship, int lenght){
		Square[] shipBoxes = ship.getShipBoxes();
		boolean valid = true ;
		int cX, cY;
		for (int j=0 ; j<lenght ; j=j+1){				//on verifie pour chaque case si un bateau est deja sur cette case
			cX = shipBoxes[j].getX() ;
			cY = shipBoxes[j].getY() ;
			if (estOccupe(cX, cY)){
				valid = false;
			}
		}
		
		if (valid){
			for (int j=0 ; j<lenght ; j=j+1){		//si toutes les cases sont libres on modifie la grille pourr acceuillir le bateau
				cX = shipBoxes[j].getX() ;
				cY = shipBoxes[j].getY() ;
				this.shipToGrid(cX, cY);
			}
			this.shipTab[this.getNbrOfShip()] = ship;		//et on ajoute le bateau dans le tableau de bateau
			this.setNbrOfShip(this.getNbrOfShip()+1); 
		}
		return valid ;
	}
	
	
	boolean estOccupe(int x, int y){ //les cases seront : 1 si bateau, 2 si bateau touche, 0 si vide, 4 si vide touche
		if (this.shotTab[y*10+x]==1){
			return true ;
		}
		else{
			return false;
		}
	}
	
	
	void displayGrid(){
		System.out.println("Ship Grid :");
		System.out.print(" ");
		for (int i=0 ; i<10 ; i=i+1){
			System.out.print(" ");
			System.out.print(i);
		}
		System.out.println(" ");
		System.out.print("A ");
		for (int i=0 ; i<100 ; i=i+1){
			if (getCase(i)==0 || getCase(i)==3){
				System.out.print("~");	//it's water
			}
			else if(getCase(i)==1){
				System.out.print("#");	//untouched ship
			}
			else if(getCase(i)==2){
				System.out.print("X");		//touched ship
			}
			else if(getCase(i)==4){
				System.out.print("o");		//missed enemy shot
			}
			else{
				System.out.print(getCase(i));	//shouldn't happen
			}
			System.out.print(" ");
			if(i==9 || i==19 || i==29 || i==39 || i==49 || i==59 || i==69 || i==79 || i==89 || i==99){
				System.out.println("");
				if (i != 99){
					int line = i/10;
					char lineLetter =(char)(line+66);
					System.out.print(lineLetter+" ");
				}
			}
		}
		System.out.println(" ");
	}
	
	
	

	public int getNumberMaxOfShips() {
		return TOTAL_NUMBER_OF_BOAT ;
	}
	
	
	
	void displayShipGridLine(int line){
		char lineLetter =(char)(line+65);
		System.out.print(lineLetter);
		System.out.print(" ");
		for (int i=line*10 ; i<line*10+10 ; i=i+1){
			if (getCase(i)==0 || getCase(i)==3){
				System.out.print("~");	//it's water
			}
			else if(getCase(i)==1){
				System.out.print("#");	//untouched ship
			}
			else if(getCase(i)==2){
				System.out.print("X");		//touched ship
			}
			else if(getCase(i)==4){
				System.out.print("o");		//missed enemy shot
			}
			System.out.print(" ");
		}
	}

}
