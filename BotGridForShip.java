
public class BotGridForShip extends GridForShip {
	
	//todo : faire des constantes pr les numero des elements de la carte
	

	
	BotGridForShip(int nbrOfShip){
		super(nbrOfShip);
	}
	
	@Override
	void shipToGrid(int x, int y){	
		this.shotTab[y*10+x] = 1 ;		//pour le bateau

		if (x!=0){
			if (this.shotTab[(y*10+x)-1]!=1){			//pour les cases autour
				this.shotTab[(y*10+x)-1] = 3 ;		
			}
		}
		if (x!=9){
			if(this.shotTab[(y*10+x)+1]!=1){
				this.shotTab[(y*10+x)+1] = 3 ;
			}
		}
		if(y!=0){
			if (this.shotTab[(y-1)*10+x]!=1){
				this.shotTab[(y-1)*10+x] = 3 ;
			}
		}
		if(y!=9){
			if (this.shotTab[(y+1)*10+x]!=1){
				this.shotTab[(y+1)*10+x] = 3 ;
			}
		}
	}
	
	
	@Override
	boolean addToGrid(Ship ship, int lenght){
		Square[] shipBoxes = ship.getShipBoxes();
		boolean valid = true ;
		int cX, cY;
		for (int j=0 ; j<lenght ; j=j+1){				//on verifie pour chaque case si un bateau est deja  sur cette case
			cX = shipBoxes[j].getX() ;
			cY = shipBoxes[j].getY() ;
			if (estOccupe(cX, cY)){
				valid = false;
			}
		}
		
		if (valid){
			for (int j=0 ; j<lenght ; j=j+1){		//si toutes les cases sont libres on modifie la grille pr acceuillir le bateau
				cX = shipBoxes[j].getX() ;
				cY = shipBoxes[j].getY() ;
				this.shipToGrid(cX, cY);
			}
			this.shipTab[this.numberOfShips] = ship;		//et on ajoute le bateau dans le tableau de bateau
			this.numberOfShips = this.numberOfShips+1;
		}
		return valid ;
	}
	
	
	
	
	@Override 
	boolean estOccupe(int x, int y){ //les cases seront : 1 si bateau, 2 si bateau touche, 0 si vide, 4 si vide touche, 3 si zone a cote d'un bateau
		if (this.shotTab[y*10+x]==1 || this.shotTab[y*10+x]==3 ){
			return true ;
		}
		else{
			return false;
		}
	}


}
