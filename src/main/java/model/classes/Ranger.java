package model.classes;

import model.Attribute;
import model.Hero;
import model.HeroAttributes;
import model.equipment.ArmorType;
import model.equipment.WeaponType;

import java.util.ArrayList;

/**
 * Implementation of ranger as a subclass of Hero
 * The unique attributes of the class is defined in the constructor.
 * Methods shared between all RPG classes are defined in the Hero class.
 */
public class Ranger extends Hero {

    public Ranger(String name) {
        super(name);
        super.className = "Ranger";
        // Set damaging attribute of class
        super.damagingAttribute = Attribute.DEXTERITY;
        // Set class base stats and stat increase on level up
        super.levelAttributes = new HeroAttributes(1, 7, 1);
        super.levelIncrease = new HeroAttributes(1, 5, 1);
        // Set valid weapon types for the class
        super.validWeaponTypes = new ArrayList<>();
        super.validWeaponTypes.add(WeaponType.BOW);
        // Set valid armor types for the class
        super.validArmorTypes = new ArrayList<>();
        super.validArmorTypes.add(ArmorType.LEATHER);
        super.validArmorTypes.add(ArmorType.MAIL);
    }

}
