package game;


public class Weapon extends Item
{
	public int damage;
	public int defense;
	
	
	
	public Weapon(String name, int damage, int defense, int price)
	{
		this.name = name;
		super.name = name;
		this.damage = damage;
		super.damage = damage;
		this.defense = defense;
		super.defense = defense;
		this.itemprice = price;
		super.itemprice = price;
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
