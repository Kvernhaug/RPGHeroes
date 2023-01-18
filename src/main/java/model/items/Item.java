package model.items;

/**
 * Abstract class used for equipment in the game.
 * An item can either be a weapon or armor.
 */
public abstract class Item {
    protected String name;          // Name of the item.
    protected int requiredLevel;    // Required level for a hero to be able to equip the item.
    protected Slot slot;            // Equipment slot the item goes into.

    public Item(String name, int requiredLevel, Slot slot){
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public int getRequiredLevel(){
        return requiredLevel;
    }

    public Slot getSlot(){
        return slot;
    }
}
