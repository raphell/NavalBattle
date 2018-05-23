
public class Square {
	private int x;
	private int y;
	private boolean destroyed ;

	Square(int X,int Y){
		this.x = X;
		this.y = Y;
		this.destroyed = false;
	}
	
	boolean matches(int X, int Y){
		if (this.x==X && this.y==Y ){
			System.out.println("x : "+X);
			System.out.println("y : "+Y);
		}
		if (this.x==X && this.y==Y && !(this.isBoxDestroyed())){
			this.destroyed = true;				//on detruit la case et on renvoi true car ca a touche
			return true ;
		}
		else{
			return false;
		}
	}
	
	boolean isBoxDestroyed(){
		return this.destroyed ;
	}

	int getX(){
		return this.x ;
	}
	
	int getY(){
		return this.y ;
	}
}
