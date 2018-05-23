package luciano.raphael;

public class BotPlayerLevel1 extends BotPlayer{
	public BotPlayerLevel1(){
		super();
	}

	@Override
	public Shot shoot() {
		int x = (int)(Math.random()*10);
		int y = (int)(Math.random()*10);
		
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
