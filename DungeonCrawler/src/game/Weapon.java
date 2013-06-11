package game;


public class Weapon extends Item
{
	public int damage;
	public int defense;
	
	public Weapon(int damage, int defense)
	{
		this.damage = damage;
		this.defense = defense;
	}
	
	public void isEquipped()
	{
		// Player benoetigt Stats, wie Attack, Defense, Magie...
		Player.attack = Player.attack + damage;
		Player.defense = Player.defense + defense;
	}
}
