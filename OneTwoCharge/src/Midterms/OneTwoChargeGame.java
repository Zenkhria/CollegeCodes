package Midterms;
import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

public class OneTwoChargeGame 
{
	static int playerSkillPoints, enemySkillPoints, playerHealth = 10, enemyHealth = 10, round;
		
	public static void main(String[] args)
	{				
		Scanner scan = new Scanner(System.in);
		Random randomizer = new Random();
		Vector<String> playerAvailableActions = new Vector<String>();
		Vector<String> enemyAvailableActions = new Vector<String>();
		String playerName, playerAction, enemyAction, help;
		int playerSkillPointChecker;		
		
		System.out.println("Input your Username: ");
		playerName = scan.nextLine();
		
		System.out.println("Need Help? (y/n)");
		help = scan.nextLine();
		
		if(help.equalsIgnoreCase("y") || help.equalsIgnoreCase("yes"))
		{
			HelpDirectory();
		}
		
		else
		{
			System.out.println("\nWelcome to One Two Charge!");
		}
		
		do
		{							
			DisplayPlayerStats(playerName);
	
			System.out.println("What is your action?");
			DisplayPlayerAvailableSkills(playerAvailableActions);						
			
			do
			{					
				System.out.print("Player Attack: ");
				playerAction = scan.next();
								
				//Skill Point Requirement Checker For Player
				playerSkillPointChecker = PlayerSkillPointChecker(playerAvailableActions, playerAction);
						
			}while(playerSkillPointChecker == 1);
			
			Vector<String> enemyAvailableSkills = DisplayEnemyAvailableSkills(enemyAvailableActions);					
				
			int randomSkillPicker = randomizer.nextInt(enemyAvailableActions.size());
			enemyAction = enemyAvailableActions.get(randomSkillPicker);
				
			//removes the Skill Chance Enhancers before
			//available skills of enemy are system printed. 				
			enemyAvailableActions.remove("Charge ");
			enemyAvailableActions.remove("Charge  ");
			enemyAvailableActions.remove("Charge   ");
			enemyAvailableActions.remove("Kamekameha ");
			enemyAvailableActions.remove("Kamekameha  ");
			
			System.out.println("\nAvailable Enemy Skills: ");	
			System.out.println(enemyAvailableSkills);
				
			System.out.println("Enemy Attack: " + enemyAction);						
			
			DamageCalculation(playerAvailableActions, playerAction, enemyAction);	
			
			//clears the elements of the Vector<String> from this round
			//so that the elements don't carry over to the next round 									
			playerAvailableActions.clear();
			enemyAvailableActions.clear();
			
		}while(!(playerHealth <= 0 || enemyHealth <= 0));
		
		DisplayPlayerStats(playerName);
		
		if (enemyHealth <= 0 )
		{
			System.out.println("You won!");
		}
		
		else
		{
			System.out.println("You lose!");
		}
	}
	
