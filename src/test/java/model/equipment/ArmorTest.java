package model.equipment;

import model.characters.HeroAttributes;
import model.items.Armor;
import model.items.ArmorType;
import model.items.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {
    @Test
    public void testArmor(){
        HeroAttributes armorAttributes = new HeroAttributes(6, 0, 0);
        Armor greatHelm = new Armor(
                "Great Helm",
                4,
                Slot.HEAD,
                ArmorType.PLATE,
                armorAttributes
        );
        assertEquals("Great Helm", greatHelm.getName());
        assertEquals(4, greatHelm.getRequiredLevel());
        assertEquals(Slot.HEAD, greatHelm.getSlot());
        assertEquals(ArmorType.PLATE, greatHelm.getArmorType());
        assertEquals(6, greatHelm.getArmorAttributes().getStrength());
        assertEquals(0, greatHelm.getArmorAttributes().getDexterity());
        assertEquals(0, greatHelm.getArmorAttributes().getIntelligence());
    }

}