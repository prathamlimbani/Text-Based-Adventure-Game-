import java.util.Scanner;
import java.util.Random;

public class TextAdventureGame {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    static String playerName;
    static int health = 100;
    static int attackPower = 10;
    static int defense = 5;

    static String[] items = {"Sword", "Shield", "Health Potion"};
    static String[] rooms = {"Dungeon Entrance", "Dark Hallway", "Treasure Room", "Monster Lair"};

    static boolean hasSword = false;
    static boolean hasShield = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the Text-Based Adventure Game!");
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();

        while (true) {
            System.out.println("\nYou are in the " + rooms[rand.nextInt(rooms.length)]);
            System.out.println("Health: " + health);
            System.out.println("What would you like to do?");
            System.out.println("1. Explore");
            System.out.println("2. Check inventory");
            System.out.println("3. Quit game");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    exploreRoom();
                    break;
                case 2:
                    checkInventory();
                    break;
                case 3:
                    System.out.println("Goodbye, " + playerName + "!");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

            if (health <= 0) {
                System.out.println("You have died. Game Over.");
                break;
            }
        }
    }

    public static void exploreRoom() {
        int event = rand.nextInt(3);

        switch (event) {
            case 0:
                encounterItem();
                break;
            case 1:
                encounterMonster();
                break;
            case 2:
                System.out.println("Nothing interesting here...");
                break;
        }
    }

    public static void encounterItem() {
        String foundItem = items[rand.nextInt(items.length)];
        System.out.println("You found a " + foundItem + "!");
        System.out.println("Do you want to pick it up?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            if (foundItem.equals("Sword")) {
                hasSword = true;
                System.out.println("You have equipped the Sword.");
            } else if (foundItem.equals("Shield")) {
                hasShield = true;
                System.out.println("You have equipped the Shield.");
            } else if (foundItem.equals("Health Potion")) {
                health += 20;
                System.out.println("You have used a Health Potion. Health: " + health);
            }
        } else {
            System.out.println("You left the " + foundItem + " behind.");
        }
    }

    public static void encounterMonster() {
        System.out.println("A wild monster appears!");
        int monsterHealth = 50;
        int monsterAttack = rand.nextInt(15) + 5;
        int monsterDefense = rand.nextInt(5) + 2;

        while (monsterHealth > 0 && health > 0) {
            System.out.println("Monster Health: " + monsterHealth);
            System.out.println("Your Health: " + health);
            System.out.println("What will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Flee");

            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (action) {
                case 1:
                    int damage = attackPower - monsterDefense;
                    if (hasSword) {
                        damage += 5; // Sword adds extra damage
                    }
                    monsterHealth -= Math.max(damage, 0);
                    System.out.println("You attack the monster! It takes " + Math.max(damage, 0) + " damage.");
                    break;
                case 2:
                    int defenseAmount = defense + (hasShield ? 5 : 0);
                    System.out.println("You defend with a shield! You block " + defenseAmount + " damage.");
                    break;
                case 3:
                    System.out.println("You flee the battle!");
                    return;
                default:
                    System.out.println("Invalid action.");
            }

            if (monsterHealth > 0) {
                int monsterDamage = monsterAttack - defense;
                if (hasShield) {
                    monsterDamage -= 3; // Shield reduces damage
                }
                health -= Math.max(monsterDamage, 0);
                System.out.println("The monster attacks you! You take " + Math.max(monsterDamage, 0) + " damage.");
            }
        }

        if (monsterHealth <= 0) {
            System.out.println("You have defeated the monster!");
        } else {
            System.out.println("You were defeated by the monster...");
        }
    }

    public static void checkInventory() {
        System.out.println("\nYour Inventory:");
        if (hasSword) {
            System.out.println("Sword");
        }
        if (hasShield) {
            System.out.println("Shield");
        }
        System.out.println("Health: " + health);
    }
}
