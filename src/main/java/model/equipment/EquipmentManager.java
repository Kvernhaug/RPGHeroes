package model.equipment;

import model.characters.HeroClass;
import model.exceptions.InvalidArmorException;
import model.exceptions.InvalidWeaponException;
import model.items.Armor;
import model.items.Item;
import model.items.Slot;
import model.items.Weapon;

import java.util.List;

public interface EquipmentManager {
    /**
     * Equip weapon if able.
     * @param weapon weapon object to equip
     * @param heroClass class of hero
     * @param level level of hero
     * @throws InvalidWeaponException if weapon does not match class/level
     */
    void equip(Weapon weapon, HeroClass heroClass, int level) throws InvalidWeaponException;

    /**
     * Equip armor if able.
     * @param armor armor object to equip
     * @param heroClass class of hero
     * @param level level of hero
     * @throws InvalidArmorException if armor does not match class/level
     */
    void equip(Armor armor, HeroClass heroClass, int level) throws InvalidArmorException;

    /**
     * Get the equipment in the given slot
     * @param slot slot we want equipment from
     * @return equipment in the given slot
     */
    Item getEquippedItem(Slot slot);

    /**
     * @return list of all equipped armor items
     */
    List<Armor> getArmor();


}