	public static void DamageCalculation(Vector<String> playerAvailableActions, String playerAction, String enemyAction)
	{
		switch(playerAction)
		{
		case "1":	//charge			
			switch(enemyAction.toLowerCase())
			{
			case "charge":
			case "charge ":		//reads the added Chance Enhancers
			case "charge  ":
			case "charge   ":	
				playerSkillPoints++;
				enemySkillPoints++;
				break;
			case "shield": 
				playerSkillPoints++;
				enemySkillPoints -= 1;
				break;
			case "wave": 
				playerSkillPoints++;
				enemySkillPoints -= 1;
				playerHealth -= 2;
				break;
			case "ax": 
				playerSkillPoints++;
				enemySkillPoints -= 2;
				playerHealth -= 3;
				break;
			case "kamekameha":
			case "kamekameha ":		//reads the added Chance Enhancers
			case "kamekameha  ":
				playerSkillPoints++;
				enemySkillPoints -= 4;
				playerHealth -= 6;
				break;
			case "amen": 
				playerSkillPoints++;
				enemySkillPoints -= 5;
				enemyHealth += 5;
				break;
			}
			break;
			
		case "2":	//shield		
			switch(enemyAction.toLowerCase())
			{
			case "charge":
			case "charge ":
			case "charge  ":
			case "charge   ":	
				playerSkillPoints -= 1;
				enemySkillPoints++;
				break;
			case "shield": 
				enemySkillPoints -= 1;
				playerSkillPoints -= 1;
				break;
			case "wave": 
				playerSkillPoints -= 1;
				enemySkillPoints -= 1;
				break;
			case "ax": 
				playerSkillPoints -= 1;
				enemySkillPoints -= 2;
				break;
			case "kamekameha":
			case "kamekameha ":
			case "kamekameha  ":	
				playerSkillPoints -= 1;
				enemySkillPoints -= 4;
				playerHealth -= 6;
				break;
			case "amen": 
				playerSkillPoints -= 1;
				enemySkillPoints -= 5;
				enemyHealth += 5;
				break;
			}
			break;
			
		case "3":	//wave		
			switch(enemyAction.toLowerCase())
			{
			case "charge":
			case "charge ":
			case "charge  ":
			case "charge   ":	
				playerSkillPoints -= 1;
				enemySkillPoints++;
				enemyHealth -= 2;
				break;
			case "shield": 
				enemySkillPoints -= 1;
				playerSkillPoints -= 1;
				break;
			case "wave": 
				playerSkillPoints -= 1;
				enemySkillPoints -= 1;
				break;
			case "ax": 
				playerSkillPoints -= 1;
				enemySkillPoints -= 2;
				playerHealth -= 1;
				break;
			case "kamekameha":
			case "kamekameha ":
			case "kamekameha  ":
				playerSkillPoints -= 1;
				enemySkillPoints -= 4;
				playerHealth -= 4;
				break;
			case "amen": 
				playerSkillPoints -= 1;
				enemySkillPoints -= 5;
				enemyHealth += 5;
				break;
			}
			break;
			
		case "4":	//ax	
			switch(enemyAction.toLowerCase())
			{
			case "charge":
			case "charge ":
			case "charge  ":
			case "charge   ":	
				playerSkillPoints -= 2;
				enemySkillPoints++;
				enemyHealth -= 3;
				break;
			case "shield": 
				enemySkillPoints -= 1;
				playerSkillPoints -= 2;
				break;
			case "wave": 
				playerSkillPoints -= 2;
				enemySkillPoints -= 1;
				enemyHealth -= 1;
				break;
			case "ax": 
				playerSkillPoints -= 2;
				enemySkillPoints -= 2;
				break;
			case "kamekameha":
			case "kamekameha ":
			case "kamekameha  ":
				playerSkillPoints -= 2;
				enemySkillPoints -= 4;
				playerHealth -= 3;
				break;
			case "amen": 
				playerSkillPoints -= 2;
				enemySkillPoints -= 5;
				enemyHealth += 5;
				break;
			}
			break;
			
		case "5":	//kamekameha		
			switch(enemyAction.toLowerCase())
			{
			case "charge":
			case "charge ":
			case "charge  ":
			case "charge   ":	
				playerSkillPoints -= 4;
				enemySkillPoints++;
				enemyHealth -= 6;
				break;
			case "shield": 
				enemySkillPoints -= 1;
				playerSkillPoints -= 4;
				enemyHealth -= 6;
				break;
			case "wave": 
				playerSkillPoints -= 4;
				enemySkillPoints -= 1;
				enemyHealth -= 4;
				break;
			case "ax": 
				playerSkillPoints -= 4;
				enemySkillPoints -= 2;
				enemyHealth -= 3;
				break;
			case "kamekameha":
			case "kamekameha ":
			case "kamekameha  ":
				playerSkillPoints -= 4;
				enemySkillPoints -= 4;
				break;
			case "amen": 
				playerSkillPoints -= 4;
				enemySkillPoints -= 5;
				enemyHealth += 5;
				break;
			}
			break;
			
		case "6":	//amen
			switch(enemyAction.toLowerCase())
			{
			case "charge":
			case "charge ":
			case "charge  ":
			case "charge   ":	
				playerSkillPoints -= 5;
				enemySkillPoints++;
				playerHealth += 5;
				break;
			case "shield": 
				enemySkillPoints -= 1;
				playerSkillPoints -= 5;
				playerHealth += 5;
				break;
			case "wave": 
				playerSkillPoints -= 5;
				enemySkillPoints -= 1;
				playerHealth += 5;
				break;
			case "ax": 
				playerSkillPoints -= 5;
				enemySkillPoints -= 2;
				playerHealth += 5;
				break;
			case "kamekameha":
			case "kamekameha ":
			case "kamekameha  ":
				playerSkillPoints -= 5;
				enemySkillPoints -= 4;
				playerHealth += 5;
				break;
			case "amen": 
				playerSkillPoints -= 5;
				enemySkillPoints -= 5;
				enemyHealth += 5;
				playerHealth += 5;
				break;
			}
			break;
		}		
	}
	
