package model;
// Project classes
import model.equipment.*;
import model.exceptions.InvalidArmorException;
import model.exceptions.InvalidWeaponException;
// External classes
import java.util.EnumMap;
import java.util.List;

/**
 * Abstract class used for every hero in the game.
 * Field variables and methods shared between all classes are defined here.
 * The class is abstract as every hero object within the game has to belong to a specific class.
 */
public abstract class Hero {
    private final String name;  // Name of hero
    protected String className; // Name of class
    protected int level;        // Current hero level
    protected Attribute damagingAttribute;  // Damaging attribute based on hero class
    protected HeroAttributes levelAttributes;   // Hero attributes based on total hero level
    protected HeroAttributes levelIncrease;     // Hero attributes added whenever a level is gained
    protected EnumMap<Slot, Item> equipment;    // Map of currently equipped items
    protected List<WeaponType> validWeaponTypes;// List of weapon types the hero class can equip
    protected List<ArmorType> validArmorTypes;  // List of armor types the hero class can equip

    public Hero(String name){
        this.name = name;
        this.level = 1; // All heroes start at level 1
        // Initialize hero without any equipment
        this.equipment = new EnumMap<>(Slot.class);
        this.equipment.put(Slot.WEAPON, null);
        this.equipment.put(Slot.HEAD, null);
        this.equipment.put(Slot.BODY, null);
        this.equipment.put(Slot.LEGS, null);
    }

    public String getName(){
        return name;
    }

    /**
     * Level up hero, incrementing level and increasing attributes based on class
     */
    public void levelUp(){
        level++;
        levelAttributes.increaseAttributes(levelIncrease);
    }

    /**
     * Equip constructor if the item is of the weapon class
     * @param weapon weapon object we want to add to hero's equipment
     * @throws InvalidWeaponException exception if weapon cannot be equipped
     */
    public void Equip(Weapon weapon) throws InvalidWeaponException {
        if (weapon.getRequiredLevel() > level) {
            throw new InvalidWeaponException("Not high enough level to equip weapon.");
        } else if (!validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new InvalidWeaponException("This class cannot equip weapons of this type.");
        } else {
            equipment.put(Slot.WEAPON, weapon);
        }
    }

    /**
     * Equip constructor if the item is of the armor class
     * @param armor armor object we want to add to hero's equipment
     * @throws InvalidArmorException exception if armor cannot be equipped
     */
    public void Equip(Armor armor) throws InvalidArmorException {
        if (armor.getRequiredLevel() > level) {
            throw new InvalidArmorException("Not high enough level to equip armor.");
        } else if (!validArmorTypes.contains(armor.getArmorType())) {
            throw new InvalidArmorException("This class cannot equip armor of this type.");
        } else {
            equipment.put(armor.getSlot(), armor);
        }
    }

    /**
     * Calculate damage inflicted by the hero.
     * Damage = weapon damage * (1 + 0.01 * damagingAttribute)
     * @return total damage inflicted
     */
    public int damage(){
        int weaponDamage = 1;   // Default damage if no weapon is equipped
        // Get weapon damage if weapon is equipped
        if (equipment.get(Slot.WEAPON) != null){
            Weapon weapon = (Weapon) equipment.get(Slot.WEAPON);
            weaponDamage = weapon.getWeaponDamage();
        }
        double damage = weaponDamage;
        switch (damagingAttribute){ // Select attribute multiplier based on class
            case STRENGTH -> damage *= (1 + 0.01*totalAttributes().getStrength());
            case DEXTERITY -> damage *= (1 + 0.01*totalAttributes().getDexterity());
            case INTELLIGENCE -> damage *= (1 + 0.01*totalAttributes().getIntelligence());
        }
        return (int) Math.round(damage);    // Round damage to the closest integer
    }

    /**
     * Calculate the hero's total attributes based level and armor
     * @return total hero attributes
     */
    public HeroAttributes totalAttributes(){
        // Start with attributes from levels
        HeroAttributes totalAttributes = levelAttributes;
        // Add attributes from armor iteratively
        for (Item item : equipment.values()){
            if (item != null && item.getSlot() != Slot.WEAPON){ // Ignore weapon and empty slots
                Armor armor = (Armor) item; // Cast to Armor to get subclass functionality
                totalAttributes.increaseAttributes(armor.getArmorAttributes());
            }
        }
        return totalAttributes;
    }

    /**
     * Display information about the hero to the console
     */
    public void display(){
        HeroAttributes totalAttributes = totalAttributes();
        String displayInfo = String.format("""
                <==== Hero Information ====>
                    Name:           %s
                    Class:          %s
                    Level:          %d
                    Strength:       %d
                    Dexterity:      %d
                    Intelligence:   %d
                <==========================>
                """,
                name,
                className,
                level,
                totalAttributes.getStrength(),
                totalAttributes.getDexterity(),
                totalAttributes.getIntelligence()
        );
        System.out.println(displayInfo);
    }
}
