package game;


public class Potion extends Item
{
	protected int health_plus;
	protected int mana_plus;
	public int price;
	
	public Potion(int health_plus, int mana_plus, int price)
	{
		this.health_plus = health_plus;
		this.mana_plus = mana_plus;
	}
	
	public void drinkPotion()
	{
		Player.life = Player.life + health_plus;
		Player.mana = Player.mana + mana_plus;
	}
	
}
