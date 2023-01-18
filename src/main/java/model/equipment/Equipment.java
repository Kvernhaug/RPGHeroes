package model.equipment;

import model.characters.HeroClass;
import model.exceptions.InvalidArmorException;
import model.exceptions.InvalidWeaponException;
import model.items.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Equipment implements EquipmentManager{

    private EnumMap<Slot, Item> equipment;

    public Equipment(){
        this.equipment = new EnumMap<>(Slot.class);
        this.equipment.put(Slot.WEAPON, null);
        this.equipment.put(Slot.HEAD, null);
        this.equipment.put(Slot.BODY, null);
        this.equipment.put(Slot.LEGS, null);
    }

    @Override
    public void equip(Weapon weapon, HeroClass heroClass, int level) throws InvalidWeaponException {
        if (weapon.getRequiredLevel() > level) {
            throw new InvalidWeaponException("Not high enough level to equip weapon.");
        } else if (!getValidWeaponTypes(heroClass).contains(weapon.getWeaponType())) {
            throw new InvalidWeaponException("This class cannot equip weapons of this type.");
        } else {
            equipment.put(Slot.WEAPON, weapon);
        }
    }

    @Override
    public void equip(Armor armor, HeroClass heroClass, int level) throws InvalidArmorException {
        if (armor.getRequiredLevel() > level) {
            throw new InvalidArmorException("Not high enough level to equip armor.");
        } else if (!getValidArmorTypes(heroClass).contains(armor.getArmorType())) {
            throw new InvalidArmorException("This class cannot equip armor of this type.");
        } else {
            equipment.put(armor.getSlot(), armor);
        }
    }

    @Override
    public Item getEquippedItem(Slot slot) {
        return equipment.get(slot);
    }

    @Override
    public List<Armor> getArmor() {
        List<Armor> armorList = new ArrayList<>();
        for (Item item : equipment.values()){
            if (item != null && item.getSlot() != Slot.WEAPON){ // Ignore weapon empty slots
                Armor armor = (Armor) item;
                armorList.add(armor);
            }
        }
        return armorList;
    }

    private List<WeaponType> getValidWeaponTypes(HeroClass heroClass){
        List<WeaponType> validWeaponTypes = new ArrayList<>();
        switch (heroClass) {
            case MAGE -> {
                validWeaponTypes.add(WeaponType.STAFF);
                validWeaponTypes.add(WeaponType.WAND);
                return validWeaponTypes;
            }
            case RANGER -> {
                validWeaponTypes.add(WeaponType.BOW);
                return validWeaponTypes;
            }
            case ROGUE -> {
                validWeaponTypes.add(WeaponType.DAGGER);
                validWeaponTypes.add(WeaponType.SWORD);
                return validWeaponTypes;
            }
            case WARRIOR -> {
                validWeaponTypes.add(WeaponType.SWORD);
                validWeaponTypes.add(WeaponType.AXE);
                validWeaponTypes.add(WeaponType.HAMMER);
                return validWeaponTypes;
            }
            default -> {
                return null;
            }
        }
    }

    private List<ArmorType> getValidArmorTypes(HeroClass heroClass){
        List<ArmorType> validArmorTypes = new ArrayList<>();
        switch (heroClass){
            case MAGE -> {
                validArmorTypes.add(ArmorType.CLOTH);
                return validArmorTypes;
            }
            case RANGER, ROGUE -> {
                validArmorTypes.add(ArmorType.LEATHER);
                validArmorTypes.add(ArmorType.MAIL);
                return validArmorTypes;
            }
            case WARRIOR -> {
                validArmorTypes.add(ArmorType.MAIL);
                validArmorTypes.add(ArmorType.PLATE);
                return validArmorTypes;
            }
            default -> {
                return null;
            }
        }
    }
}
