package game;


public class Weapon extends Item
{
	public int damage;
	public int defense;
	public int price;
	
	public Weapon(int damage, int defense, int price)
	{
		this.damage = damage;
		this.defense = defense;
		this.price = price;
	}
	
	public void isEquipped()
	{
		// Player benoetigt Stats, wie Attack, Defense, Magie...
		// Moegliches Ablegen einer vorher getragenen Waffe, 10 = Basisstaerke
		if(Player.attack < 10 || Player.defense < 10)
		{
			Player.attack = 10;
			Player.defense = 10;
		}
		
		Player.attack = Player.attack + damage;
		Player.defense = Player.defense + defense;
	}
}
