package model.characters;

/**
 * Interface used to represent any character in the game.
 * i.e. heroes, enemies, friendly NPCs
 */
public interface Character {

    /**
     * @return name of the character
     */
    String getName();

    /**
     * @return current level of the character
     */
    int getLevel();

    /**
     * @return damage caused by character
     */
    int damage();

    /**
     * @return total strength, dexterity and intelligence from levels and items
     */
    HeroAttributes totalAttributes();

    /**
     * Display useful information about the character
     */
    void display();
}
