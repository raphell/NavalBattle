package luciano.raphael;

public class BotPlayerLevel2 extends BotPlayer{
	public BotPlayerLevel2(){
		super();
	}

	@Override
	public Shot shoot() {
		int x = (int)(Math.random()*10);
		int y = (int)(Math.random()*10);
		
		while(this.getShotGrid().alreadyShot(x, y)){		//it understand it's a bad idea to shoot at a box it already shot before
			x = (int)(Math.random()*10);
			y = (int)(Math.random()*10);
		}
		
		return new Shot(x,y);
	}

	@Override
	public void addShotToFinder(Shot t) {
		//this is a stupid IA, it doesn't understand what to do with this information
	}

	@Override
	public void reinitFinder() {
		//this is a stupid IA, it doesn't understand what to do with this information
	}
}
