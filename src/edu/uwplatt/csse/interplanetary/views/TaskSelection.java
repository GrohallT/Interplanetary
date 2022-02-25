package edu.uwplatt.csse.interplanetary.views;

import edu.uwplatt.csse.interplanetary.*;
import edu.uwplatt.csse.interplanetary.planet.*;

import java.util.Random;
/**
* This class performs the methods for the tasks that the player selected. The player's 
* stats are also changed and updated here.

@author taryn
*/
public class TaskSelection
{
   private final int HEAVY_CHANGE = 7;
   private final int MEDIUM_HEAVY_CHANGE = 5;
   private final int MEDIUM_LOW_CHANGE = 3;
   private final int LOW_CHANGE = 1;
   
   private Player player;
   private Planet planet;
   private Random rand;
   private double statDiff;
   private String outcomeMessage;
   
   /**
   * Constructor for the TaskSelection class sets the passed in player class to be use
   * able in this class.
   *
   * @param  inPlayer  the Player object passed in from the previous class
   * @param  planSel   the Planet object passed in from the previous class
   */
   public TaskSelection(Player inPlayer, Planet planSel)
   {
      this.player = inPlayer;
      this.planet = planSel;
      rand = new Random();
   }

   /**
   * Returns the new player stats to be able to raise and lower their stats. Also
   * checks the range of the distance to change how much stats are raised and lowered.
   *
   * @param  playerStat   the Player statistic passed in from one of the tasks
   * @param  planetStat   the Planet statistic passed in from one of the tasks
   */
   public double statChanger(double playerStat, int planetStat)
   {
      // Low stat difference is a high stat change
      if (statDiff > 0)
      {
         if(statDiff <= 25)
         {
            playerStat += HEAVY_CHANGE;
         } 
         else if(statDiff <= 50)
         {
            playerStat += MEDIUM_HEAVY_CHANGE;
         } 
         else if(statDiff <= 75)
         {
            playerStat += MEDIUM_LOW_CHANGE;
         } 
         else if(statDiff <= 100)
         {
            playerStat += LOW_CHANGE;
         }
      }
      else if(statDiff == 0)
      {
         playerStat = playerStat;
      }
      // Makes the stat changes opposite of the success ones lower difference is
      // less stat changes
      else
      {
         if(statDiff >=  -25)
         {
            playerStat -= LOW_CHANGE;
         } 
         else if(statDiff >= -50)
         {
            playerStat -= MEDIUM_LOW_CHANGE;
         }
         else if(statDiff >= -75)
         {
            playerStat -= MEDIUM_HEAVY_CHANGE;
         } 
         else if(statDiff >= -100)
         {
            playerStat -= HEAVY_CHANGE;
         }
      }
      
      return playerStat;
   }
   
   /**
   * The Mine method for the task of mining. This method will check to see if the user's
   * stats minus the planet stats are negative. Then it sends the stats to the
   * ChangeStats method, to calculate the change in stats. Returns a message based on how
   * the changes went.
   *
   * @return      a message telling the user if they succeeded or failed the task and the stat changes
   */
   public String Mine()
   {
      statDiff = player.getMiningStrength() - planet.getMiningDiff();
      player.setMiningStrength(statChanger(player.getMiningStrength(), planet.getMiningDiff()));
      
      if(statDiff > 0)
      {
         int fuelIncrease = rand.nextInt(MEDIUM_HEAVY_CHANGE) + 1;
         
         outcomeMessage = "Mining gained \n";
         outcomeMessage += fuelIncrease + " Fuel gained";
         player.setFuelLevel(player.getFuelLevel() + fuelIncrease);
         outcomeMessage += "\nNew player fuel stat: " + String.valueOf(player.getFuelLevel());
      }
      else if(statDiff == 0)
      {
         outcomeMessage = "Mining stat not changed";
      }
      else
      {
         outcomeMessage = "Mining lost ";
      }
      
      outcomeMessage += "\nNew player mining stat: " + String.valueOf(player.getMiningStrength()) + "\n";
      return outcomeMessage;
   }
   
   /**
   * The Farm method for the task of farming. This method will check to see if the user's
   * stats minus the planet stats are negative. Then it sends the stats to the
   * ChangeStats method, to calculate the change in stats. Returns a message based on how
   * the changes went.
   *
   * @return      a message telling the user if they succeeded or failed the task and the stat changes
   */
   public String Farm()
   {
      statDiff = player.getFarmingStrength() - planet.getFarmingDiff();
      player.setFarmingStrength(statChanger(player.getFarmingStrength(), planet.getFarmingDiff()));
      
      if (statDiff > 0)
      {
         outcomeMessage = "Farming gained ";
      }
      else if(statDiff == 0)
      {
         outcomeMessage = "Farming stat not changed";
      }
      else
      {
         outcomeMessage = "Farming lost ";
      }
      
      outcomeMessage += "\nNew player farming stat: " + String.valueOf(player.getFarmingStrength()) + "\n";
      return outcomeMessage;
   }

   /**
   * The Combat method for the task of combat. This method will check to see if the user's
   * stats minus the planet stats are negative. Then it sends the stats to the
   * ChangeStats method, to calculate the change in stats. Returns a message based on how
   * the changes went.
   *
   * @return      a message telling the user if they succeeded or failed the task and the stat changes
   */
   public String Combat()
   {
      statDiff = player.getCombatStrength() - planet.getHostility();
      player.setCombatStrength(statChanger(player.getCombatStrength(), planet.getHostility()));
      
      if (statDiff > 0)
      {
         outcomeMessage = "Combat gained ";
      }
      else if(statDiff == 0)
      {
         outcomeMessage = "Combat stat not changed";
      }
      else
      {
         outcomeMessage = "Combat lost ";
      }
      
      outcomeMessage += "\nNew player combat stat: " + String.valueOf(player.getCombatStrength()) + "\n";
      return outcomeMessage;
   }

