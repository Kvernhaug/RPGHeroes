package model.equipment;

public class Weapon extends Item {

    private WeaponType weaponType;
    private final int weaponDamage;

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
