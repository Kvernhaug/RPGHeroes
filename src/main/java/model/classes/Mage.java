package model.classes;

import model.Attribute;
import model.Hero;
import model.HeroAttributes;
import model.equipment.*;

import java.util.ArrayList;

public class Mage extends Hero {

    public Mage(String name) {
        super(name);
        super.className = "Mage";
        // Set damaging attribute of class
        super.damagingAttribute = Attribute.INTELLIGENCE;
        // Set class base stats and stat increase on level up
        super.levelAttributes = new HeroAttributes(1, 1, 8);
        super.levelIncrease = new HeroAttributes(1, 1, 5);
        // Set valid weapon types for the class
        super.validWeaponTypes = new ArrayList<>();
        super.validWeaponTypes.add(WeaponType.STAFF);
        super.validWeaponTypes.add(WeaponType.WAND);
        // Set valid armor types for the class
        super.validArmorTypes = new ArrayList<>();
        super.validArmorTypes.add(ArmorType.CLOTH);
    }

}
