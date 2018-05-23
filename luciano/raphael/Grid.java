package luciano.raphael;
public class Grid {
	protected int shotTab[];
	
	Grid(){
		this.shotTab = new int[]{0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,0,0};
	}
	
	void failShotToGrid(Shot t){
		int index = t.getShotX() + t.getShotY()*10 ;
		this.shotTab[index] = 1;
	}
	
	void shipShotToGrid(Shot t){
		int index = t.getShotX() + t.getShotY()*10 ;
		this.shotTab[index] = 2;
	}
	
	int getCase(int boxNumber){
		return this.shotTab[boxNumber];
	}
	
	
	
	void displayGridLine(int line){
		char lineLetter =(char)(line+65);
		System.out.print(lineLetter);
		System.out.print(" ");
		for (int i=line*10 ; i<line*10+10 ; i=i+1){
			if (getCase(i)==0){
				System.out.print("~");	//c'est de l'eau
			}
			else if(getCase(i)==1){
				System.out.print("o");	//tir rate
			}
			else if(getCase(i)==2){
				System.out.print("X");		//tir reussi
			}
			System.out.print(" ");
		}
	}

	public boolean alreadyShot(int x, int y) {
		if(this.shotTab[y*10 +x]==1 || this.shotTab[y*10 +x]==2){
			return true;
		}
		else{
			return false;
		}
	}
}