	public static void DisplayPlayerStats(String playerName)
	{	
		round+=1;
		
		String upperCaseName = "";
		
		String playerHealthDigit = Integer.toString(playerHealth);		
		String enemyHealthDigit = Integer.toString(enemyHealth);
		String Round = Integer.toString(round);
		
		//This just makes it so that the playerStat Interface doesn't get disturbed 
		//if the enemyHealth that is originally a 2 digit number, becomes a 1 digit
		//number by adding a space before the enemyHealth value	
		if (enemyHealth > 10)
		{
			enemyHealthDigit = " " + enemyHealth;
		}
		
		else if (enemyHealth == 10)
		{
			enemyHealthDigit = " " + enemyHealth;
		}
		
		else if (enemyHealth >= 0)
		{
			enemyHealthDigit = "  " + enemyHealth;
		}
				
		else if (enemyHealth < 0)
		{
			enemyHealthDigit = "  " + "0";
		}
		
		if (playerHealth > 10)
		{
			playerHealthDigit = " " + playerHealth;
		}
		
		else if (playerHealth == 10)
		{
			playerHealthDigit = " " + playerHealth;
		}
		
		else if (playerHealth >= 0)
		{
			playerHealthDigit = "  " + playerHealth;
		}		
		
		else if (playerHealth < 0)
		{
			playerHealthDigit = "  " + "0";
		}
		
		if (round <= 9)
		{
			Round = " " + round;
		}
		
		switch(playerName.length())
		{
		case 1: 
			upperCaseName = playerName.toUpperCase() + "        ";
			break;
		case 2:
			upperCaseName = playerName.toUpperCase() + "       ";
			break;
		case 3:
			upperCaseName = playerName.toUpperCase() + "      ";
			break;
		case 4:
			upperCaseName = playerName.toUpperCase() + "     ";
			break;
		case 5:
			upperCaseName = playerName.toUpperCase() + "    ";
			break;
		case 6:
			upperCaseName = playerName.toUpperCase() + "   ";
			break;
		case 7:
			upperCaseName = playerName.toUpperCase() + "  ";
			break;
		case 8:
			upperCaseName = playerName.toUpperCase() + " ";
			break;
		}
		
		String playerStat = "\n  +--------------------------------------------------------------------+\n"
				+ "  |  Skill Points: " + playerSkillPoints + "  	       Player Name: " + upperCaseName + "Player Health:" + playerHealthDigit + " |\n"
				+ "  |  EnemySkillPoints: " + enemySkillPoints + "           Round :  " + Round + "        Enemy Health:" + enemyHealthDigit + " |\n"
				+ "  +--------------------------------------------------------------------+\n"
				+ "";
		
		System.out.println(playerStat);
	}
	
