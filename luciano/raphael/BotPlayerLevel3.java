package luciano.raphael;

public class BotPlayerLevel3 extends BotPlayer{
	
	private Shot firstSucceedShot;
	private Shot secondSucceedShot;
	private char direction;
	private boolean side;
	private int tryShot ;
	
	public BotPlayerLevel3(){
		super();
		this.firstSucceedShot = null;
		this.secondSucceedShot = null;
		this.direction = 'E';
		this.tryShot = 0;
		this.side = true;
	}
	
	
	private boolean getSide(){
		return this.side;
	}
	
	private void setSide(boolean b){
		this.side = b;
	}
	
	private int getTryShot(){
		return this.tryShot;
	}
	
	private void setTryShot(int i){
		this.tryShot = i;
	}
	
	private char getDirectionShot(){
		return this.direction;
	}
	
	private void setDirectionShot(char i){
		this.direction = i;
	}
	
	private Shot getFirstSucceedShot(){
		return this.firstSucceedShot;
	}
	
	private void setFirstSucceedShot(Shot t){
		this.firstSucceedShot = t;
	}
	
	private Shot getSecondSucceedShot(){
		return this.secondSucceedShot;
	}
	
	private void setSecondSucceedShot(Shot t){
		this.secondSucceedShot = t;
	}

	
	
