
import java.io.IOException;
import java.util.*;

/** This program simulates a virtual game of Pass the Pigs.
 * The program starts giving the user(s) a set of instructions instructing them on how
 * to play Pass the Pigs. Then the program asks for the players' names. 
 * Then the game finally starts. Player 1 rolls first and they keep rolling,
 * earning points until they choose to end their turn and receive all the points
 * they earned, or Pig Out and their turn ends and they lose all of the points they rolled that
 * turn. Then Player 2 gets their turn and does the same thing as Player 1. The game goes on
 * till one player reaches 100 points and that player wins. Then at that points the user(s)
 * can either choose to close the program or play another game.
 * 
 * @author dliu
 */


public class PassthePigsDanny{
	public static void main(String[] args) throws InterruptedException, IOException{
		Scanner in = new Scanner(System.in);

		int [] values = {1,1,5,5,10,15};
		int again;
		//Instructions
		System.out.println("Welcome to Pass the Pigs!\n\nIntructions:");
		System.out.println("There are two players, a bank and two pigs.\nTo start one player rolls two pigs");
		System.out.println("Depending on what position the pigs are in a certain amount of points are added to the Bank");
		System.out.println("After rolling the player can either keep rolling or Bank out");
		System.out.println("If they Bank out, the points in the Bank are added to their total and the Bank is reset to 0, then it is the other player's turn");
		System.out.println("Then they basically repeat the steps above until one player reaches 100 points and wins");
		System.out.println("But be careful, you can roll a PIG OUT and lose all the points in the Bank, so there is a risk in continuing to roll");
		System.out.println("Possible roll combinations:\n\tLeft side (1 point)\n\tRight side (1 point)\n\tRazorback (5 points)\n\tTrotter (5 points)\n\tSnouter (10 points)\n\tLeaning Jowler (15 points)");
		System.out.println("\nPossible pig combos:\n\t1 Point Sider (Left – Left or Right – Right)\n\tLose Turn Pig Out (Left – Right)\n\t(20 Points) Double Razorback, Double Trotter\n\t(40 Points) Double Snouter\n\t(60 Points) Double Leaning Jowler\n\tCombo Points Add single points together (Possible Roll Combinations)");

		System.out.print("\nPlayer 1, Enter is your name: ");
		String name1 = in.nextLine();
		System.out.print("Player 2, Enter is your name: ");
		String name2 = in.nextLine();

		//This do while loop allows the user to restart and play another game if they want to
		do {
			int Turn = 0;
			int Bank = 0;
			int player1 = 0;
			int player2 = 0;
			int Playerturn = 1;

			//This do while loop is one set of rolls, every time a user chooses to roll again this loop restarts
			do {
				System.out.println("\n" + name1 + " has: " + player1 + " points");
				System.out.println(name2 + " has: " + player2 + " points");
				System.out.println("Bank: " + Bank);
				//Prompts the player to roll
				//If it is player 1's turn
				if (Playerturn == 1) {
					System.out.println(name1 + "'s turn: Press \"Enter\" to roll");
				}
				//If it is player 2's turn
				else {
					System.out.println(name2 + "'s turn: Press \"Enter\" to roll");
				}
				System.in.read();
				//Puts time between "." to simulate how you wait when you roll the pigs
				for (int i = 0; i < 3; i ++) {
					Thread.sleep(300);
					System.out.print(". ");
					Thread.sleep(300);
				}
				System.out.print("\n");


				int roll1 = getPosition(roll());
				int roll2 = getPosition(roll());
				int points = getPoints(roll1, roll2, values);

				//Checks if the user rolled a pig out, if so empties the Bank and ends their turn, 
				//else adds to the Bank and asks if the user wants to roll again or bank out
				if (points == -1){
					System.out.print("PIG OUT!!!\n");
					System.out.print("            (\\____/)\n" + "            / @__@ \\\n" + "           (  (oo)  )\n" + "            `-.~~.-'\n" + "             /    \\\n" + "           @/      \\_\n" + "          (/ /    \\ \\)\n" + "           WW`----'WW\n");
					Bank = 0;
					Turn = 2;
					//Checks whoose's turn it is
					//If it is Player1's turn add one to make it Player2's turn
					if (Playerturn == 1) {
						//Add one so Player2 can go 
						Playerturn++;
					}
					//If it is Player2's turn add one to make it Player1's turn
					else {
						//Subtract one one so Player1 can go 
						Playerturn--;
					}
				}
				else {
					//If user doesn't pig out, the points are added to the Bank
					Bank += points;
					System.out.println("Bank: " + Bank);
					System.out.println("If want to roll again enter 1, if you want to bank out and end your turn enter 2: ");
					Turn = in.nextInt();
					//Checks if the user wants to Bank out
					if (Turn == 2) {
						//If it is Player1's turn add the points in the Bank to their count
						if (Playerturn == 1) {
							//Add points to Player1's Bank
							player1 += Bank;
						}
						//If it is Player2's turn add the points in the Bank to their count
						else {
							//Add points to Player2's Bank
							player2 += Bank;
						}
						//Resets Bank
						Bank = 0;
						//Checks whoose's turn it is
						//If it is Player1's turn add one to make it Player2's turn
						if (Playerturn == 1) {
							//Add one so Player2 can go 
							Playerturn++;
						}
						//If it is Player2's turn add one to make it Player1's turn
						else {
							//Subtract one one so Player1 can go 
							Playerturn--;
						}
					}
				}

			} while (player1 < 100 && player2 < 100);

			//Checks who won
			if (player1 >= 100) {
				System.out.println("\n" + name1 + " wins!");
				System.out.println("  ___________\n |           |\n(| Player 1  |)\n |           |\n \\           /\n  \\         /\n   `-------'\n      | |\n      | | \n     _|_|_");
			}
			else {
				System.out.println("\n" + name2 + " wins!");
				System.out.println("  ___________\n |           |\n(| Player 2  |)\n |           |\n \\           /\n  \\         /\n   `-------'\n      | |\n      | | \n     _|_|_");
			}
			System.out.println("\nPress 0 to play again! Press 1 to end the program: ");
			again = in.nextInt();
		} while (again == 0);
	}


