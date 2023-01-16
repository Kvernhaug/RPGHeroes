package model.classes;

import model.Attribute;
import model.Hero;
import model.HeroAttributes;
import model.equipment.ArmorType;
import model.equipment.WeaponType;

import java.util.ArrayList;

/**
 * Implementation of warrior as a subclass of Hero
 * The unique attributes of the class is defined in the constructor.
 * Methods shared between all RPG classes are defined in the Hero class.
 */
public class Warrior extends Hero {

    public Warrior(String name) {
        super(name);
        super.className = "Warrior";
        // Set damaging attribute of class
        super.damagingAttribute = Attribute.STRENGTH;
        // Set class base stats and stat increase on level up
        super.levelAttributes = new HeroAttributes(5, 2, 1);
        super.levelIncrease = new HeroAttributes(3, 2, 1);
        // Set valid weapon types for the class
        super.validWeaponTypes = new ArrayList<>();
        super.validWeaponTypes.add(WeaponType.SWORD);
        super.validWeaponTypes.add(WeaponType.AXE);
        super.validWeaponTypes.add(WeaponType.HAMMER);
        // Set valid armor types for the class
        super.validArmorTypes = new ArrayList<>();
        super.validArmorTypes.add(ArmorType.MAIL);
        super.validArmorTypes.add(ArmorType.PLATE);
    }

}
