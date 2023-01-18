package model.characters;

/**
 * Class used to handle all the attributes a hero has
 * Attributes include:
 * Strength – determines the physical strength of the character.
 * Dexterity – determines the characters ability to attack with speed and nimbleness.
 * Intelligence – determines the characters affinity with magic.
 */
public class HeroAttributes {
    private int strength;
    private int dexterity;
    private int intelligence;

    public HeroAttributes(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getStrength(){
        return strength;
    }

    public int getDexterity(){
        return dexterity;
    }

    public int getIntelligence(){
        return intelligence;
    }

    /**
     * Add input HeroAttributes to this HeroAttributes object
     * @param newAttributes HeroAttributes to add
     */
    public void increaseAttributes(HeroAttributes newAttributes){
        this.strength += newAttributes.getStrength();
        this.dexterity += newAttributes.getDexterity();
        this.intelligence += newAttributes.getIntelligence();
    }
}