	/**
	 * This method randomly rolls a # between 1-100
	 * @return #1-100
	 */
	public static int roll(){
		return (int)(Math.random()*100+1);
	}
	/**This method returns the position of the pig associated with the roll
	 * and outputs what position the user rolled
	 * @param rollVal
	 * @return 0, 1, 2, 3, 4, 5
	 */
	public static int getPosition(int rollVal){
		//Depending on what number roll() got is what the user "rolled"
		if (rollVal <= 28) {
			System.out.println("You rolled a Left Side");
			return 0;
		}
		else if (rollVal <= 56) {
			System.out.println("You rolled a Right Side");
			return 1;
		}
		else if (rollVal <= 71) {
			System.out.println("You rolled a Razorback");
			return 2;
		}
		else if (rollVal <= 86) {
			System.out.println("You rolled a Troutter");
			return 3;
		}
		else if (rollVal <= 95) {
			System.out.println("You rolled a Snouter");
			return 4;
		}
		else {
			System.out.println("You rolled a Leaning Jowler");
			return 5;
		}
	}

	/**
	 * This method finds the points associated with the pig positions
	 * @param pig1
	 * @param pig2
	 * @param values
	 * @return point values associated with pig positions
	 */
	public static int getPoints(int pig1, int pig2, int[] values){
		//Checks if the user rolled a pig out
		if (pig1 == 0 && pig2 == 1 || pig1 == 1 && pig2 == 0) {
			return -1;
		}
		//Checks if the user pigs the user rolled are in the same positions 
		else if(pig1 == pig2){
			//Checks to see if the user rolled 2 left sides or two right sides
			if (pig1 == 0 && pig2 == 0 || pig1 == 1 && pig2 == 1){
				return 1;
			}
			else {
				//Multiplies the initial points by 2 because that's in the games rules
				return (values[pig1] + values[pig2]) * 2;
			}
		}
		else {
			//Return the two rolls point values added up
			return values[pig1] + values[pig2];
		}
	}

}
