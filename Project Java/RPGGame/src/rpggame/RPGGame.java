/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpggame;
import java.util.Scanner;
/**
 *
 * @author Mosaddik Asif
 */
public class RPGGame {
    
    static Scanner scanner = new Scanner(System.in);
    public static String playerName;
    private int health = 100;
    private int money = 100;
    private int currentEnemyIndex = 0;
    private final String[] enemyNames = {"Slime", "Skeleton", "Frost Giant", "Lava Golem", "Wyvern", "White Dragon"};
    private final int[] enemyHP = {10, 30, 80, 100, 220, 300};
    public final int[] enemyAttack = {10, 20, 40, 50, 100, 200};
   
    public void start() {
        
       
        while (true) {
            
            System.out.println("\nWelcome " + playerName + " to the city of Caldemount!!!");
            System.out.println("Your health: " + health);
            
            System.out.println("1. Shop");
            System.out.println("2. Dungeon");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
           
            switch (choice) {
                case 1:
                    shopMenu(scanner);
                    break;
                case 2:
                    dungeonMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
   
    private void shopMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nWelcome to the Shop!");
            System.out.println("You have: " + money + " gold");
            System.out.println("1. Dagger (Damage +30) - Cost: 30 gold");
            System.out.println("2. Sword (Damage +50) - Cost: 60 gold");
            System.out.println("3. Two-Handed Sword (Damage +70) - Cost: 90 gold");
            System.out.println("4. The Excalibur (Damage +190) - Cost: 450 gold");
            System.out.println("5. Potion (Health +50) - Cost: 60 gold");
            System.out.println("6. Magical Potion (Health +100) - Cost: 150 gold");
            System.out.println("7. Basic Armor (Defense +50) - Cost: 70 gold");
            System.out.println("8. Heavy Armor (Defense +100) - Cost: 150 gold");
            System.out.println("9. Leave Shop");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
           
            switch (choice) {
                case 1:
                    purchaseItem("Dagger", 30, 30, 0);
                    break;
                case 2:
                    purchaseItem("Sword", 50, 60, 0);
                    break;
                case 3:
                    purchaseItem("Two-Handed Sword", 70, 90, 0);
                    break;
                case 4:
                    purchaseItem("The Excalibur", 190, 450, 0);
                    break;
                case 5:
                    purchaseItem("Potion", 0, 60, 50);
                    break;
                case 6:
                    purchaseItem("Magical Potion", 0, 150, 100);
                    break;
                case 7:
                    purchaseItem("Basic Armor", 0, 70, 50);
                    break;
                case 8:
                    purchaseItem("Heavy Armor", 0, 150, 100);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
   
    private void purchaseItem(String itemName, int damageBoost, int cost, int healthBoost) {
        if (money >= cost) {
            money -= cost;
            health += healthBoost;
            System.out.println("\nYou purchased " + itemName + ".");
           
            if (damageBoost > 0) {
                System.out.println("Your damage is now " + (damageBoost + 30) + ".");
            }
           
            if (healthBoost > 0) {
                System.out.println("Your health is now " + health + ".");
            }
        } else {
            System.out.println("Not enough money to purchase " + itemName + ".");
        }
    }
   
    private void dungeonMenu(Scanner scanner) {
        while (currentEnemyIndex < enemyNames.length) {
            System.out.println("\nYou are now inside the Dungeon.");
            System.out.println("\nCurrent Enemy: " + enemyNames[currentEnemyIndex]);
            System.out.println("Enemy Health: " + enemyHP[currentEnemyIndex]);
            System.out.println("1. Fight Enemy");
            System.out.println("2. Go back to town square");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
           
            switch (choice) {
                case 1:
                    fightEnemy(scanner);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice.");                    
                    return;
            }
        }
       
        System.out.println("\nYou took the fear away from the people of Caldemount.\nYour name " + playerName + " shall be written in the Hall of Great Adventurer and you will be known as the \"Greatest Hero That Has Ever Lived\".\nThe King is indebted to you for your bravery and as a reward gives you 1000 gold for saving this kingdom snd its people.");
        money += 1000;
        return;
    }
   
    private void fightEnemy(Scanner scanner) {
        int enemyDamage = enemyAttack[currentEnemyIndex];
        int enemyHealth = enemyHP[currentEnemyIndex];
       
        while (true) {
            System.out.println("\nYour Health is: " + health);
            System.out.println("Enemy Health: " + enemyHealth);
            System.out.println("1. Attack the Enemy");
            System.out.println("2. Block Enemy Attack");
            System.out.println("3. Abandon Battle");
            System.out.print("Choose an action: ");
            int choice = scanner.nextInt();
           
            switch (choice) {
                case 1:
                    int playerDamage = (int) (Math.random() * 50) + 30; // Random damage between 30 and 80
                    enemyHealth -= playerDamage;
                    System.out.println("\nYou attacked the enemy for " + playerDamage + " damage.");
                    break;
                case 2:
                    int blockAmount = (int) (Math.random() * 40); // Random block amount between 10 and 40
                    int damageTaken = enemyDamage - blockAmount;
                    if (damageTaken < 0) {
                        damageTaken = 0;
                    }
                    health -= damageTaken;
                    System.out.println("\nYou blocked the enemy's attack, but took " + damageTaken + " damage.");
                    break;
                case 3:
                    if (enemyHealth <= 0) {
                        money += (Math.random() * 40)+10; // Reward for defeating the enemy
                        System.out.println("You defeated the " + enemyNames[currentEnemyIndex] + " and received " + money + " gold!");
                        currentEnemyIndex++;
                    }
                    return;
                default:
                    System.out.println("Invalid choice.");                    
                    return;
            }
            if (enemyHealth <= 0) {
                System.out.println("You defeated the " + enemyNames[currentEnemyIndex] + "!");
                money += (Math.random() * 40)+10; // Reward for defeating the enemy
                System.out.println("You received "+ money + " gold.");
                currentEnemyIndex++;
                break;
            }
           
            if (health <= 0) {
                System.out.println("You died fighting the " + enemyNames[currentEnemyIndex] + ".\nThe blank gravestone is the only witness of your existence...");
                System.exit(0);
            }
        }
    }
   
    public static void main(String[] args) {
        System.out.print("Hello Adventurer, Please Enter your name: ");
        playerName = scanner.nextLine();
        RPGGame game = new RPGGame();
        game.start();
    }
}
