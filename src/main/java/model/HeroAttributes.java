package model;

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
    public void increaseStrength(int value) {strength += value;}

    public int getDexterity(){
        return dexterity;
    }
    public void increaseDexterity(int value) {dexterity += value;}

    public int getIntelligence(){
        return intelligence;
    }
    public void increaseIntelligence(int value) {intelligence += value;}

    /**
     *
     * @param strength
     * @param dexterity
     * @param intelligence
     */
    public void increaseAttributes(int strength, int dexterity, int intelligence){
        this.strength += strength;
        this.dexterity += dexterity;
        this.intelligence += intelligence;
    }

    public void increaseAttributes(HeroAttributes newAttributes){
        this.strength += newAttributes.getStrength();
        this.dexterity += newAttributes.getDexterity();
        this.intelligence += newAttributes.getIntelligence();
    }
}
