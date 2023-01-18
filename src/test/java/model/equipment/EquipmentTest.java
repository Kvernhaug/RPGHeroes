package model.equipment;

import model.characters.HeroAttributes;
import model.characters.HeroClass;
import model.exceptions.InvalidArmorException;
import model.exceptions.InvalidWeaponException;
import model.items.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    @Test
    void testConstruction() {
        EquipmentManager equipment = new Equipment();
        assertNull(equipment.getEquippedItem(Slot.WEAPON));
        assertNull(equipment.getEquippedItem(Slot.HEAD));
        assertNull(equipment.getEquippedItem(Slot.BODY));
        assertNull(equipment.getEquippedItem(Slot.LEGS));
    }

    @Test
    void testEquipWeapon() throws InvalidWeaponException {
        EquipmentManager equipment = new Equipment();
        Weapon longsword = new Weapon(
                "Longsword",
                2,
                WeaponType.SWORD,
                100
        );
        // Test wrong class
        Exception e = assertThrows(InvalidWeaponException.class, () -> equipment.equip(longsword, HeroClass.MAGE, 2));
        assertEquals("This class cannot equip weapons of this type.", e.getMessage());
        // Test under leveled
        e = assertThrows(InvalidWeaponException.class, () -> equipment.equip(longsword, HeroClass.WARRIOR, 1));
        assertEquals("Not high enough level to equip weapon.", e.getMessage());
        // Test successful equip
        equipment.equip(longsword, HeroClass.WARRIOR, 2);
        assertEquals(longsword, equipment.getEquippedItem(Slot.WEAPON));
    }

    @Test
    void testEquipArmor() throws InvalidArmorException {
        EquipmentManager equipment = new Equipment();
        Armor greatHelm = new Armor(
                "Great Helm",
                4,
                Slot.HEAD,
                ArmorType.PLATE,
                new HeroAttributes(6, 0, 0)
        );
        // Test wrong class
        Exception e = assertThrows(InvalidArmorException.class, () -> equipment.equip(greatHelm, HeroClass.MAGE, 4));
        assertEquals("This class cannot equip armor of this type.", e.getMessage());
        // Test under leveled
        e = assertThrows(InvalidArmorException.class, () -> equipment.equip(greatHelm, HeroClass.WARRIOR, 1));
        assertEquals("Not high enough level to equip armor.", e.getMessage());
        // Test successful equip
        equipment.equip(greatHelm, HeroClass.WARRIOR, 4);
        assertEquals(greatHelm, equipment.getEquippedItem(Slot.HEAD));
    }

    @Test
    void testGetArmor() throws InvalidArmorException {
        EquipmentManager equipment = new Equipment();
        List<Armor> armor = equipment.getArmor();
        assertEquals(new ArrayList<Armor>(), armor);    // Should return empty list
        Armor greatHelm = new Armor(
                "Great Helm",
                4,
                Slot.HEAD,
                ArmorType.PLATE,
                new HeroAttributes(6, 0, 0)
        );
        equipment.equip(greatHelm, HeroClass.WARRIOR, 4);
        Armor SLP = new Armor(
                "Smart Looking Plate",
                4,
                Slot.BODY,
                ArmorType.PLATE,
                new HeroAttributes(1, 0, 10)
        );
        equipment.equip(SLP, HeroClass.WARRIOR, 4);
        armor = equipment.getArmor();
        assertEquals(greatHelm, armor.get(0));
        assertEquals(SLP, armor.get(1));
    }
}