	@Override
	public Shot shoot() {
		if(this.firstSucceedShot!=null){
			System.out.println("firstX :"+this.firstSucceedShot.getShotX());
			System.out.println("firstY :"+this.firstSucceedShot.getShotY());
		}
		if(this.secondSucceedShot!=null){
			System.out.println("secondX :"+this.firstSucceedShot.getShotX());
			System.out.println("secondY :"+this.firstSucceedShot.getShotY());
		}
		System.out.println("direction :"+this.direction);
		System.out.println("tryshot :"+this.tryShot);
		System.out.println("side :"+this.side);

		boolean valid = false;
		char dir;
		int x = 0;
		int y = 0;
		Shot first ;
		Shot second ;
		
		while(!valid){
			first = this.getFirstSucceedShot();
			second = this.getSecondSucceedShot();
			if(first==null){						//no ship spotted
				System.out.println("no ship");
				 x = (int)(Math.random()*10);
				 y = (int)(Math.random()*10);
				 while(this.getShotGrid().alreadyShot(x, y)){		//it understand it's a bad idea to shoot at a box it already shot before
						x = (int)(Math.random()*10);
						y = (int)(Math.random()*10);
				 }
				 valid = true;
			}
			else if(second==null){					//a ship is spotted but it doesn't know where to shot after, so it tries all position around
				System.out.println("");
				System.out.println("have a first ship");
				System.out.println("tryshot :"+this.tryShot);
				System.out.println("direction :"+this.direction);
				if(this.firstSucceedShot!=null){
					System.out.println("firstX :"+this.firstSucceedShot.getShotX());
					System.out.println("firstY :"+this.firstSucceedShot.getShotY());
				}
				else{
					System.out.println("first ship is null");
				}
				System.out.println("first :"+first);
				
				if (this.getTryShot()==0 ){
					System.out.println("tries to shot below the firstSucceedShot");
					if((first.getShotY()+1)<10){
						x = first.getShotX();		//tries to shot below the firstSucceedShot
				 		y = first.getShotY()+1;
				 		valid = true;
					}
					this.setTryShot(this.getTryShot()+1);
				}
				else if(this.getTryShot()==1 ){
					System.out.println("try to shot to the right of the firstSucceedShot");
					if((first.getShotX()+1)<10){
						x = first.getShotX()+1;		//try to shot to the right of the firstSucceedShot
				 		y = first.getShotY();
				 		valid = true;
					}
					this.setTryShot(this.getTryShot()+1);
				}
				else if(this.getTryShot()==2 ){
					System.out.println("try to shot above the firstSucceedShot");
					if((first.getShotY()-1)>=0){
						x = first.getShotX();		//try to shot above the firstSucceedShot
				 		y = first.getShotY()-1;
				 		valid = true;
					}
					this.setTryShot(this.getTryShot()+1);
				}
				else if(this.getTryShot()==3 ){
					System.out.println("try to shot to the left of the firstSucceedShot");
					if((first.getShotX()-1)>=0){
						x = first.getShotX()-1;		//try to shot to the left of the firstSucceedShot
				 		y = first.getShotY();
				 		valid = true;
					}
					this.setTryShot(this.getTryShot()+1);
				}
				else{
					this.setFirstSucceedShot(null);
				}
			}
			else{				//we already have a direction to test
				System.out.println("have a direction");
				dir = this.getDirectionShot();
				side = this.getSide();
				if(dir=='U'){		//vertical direction, first is below second
					if(side){
						if(second.getShotY()-1>=0){			//position above the secondSucceedShot is still in the grid
							x = second.getShotX();		
					 		y = second.getShotY()-1;
					 		this.setSecondSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
					else{
						if(first.getShotY()+1 < 10){    //position below the firstSucceedShot is still in the grid
							x = first.getShotX();		
					 		y = first.getShotY()+1;
					 		this.setFirstSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
				}
				if(dir=='D'){		//vertical direction, first is above second
					if(side){
						if(first.getShotY()-1>=0){			//position above the firstSucceedShot is still in the grid
							x = first.getShotX();		
					 		y = first.getShotY()-1;
					 		this.setFirstSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
					else{
						if(second.getShotY()+1 < 10){    //position below the secondSucceedShot is still in the grid
							x = second.getShotX();		
					 		y = second.getShotY()+1;
					 		this.setSecondSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
				}
				if(dir=='L'){		//horizontal direction, first is at the right of the second
					if(side){
						if(second.getShotX()-1>=0){			//position at the left of the secondSucceedShot is still in the grid
							x = second.getShotX()-1;		
					 		y = second.getShotY();
					 		this.setSecondSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
					else{
						if(first.getShotX()+1 < 10){    //position at the right of the firstSucceedShot is still in the grid
							x = first.getShotX()+1;		
					 		y = first.getShotY();
					 		this.setFirstSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
				}
				if(dir=='R'){		//horizontal direction, second is at the right of the first
					if(side){
						if(first.getShotX()-1>=0){			//position at the left of the firstSucceedShot is still in the grid
							x = first.getShotX()-1;		
					 		y = first.getShotY();
					 		this.setFirstSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
					else{
						if(second.getShotX()+1 < 10){    //position at the right of the secondSucceedShot is still in the grid
							x = second.getShotX()+1;		
					 		y = second.getShotY();
					 		this.setSecondSucceedShot(new Shot(x, y));
					 		this.setSide(!side);
					 		valid = true;
						}
						else{
							this.setSide(!side);
						}
					}
				}
				
			}
			if(this.getShotGrid().alreadyShot(x, y)){
				valid=false;
			}
		}
		System.out.println("newX :"+x);
		System.out.println("newY :"+y);
		return new Shot(x,y);
	}



	@Override
	public void addShotToFinder(Shot t) {
		Shot first = this.getFirstSucceedShot();
		Shot second = this.getSecondSucceedShot();
		
		if(first==null){
			this.setFirstSucceedShot(t);
		}
		else if(second==null){
			this.setSecondSucceedShot(t);
			second = this.getSecondSucceedShot();
			if(second.getShotX()==first.getShotX()){
				if(second.getShotY()<first.getShotY()){
					this.setDirectionShot('U');
				}
				else{
					this.setDirectionShot('D');
				}
			}
			else{
				if(second.getShotX()<first.getShotX()){
					this.setDirectionShot('L');
				}
				else{
					this.setDirectionShot('R');
				}
			}
		}
	}


	public void reinitFinder() {
		this.firstSucceedShot = null;
		this.secondSucceedShot = null;
		this.direction = 'E';
		this.tryShot = 0;
		this.side = true;
	}
}
