package game;


public class Potion extends Item
{
	protected int health_plus;
	protected int mana_plus;
	
	
	public Potion(String name, int health_plus, int mana_plus, int price)
	{
		this.name = name;
		this.health_plus = health_plus;
		this.mana_plus = mana_plus;
		this.itemprice = price;
	}
	
	public void drinkPotion()
	{
		Player.life = Player.life + health_plus;
		Player.mana = Player.mana + mana_plus;
	}
	
}
