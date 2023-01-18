package model.characters;

import model.exceptions.InvalidArmorException;
import model.exceptions.InvalidWeaponException;
import model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeroTest {
    @Test
    public void testMage() throws InvalidWeaponException {
        String name = "Viviv";
        HeroClass heroClass = HeroClass.MAGE;
        Hero mage = new Hero(name, heroClass);
        assertEquals(name, mage.getName());
        assertEquals(1, mage.getLevel());
        // Test base attributes
        assertEquals(1, mage.totalAttributes().getStrength());
        assertEquals(1, mage.totalAttributes().getDexterity());
        assertEquals(8, mage.totalAttributes().getIntelligence());
        // Test level up
        mage.levelUp();
        assertEquals(2, mage.totalAttributes().getStrength());
        assertEquals(2, mage.totalAttributes().getDexterity());
        assertEquals(13, mage.totalAttributes().getIntelligence());
        // Test equip wrong weapon type
        Weapon ankleShanker = new Weapon("Ankle Shanker", 2, WeaponType.DAGGER, 80);
        Exception e = assertThrows(InvalidWeaponException.class, () -> mage.equip(ankleShanker));
        assertEquals("This class cannot equip weapons of this type.", e.getMessage());
        // Test right weapon class works
        Weapon UFSOD = new Weapon(
                "User Friendly Staff of Obliteration and Destruction",
                1,
                WeaponType.STAFF,
                999999999
        );
        mage.equip(UFSOD);
    }
    @Test
    public void testRanger(){
        String name = "Viviv";
        HeroClass heroClass = HeroClass.RANGER;
        Hero hero = new Hero(name, heroClass);
        assertEquals(name, hero.getName());
        assertEquals(1, hero.getLevel());
        // Test base attributes
        assertEquals(1, hero.totalAttributes().getStrength());
        assertEquals(7, hero.totalAttributes().getDexterity());
        assertEquals(1, hero.totalAttributes().getIntelligence());
        // Test level up
        hero.levelUp();
        assertEquals(2, hero.totalAttributes().getStrength());
        assertEquals(12, hero.totalAttributes().getDexterity());
        assertEquals(2, hero.totalAttributes().getIntelligence());
        // Test weapon level too high to equip
        Weapon UCB = new Weapon(
                "Unnecessarily Complicated Bow",
                100,
                WeaponType.BOW,
                45
        );
        Exception e = assertThrows(InvalidWeaponException.class, () -> hero.equip(UCB));
        assertEquals("Not high enough level to equip weapon.", e.getMessage());
    }
    @Test
    public void testRogue(){
        String name = "Viviv";
        HeroClass heroClass = HeroClass.ROGUE;
        Hero hero = new Hero(name, heroClass);
        assertEquals(name, hero.getName());
        assertEquals(1, hero.getLevel());
        // Test base attributes
        assertEquals(2, hero.totalAttributes().getStrength());
        assertEquals(6, hero.totalAttributes().getDexterity());
        assertEquals(1, hero.totalAttributes().getIntelligence());
        // Test level up
        hero.levelUp();
        assertEquals(3, hero.totalAttributes().getStrength());
        assertEquals(10, hero.totalAttributes().getDexterity());
        assertEquals(2, hero.totalAttributes().getIntelligence());
        // Test wrong armor type
        HeroAttributes armorAttributes = new HeroAttributes(1, 0, 4);
        Armor TDSSED = new Armor(
                "Trousers of Definitely not Suited for Rogues.",
                2,
                Slot.LEGS,
                ArmorType.CLOTH,
                armorAttributes
        );
        Exception e = assertThrows(InvalidArmorException.class, () -> hero.equip(TDSSED));
        assertEquals("This class cannot equip armor of this type.", e.getMessage());

    }
    @Test
    public void testWarrior() throws InvalidArmorException {
        String name = "Viviv";
        HeroClass heroClass = HeroClass.WARRIOR;
        Hero hero = new Hero(name, heroClass);
        assertEquals(name, hero.getName());
        assertEquals(1, hero.getLevel());
        // Test base attributes
        assertEquals(5, hero.totalAttributes().getStrength());
        assertEquals(2, hero.totalAttributes().getDexterity());
        assertEquals(1, hero.totalAttributes().getIntelligence());
        // Test level up
        hero.levelUp();
        assertEquals(8, hero.totalAttributes().getStrength());
        assertEquals(4, hero.totalAttributes().getDexterity());
        assertEquals(2, hero.totalAttributes().getIntelligence());
        // Test equip
        HeroAttributes armorAttributes = new HeroAttributes(6, 0, 0);
        Armor greatHelm = new Armor(
                "Great Helm",
                4,
                Slot.HEAD,
                ArmorType.PLATE,
                armorAttributes
        );
        // Check level too low to equip
        Exception e = assertThrows(InvalidArmorException.class, () -> hero.equip(greatHelm));
        assertEquals("Not high enough level to equip armor.", e.getMessage());
        hero.levelUp();
        hero.levelUp();
        // Should work after level ups
        hero.equip(greatHelm);
        assertEquals(20, hero.totalAttributes().getStrength());
        assertEquals(8, hero.totalAttributes().getDexterity());
        assertEquals(4, hero.totalAttributes().getIntelligence());
        // Test multiple armor pieces
        Armor SLP = new Armor(
                "Smart Looking Plate",
                4,
                Slot.BODY,
                ArmorType.PLATE,
                new HeroAttributes(1, 0, 10)
        );
        hero.equip(SLP);
        assertEquals(21, hero.totalAttributes().getStrength());
        assertEquals(8, hero.totalAttributes().getDexterity());
        assertEquals(14, hero.totalAttributes().getIntelligence());
        // Test equipping item in same slot
        Armor NSGH = new Armor(
                "Not So Great Helm",
                2,
                Slot.HEAD,
                ArmorType.PLATE,
                new HeroAttributes(3, 0, 0)
        );
        hero.equip(NSGH);
        assertEquals(18, hero.totalAttributes().getStrength());
        assertEquals(8, hero.totalAttributes().getDexterity());
        assertEquals(14, hero.totalAttributes().getIntelligence());


    }

    /**
     * Test damage with no equipment
     */
    @Test
    public void testUnarmedDamage(){
        Hero hero = new Hero("Strübmeister", HeroClass.ROGUE);
        double damage = 10 * (1 + 0.01*6); // 6 = hero dexterity
        int expectedDamage = (int) Math.round(damage);
        assertEquals(expectedDamage, hero.damage());
        Weapon dagger = new Weapon(
                "Dagger",
                1,
                WeaponType.DAGGER,
                60
        );
    }

    /**
     * Test damage with weapon equipped
     */
    @Test
    public void testArmedDamage() throws InvalidWeaponException, InvalidArmorException {
        Hero hero = new Hero("Strübmeister", HeroClass.ROGUE);
        Weapon dagger = new Weapon(
                "Dagger",
                1,
                WeaponType.DAGGER,
                60
        );
        hero.equip(dagger);
        double damage = 60*(1 + 0.01*6);
        int expectedDamage = (int) Math.round(damage);
        assertEquals(expectedDamage, hero.damage());
        // Test damage after replacing weapon
        Weapon shortsword = new Weapon(
                "Shortsword",
                1,
                WeaponType.SWORD,
                85
        );
        hero.equip(shortsword);
        damage = 85*(1 + 0.01*6);
        expectedDamage = (int) Math.round(damage);
        assertEquals(expectedDamage, hero.damage());
        // Test damage after equipping armor
        Armor badassHood = new Armor(
                "Badass Totally not Edgy Hood",
                1,
                Slot.HEAD,
                ArmorType.LEATHER,
                new HeroAttributes(0, 4, 0)
        );
        hero.equip(badassHood);
        damage = 85*(1 + 0.01*10); // dexterity should now be 10
        expectedDamage = (int) Math.round(damage);
        assertEquals(expectedDamage, hero.damage());
    }

    // Initialization to test console output for testing display method
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testDisplay() throws InvalidArmorException {
        Hero hero = new Hero("Sir Person", HeroClass.WARRIOR);
        hero.levelUp();
        Armor mailHood = new Armor(
                "Mail Hood",
                1,
                Slot.HEAD,
                ArmorType.MAIL,
                new HeroAttributes(3, 1, 0)
        );
        hero.equip(mailHood);
        hero.display();
        assertEquals(
                """
                <==== Hero Information ====>
                    Name:           Sir Person
                    Class:          Warrior
                    Level:          2
                    Strength:       11
                    Dexterity:      5
                    Intelligence:   2
                <==========================>""",
                outputStreamCaptor.toString().trim());
    }
}