	public static int PlayerSkillPointChecker(Vector<String> playerAvailableAction, String playerAction) 
	{
		int playerSkillPointChecker = 0;
		
		if(!(playerAvailableAction.contains("[2]Shield")))
		{
			if(playerAction.equals("2"))
			{
				System.out.println("Not Enough Skill Points");
				
				playerSkillPointChecker = 1;
			}
		}
		
		if(!(playerAvailableAction.contains("[3]Wave")))
		{
			if(playerAction.equals("3"))
			{
				System.out.println("Not Enough Skill Points");
				
				playerSkillPointChecker = 1;
			}
		}
		
		if(!(playerAvailableAction.contains("[4]Ax")))
		{
			if(playerAction.equals("4"))
			{
				System.out.println("Not Enough Skill Points");
				
				playerSkillPointChecker = 1;
			}
		}
		
		if(!(playerAvailableAction.contains("[5]Kamekameha")))
		{
			if(playerAction.equals("5"))
			{
				System.out.println("Not Enough Skill Points");
				
				playerSkillPointChecker = 1;
			}
		}
		
		if(!(playerAvailableAction.contains("[6]Amen")))
		{
			if(playerAction.equals("6"))
			{
				System.out.println("Not Enough Skill Points");
				
				playerSkillPointChecker = 1;
			}
		}
		
		//Makes sure that the skills used are only those that are valid
		if(!(playerAction.equals("1") || (playerAction.equals("2")) || (playerAction.equals("3")) || (playerAction.equals("4")) || (playerAction.equals("5")) || (playerAction.equals("6"))))
		{
			System.out.println("Skill Not Present In The Available Skill Choices. Please Choose A Valid Skill");
				
			playerSkillPointChecker = 1;
		}	
		
		
		return playerSkillPointChecker;
	}
		
	public static void DisplayPlayerAvailableSkills(Vector<String> playerAvailableActions)
	{	
		if(playerSkillPoints >= 0)
		{
			playerAvailableActions.add("[1]Charge");
		}
		
		if(playerSkillPoints >= 1)
		{
			playerAvailableActions.add("[2]Shield");
			playerAvailableActions.add("[3]Wave");
		}
		
		if(playerSkillPoints >= 2)
		{
			playerAvailableActions.add("[4]Ax");
		}
		
		if(playerSkillPoints >= 4)
		{
			playerAvailableActions.add("[5]Kamekameha");
		}
		
		if(playerSkillPoints >= 5)
		{
			playerAvailableActions.add("[6]Amen");
		}
		
		System.out.println(playerAvailableActions);
	}
	
	public static Vector<String> DisplayEnemyAvailableSkills(Vector<String> enemyAvailableActions)
	{	
		if(enemySkillPoints >= 0)
		{
			enemyAvailableActions.add("Charge");
			enemyAvailableActions.add("Charge ");		//enhances the chance of Charge being picked using the
			enemyAvailableActions.add("Charge  ");		//random number generator
			enemyAvailableActions.add("Charge   ");
		}
		
		if(enemySkillPoints >= 1)
		{
			enemyAvailableActions.add("Shield");
			enemyAvailableActions.add("Wave");
		}
		
		if(enemySkillPoints >= 2)
		{
			enemyAvailableActions.add("Ax");
		}
		
		if(enemySkillPoints >= 4)
		{
			enemyAvailableActions.add("Kamekameha");
			enemyAvailableActions.add("Kamekameha ");	//enhances the chance of Charge being picked using the
			enemyAvailableActions.add("Kamekameha  ");	//random number generator		
		}
		
		if(enemySkillPoints >= 5)
		{
			enemyAvailableActions.add("Amen");
		}
		Vector<String> enemyAvailableSkills = enemyAvailableActions;
		
		return enemyAvailableSkills;
	}
	
	public static void HelpDirectory()
	{
		System.out.println("\nCharge - Increase the User’s skill point by 1.\r\n"
				+ "Shield - Costs 1 skill point, makes the user immune to enemy attacks, except for “Kamekameha”.\r\n"
				+ "Wave - Costs 1 skill point and deals 2 damage to the enemy target.\r\n"
				+ "Ax - Costs 2 skill points and deals 3 damage to the enemy target.\r\n"
				+ "Kamekameha - Costs 4 skill points, deals 6 damage to the enemy target.\r\n"
				+ "Amen - Costs 5 skill points, Negates damage and heals 5 health to the user.\r\n"
				+ "");
		System.out.println("Enter 1 if you wanna use Charge\r\n"
				+ "Enter 2 if you wanna use Shield\r\n"
				+ "Enter 3 if you wanna use Wave\r\n"
				+ "Enter 4 if you wanna use Ax\r\n"
				+ "Enter 5 if you wanna use Kamekameha\r\n"
				+ "Enter 6 if you wanna use Amen\r\n"
				+ "");
	}
}

