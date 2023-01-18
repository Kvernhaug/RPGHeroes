package model.equipment;

import model.items.Weapon;
import model.items.WeaponType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    @Test
    public void testWeapon(){
        Weapon ankleShanker = new Weapon("Ankle Shanker", 2, WeaponType.DAGGER, 80);
        assertEquals("Ankle Shanker", ankleShanker.getName());
        assertEquals(2, ankleShanker.getRequiredLevel());
        assertEquals(WeaponType.DAGGER, ankleShanker.getWeaponType());
        assertEquals(80, ankleShanker.getWeaponDamage());
    }

}