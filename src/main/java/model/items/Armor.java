package model.items;

import model.characters.HeroAttributes;

/**
 * Class used to represent armor items in the game.
 */
public class Armor extends Item {
    private final ArmorType armorType;              // Armor type of the item.
    private final HeroAttributes armorAttributes;   // Attributes the armor gives its user.

    public Armor(String name, int requiredLevel, Slot slot, ArmorType armorType, HeroAttributes armorAttributes) {
        super(name, requiredLevel, slot);
        this.armorType = armorType;
        this.armorAttributes = armorAttributes;
    }

    public ArmorType getArmorType(){
        return armorType;
    }

    public Slot getSlot(){
        return super.slot;
    }

    public HeroAttributes getArmorAttributes(){
        return armorAttributes;
    }
}