   /**
   * The Collaborate method for the task of collaborate. This method will check the planets hostility stats
   * in some ranges the outcome has a random chance of being positive or negative. If the hostility the player 
   * will automatically lose stats for all stats. if the hostility is less than 25 the player will 
   * automatically get a positive outcome. This tasks affects every player stat.
   *
   * @return      a message telling the user if they succeeded or failed the task and the stat changes
   */
   public String Collaborate()
   {
      int randomNumber = rand.nextInt(20);
      
      // If the hostility is between 25 and 75 then have a chance to fail, but a larger chance to succeed
      // with low stat changes for each outcome
      if (planet.getHostility() >= 25 && planet.getHostility() < 75)
      {
         if(randomNumber >= 9 && randomNumber <= 15)
         {
            player.setFuelLevel(player.getFuelLevel() - LOW_CHANGE);
           player.setMiningStrength(player.getMiningStrength() - LOW_CHANGE);
           player.setFarmingStrength(player.getFarmingStrength() - LOW_CHANGE);
           player.setCombatStrength(player.getCombatStrength() - LOW_CHANGE);
           player.setSupplyLevel(player.getSupplyLevel() - LOW_CHANGE);
           outcomeMessage = "All player stats were lowered by " + LOW_CHANGE;
         }
         else
         {
           player.setFuelLevel(player.getFuelLevel() + LOW_CHANGE);
           player.setMiningStrength(player.getMiningStrength() + LOW_CHANGE);
           player.setFarmingStrength(player.getFarmingStrength() + LOW_CHANGE);
           player.setCombatStrength(player.getCombatStrength() + LOW_CHANGE);
           player.setSupplyLevel(player.getSupplyLevel() + LOW_CHANGE);
           outcomeMessage = "All player stats were raised by " + LOW_CHANGE;
         }
      }
      // If the hostility is between 75 and 100 then make the random check more difficult
      // also lower stats by a larger amount
      else if(planet.getHostility() >= 75 && planet.getHostility() < 100)
      {
         if(randomNumber >= 0 && randomNumber < 10)
         {
            player.setFuelLevel(player.getFuelLevel() - MEDIUM_LOW_CHANGE);
           player.setMiningStrength(player.getMiningStrength() - MEDIUM_LOW_CHANGE);
           player.setFarmingStrength(player.getFarmingStrength() - MEDIUM_LOW_CHANGE);
           player.setCombatStrength(player.getCombatStrength() - MEDIUM_LOW_CHANGE);
           player.setSupplyLevel(player.getSupplyLevel() - MEDIUM_LOW_CHANGE);
           outcomeMessage = "All player stats were lowered by " + MEDIUM_LOW_CHANGE;
         }
         else
         {
            player.setFuelLevel(player.getFuelLevel() + MEDIUM_LOW_CHANGE);
            player.setMiningStrength(player.getMiningStrength() + MEDIUM_LOW_CHANGE);
            player.setFarmingStrength(player.getFarmingStrength() + MEDIUM_LOW_CHANGE);
            player.setCombatStrength(player.getCombatStrength() + MEDIUM_LOW_CHANGE);
            player.setSupplyLevel(player.getSupplyLevel() + MEDIUM_LOW_CHANGE);
            outcomeMessage = "All player stats were raised by " + MEDIUM_LOW_CHANGE;
         }
      }
      // Auto fail the collaborate task and lose a large amount of stats
      else if(planet.getHostility() == 100)
      {
         player.setFuelLevel(player.getFuelLevel() - HEAVY_CHANGE);
         player.setMiningStrength(player.getMiningStrength() - HEAVY_CHANGE);
         player.setFarmingStrength(player.getFarmingStrength() - HEAVY_CHANGE);
         player.setCombatStrength(player.getCombatStrength() - HEAVY_CHANGE);
         player.setSupplyLevel(player.getSupplyLevel() - HEAVY_CHANGE);
         outcomeMessage = "All player stats were lowered by " + HEAVY_CHANGE;
      }
      // Auto succeed the collaborate task and gain a medium large amount of stats
      else
      {
         player.setFuelLevel(player.getFuelLevel() + MEDIUM_HEAVY_CHANGE);
         player.setMiningStrength(player.getMiningStrength() + MEDIUM_HEAVY_CHANGE);
         player.setFarmingStrength(player.getFarmingStrength() + MEDIUM_HEAVY_CHANGE);
         player.setCombatStrength(player.getCombatStrength() + MEDIUM_HEAVY_CHANGE);
         player.setSupplyLevel(player.getSupplyLevel() + MEDIUM_HEAVY_CHANGE);
         outcomeMessage = "All player stats were raised by " + MEDIUM_HEAVY_CHANGE;
      }
      
      outcomeMessage += "\nNew player fuel stat: " + String.valueOf(player.getFuelLevel());
      outcomeMessage += "\nNew player mining stat: " + String.valueOf(player.getMiningStrength());
      outcomeMessage += "\nNew player farming stat: " + String.valueOf(player.getFarmingStrength());
      outcomeMessage += "\nNew player combat stat: " + String.valueOf(player.getCombatStrength());
      outcomeMessage += "\nNew player supply stat: " + String.valueOf(player.getSupplyLevel()) + "\n";
      
      return outcomeMessage;
   }
}
