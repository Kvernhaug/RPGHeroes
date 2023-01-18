package model.characters;
// Project classes
import model.equipment.*;
import model.exceptions.InvalidArmorException;
import model.exceptions.InvalidWeaponException;
import model.items.Armor;
import model.items.Slot;
import model.items.Weapon;
// External classes


/**
 * Abstract class used for every hero in the game.
 * Field variables and methods shared between all classes are defined here.
 * The class is abstract as every hero object within the game has to belong to a specific class.
 */
public class Hero implements Character {
    private final String name;  // Name of hero
    private String className; // Name of class
    private final HeroClass heroClass;
    private int level;        // Current hero level
    private Attribute damagingAttribute;  // Damaging attribute based on hero class
    private HeroAttributes levelAttributes;   // Hero attributes based on total hero level
    private HeroAttributes levelIncrease;     // Hero attributes added whenever a level is gained
    private EquipmentManager equipment;


    public Hero(String name, HeroClass heroClass){
        this.name = name;
        this.level = 1; // All heroes start at level 1
        this.heroClass = heroClass;
        initClass();   // Initialize class unique values
        equipment = new Equipment();    // Use Equipment class to manage hero equipment
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getLevel(){
        return level;
    }

    /**
     * Level up hero, incrementing level and increasing attributes based on class
     */
    public void levelUp(){
        level++;
        levelAttributes.increaseAttributes(levelIncrease);
    }

    /**
     * Method used to equip weapons to the hero.
     * @param weapon weapon object we want to add to hero's equipment
     * @throws InvalidWeaponException exception if weapon cannot be equipped
     */
    public void equip(Weapon weapon) throws InvalidWeaponException {
        equipment.equip(weapon, heroClass, level);
    }

    /**
     * Method used to equip armor to the hero.
     * @param armor armor object we want to add to hero's equipment
     * @throws InvalidArmorException exception if armor cannot be equipped
     */
    public void equip(Armor armor) throws InvalidArmorException {
        equipment.equip(armor, heroClass, level);
    }

    /**
     * Calculate damage inflicted by the hero.
     * Damage = weapon damage * (1 + 0.01 * damagingAttribute)
     * @return total damage inflicted
     */
    @Override
    public int damage(){
        int weaponDamage = 10;   // Default damage if no weapon is equipped
        // Get weapon damage if weapon is equipped
        if (equipment.getEquippedItem(Slot.WEAPON) != null){
            Weapon weapon = (Weapon) equipment.getEquippedItem(Slot.WEAPON);
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
    @Override
    public HeroAttributes totalAttributes(){
        // Start with attributes from levels
        HeroAttributes totalAttributes = new HeroAttributes(
                levelAttributes.getStrength(),
                levelAttributes.getDexterity(),
                levelAttributes.getIntelligence()
        );
        // Add attributes from armor iteratively
        for (Armor armor : equipment.getArmor()){
            totalAttributes.increaseAttributes(armor.getArmorAttributes());
        }
        return totalAttributes;
    }

    /**
     * Display information about the hero to the console
     */
    @Override
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

    /**
     * Calls the right class initialization method
     */
    private void initClass(){
        switch (heroClass) {
            case MAGE -> initMage();
            case RANGER -> initRanger();
            case ROGUE -> initRogue();
            case WARRIOR -> initWarrior();
        }
    }

    /**
     * Initialization method for Mage.
     * Defines variable values that are unique to the mage.
     */
    private void initMage(){
        className = "Mage";
        // Set damaging attribute of class
        damagingAttribute = Attribute.INTELLIGENCE;
        // Set class base stats and stat increase on level up
        levelAttributes = new HeroAttributes(1, 1, 8);
        levelIncrease = new HeroAttributes(1, 1, 5);
    }

    /**
     * Initialization method for ranger.
     * Defines variable values that are unique to the ranger.
     */
    private void initRanger(){
        className = "Ranger";
        // Set damaging attribute of class
        damagingAttribute = Attribute.DEXTERITY;
        // Set class base stats and stat increase on level up
        levelAttributes = new HeroAttributes(1, 7, 1);
        levelIncrease = new HeroAttributes(1, 5, 1);
    }

    /**
     * Initialization method for rogue.
     * Defines variable values that are unique to the rogue.
     */
    private void initRogue(){
        className = "Rogue";
        // Set damaging attribute of class
        damagingAttribute = Attribute.DEXTERITY;
        // Set class base stats and stat increase on level up
        levelAttributes = new HeroAttributes(2, 6, 1);
        levelIncrease = new HeroAttributes(1, 4, 1);
    }

    /**
     * Initialization method for warrior.
     * Defines variable values that are unique to the warrior.
     */
    private void initWarrior(){
        className = "Warrior";
        // Set damaging attribute of class
        damagingAttribute = Attribute.STRENGTH;
        // Set class base stats and stat increase on level up
        levelAttributes = new HeroAttributes(5, 2, 1);
        levelIncrease = new HeroAttributes(3, 2, 1);
    }
}
