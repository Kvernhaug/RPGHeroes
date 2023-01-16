package model.equipment;

import model.HeroAttributes;

public class Armor extends Item{
    private final ArmorType armorType;
    private HeroAttributes armorAttributes;

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
