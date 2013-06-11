package game;


public class Potion extends Item
{
	protected int health_plus;
	protected int mana_plus;
	
	public Potion(int health_plus, int mana_plus)
	{
		this.health_plus = health_plus;
		this.mana_plus = mana_plus;
	}
	
	
}
