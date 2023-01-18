package model.items;

/**
 * Class used to represent weapons in the game
 */
public class Weapon extends Item {

    private final WeaponType weaponType;    // Weapon type of the item.
    private final int weaponDamage;         // Base damage inflicted by the weapon

    public Weapon(String name, int requiredLevel, WeaponType weaponType, int weaponDamage) {
        super(name, requiredLevel, Slot.WEAPON);
        this.weaponDamage = weaponDamage;
        this.weaponType = weaponType;
    }

    public int getWeaponDamage(){
        return weaponDamage;
    }

    public WeaponType getWeaponType(){
        return weaponType;
    }

}
