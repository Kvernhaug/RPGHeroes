package model.characters;

import model.characters.HeroAttributes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAttributesTest {
    int strength = 2;
    int dexterity = 4;
    int intelligence = 7;

    @Test
    public void assertConstruction() {
        HeroAttributes heroAttributes = new HeroAttributes(
                strength,
                dexterity,
                intelligence
        );
        assertEquals(strength, heroAttributes.getStrength());
        assertEquals(dexterity, heroAttributes.getDexterity());
        assertEquals(intelligence, heroAttributes.getIntelligence());
    }

    @Test
    public void testIncreaseAttributes(){
        HeroAttributes heroAttributes = new HeroAttributes(
                strength,
                dexterity,
                intelligence
        );
        HeroAttributes newAttributes = new HeroAttributes(1, 1, 1);
        heroAttributes.increaseAttributes(newAttributes);
        assertEquals(strength+1, heroAttributes.getStrength());
        assertEquals(dexterity+1, heroAttributes.getDexterity());
        assertEquals(intelligence+1, heroAttributes.getIntelligence());
    }


}