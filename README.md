# RPG Heroes
***

## Tools

![OpenJDK] ![Junit5] ![Maven]

## Description

This project aims to create a console based RPG game where the user plays as a hero from
a set of predefined hero classes. Currently, a base model of the game is implemented with
no form of controller or gameplay loop.

## Project Structure

### Model

#### Characters

The characters package holds on to classes related to characters 
in the game. The *Character* interface contains a set of methods
any character in the game should be able to execute. The interface
does not currently serve much purpose, as the only class implementing
the interface so far, is the *Hero* class. In theory, the *Character*
interface can also be used to implement other types of characters, like
enemies and NPCs. The *Hero* class represents the character the user 
can play ass. The *Hero* is initialized with a hero name and a hero
class. The idea is that the user types the name of the character
into the console during character creation. The user chooses a class on
a selection menu, from which the *Hero* object is fed the correct class
from the *HeroClass* enumerator. 

The *Hero* class contains an initialization method for each playable 
class, where the correct method is called when the *Hero* object is created.
The class specific initialization methods define variables that are 
unique between the classes. I initially had the different hero classes
in separate classes, but it sort of pointless as those classes ended
up with only a constructor, since every method worked the same for every class.


#### Items

In this package, the different items within the game are handled.
The items in the game can either be weapons or armor, represented by the
*Weapon* and *Armor* classes. Both classes extend the abstract class *Item*,
which has the shared characteristics between the two item classes.
The *WeaponType* and *ArmorType* enumerators define their respective
types, dictating which classes can use them. 

#### Equipment

The equipment package handles equipment worn by a hero. The 
*Equipment* class is a callable object that can hold onto an item
for equipment slot, defined by the *Slot* enumerator. The *Hero* class
interacts with the Equipment ,class through the *EquipmentManager*
interface with contain all the public methods in the *Equipment* class.

The equipment was originally handled within the *Hero* class, but
it made the class look really cluttered, so it made sense to move it
a different class and create it as an object in *Hero*.




[OpenJDK]: https://img.shields.io/badge/OpenJDK-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Junit5]: https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white
[Maven]: https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white