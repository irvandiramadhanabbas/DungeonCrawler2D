import java.util.Random;
import java.util.Scanner;
import java.util.random.*;

public class Game {

    Scanner myScanner = new Scanner(System.in);
    Scanner enterScanner = new Scanner(System.in);
    int playerHP;
    String playerName;
    String playerWeapon;
    int choice;
    int monsterHP;

    int silverRing;

    public static void main(String[] args) {

        Game dublin;
        dublin = new Game();

        dublin.playerSetUp();
        dublin.townGate();
    }


    public void playerSetUp() {

        playerHP = 20;
        monsterHP = 15;

        playerWeapon = "Knife";

        System.out.println("Darah: " + playerHP);
        System.out.println("Senjata: " + playerWeapon);

        System.out.println("Masukkan Nama Mu:");

        playerName = myScanner.nextLine();

        System.out.println("Assalamualaikum " + playerName + ", Kita gass saja langsung gamenya");

    }

    public void townGate() {

        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Kamu ada di gerbang kota.");
        System.out.println("Ada seorang penjaga berdiri di depan mu.");
        System.out.println("");
        System.out.println("Apa yang akan kamu lakukan?");
        System.out.println("");
        System.out.println("1: Berbicara pada penjaga");
        System.out.println("2: Menyerang Penjaga");
        System.out.println("3: Pergi");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            if (silverRing == 1) {
                ending();
            } else {
                System.out.println("Guard: Haii kau, orang asing. jadi nama kamu " + playerName
                        + "? \nSorry but we cannot let stranger enter our town.");
                enterScanner.nextLine();
                townGate();
            }

        } else if (choice == 2) {
            Random random = new Random();
            int Acak = random.nextInt(5)+1;
            playerHP = playerHP - Acak;
            System.out.println(
                    "Guard: Hey kamu jangan bodoh.\n\nPenjaga menyerangmu dan kamu menyerah.\n");
            System.out.println("Darah: " + playerHP);
            System.out.println("Kamu Menerima" + Acak + "Damage");
            enterScanner.nextLine();
            townGate();
        } else if (choice == 3) {
            crossRoad();
        } else {
            townGate();
        }
    }

    public void crossRoad() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
        System.out.println("1: Go north");
        System.out.println("2: Go east");
        System.out.println("3: Go south");
        System.out.println("4: Go west");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            north();
        } else if (choice == 2) {
            east();
        } else if (choice == 3) {
            townGate();
        } else if (choice == 4) {
            west();
        } else {
            crossRoad();
        }
    }

    public void north() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("There is a river. You drink the water and rest at the riverside.");
        System.out.println("Your HP is recovered.");
        playerHP = playerHP + 1;
        System.out.println("Your HP: " + playerHP);
        System.out.println("\n\n1: Go back to the crossroad");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            crossRoad();
        } else {
            north();
        }
    }

    public void east() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("You walked into a forest and found a Long Sword!");
        playerWeapon = "Long Sword";
        System.out.println("Your Weapon: " + playerWeapon);
        System.out.println("\n\n1: Go back to the crossroad");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            crossRoad();
        } else {
            east();
        }
    }

    public void west() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("You encounter a goblin!\n");
        System.out.println("1: Fight");
        System.out.println("2: Run");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            fight();
        } else if (choice == 2) {
            crossRoad();
        } else {
            west();
        }
    }

    public void fight() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Your HP: " + playerHP);
        System.out.println("Monster HP: " + monsterHP);
        System.out.println("\n1: Attack");
        System.out.println("2: Run");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            attack();
        } else if (choice == 2) {
            crossRoad();
        } else {
            fight();
        }
    }

    public void attack() {
        int playerDamage = 0;

        if (playerWeapon.equals("Knife")) {
            playerDamage = new java.util.Random().nextInt(5);
        } else if (playerWeapon.equals("Long Sword")) {
            playerDamage = new java.util.Random().nextInt(8);
        }

        System.out.println("You attacked the monster and gave " + playerDamage + " damage!");

        monsterHP = monsterHP - playerDamage;

        System.out.println("Monster HP: " + monsterHP);

        if (monsterHP < 1) {
            win();
        } else if (monsterHP > 0) {
            int monsterDamage = 0;

            monsterDamage = new java.util.Random().nextInt(4);

            System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");

            playerHP = playerHP - monsterDamage;

            System.out.println("Player HP: " + playerHP);

            if (playerHP < 1) {
                dead();
            } else if (playerHP > 0) {
                fight();
            }
        }

    }

    public void dead() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("You are dead!!!");
        System.out.println("\n\nGAME OVER");
        System.out.println("\n------------------------------------------------------------------\n");

    }

    public void win() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("You killed the monster!");
        System.out.println("The monster dropped a ring!");
        System.out.println("You obtaind a silver ring!\n\n");
        System.out.println("1: Go east");
        System.out.println("\n------------------------------------------------------------------\n");

        silverRing = 1;

        choice = myScanner.nextInt();
        if (choice == 1) {
            crossRoad();
        } else {
            win();
        }

    }

    public void ending() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Guard: Oh you killed that goblin!?? Great!");
        System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
        System.out.println("\n\n           THE END                    ");
        System.out.println("\n------------------------------------------------------------------\n");
    }
}
