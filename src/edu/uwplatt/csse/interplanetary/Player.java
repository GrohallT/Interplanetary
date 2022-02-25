package edu.uwplatt.csse.interplanetary;

/*
 * 
 * This class handles all the data required for a player
 * This also contains all methods needed to get and change the player data
 */
/**

 @author EJ
 */
public class Player
{

   private double miningStrength;
   private double farmingStrength;
   private double combatStrength;
   private int fuelLevel;
   private int supplyLevel;

   // Default constructor
   Player()
   {
      fuelLevel = 100;
      supplyLevel = 10;
      farmingStrength = 10;
      miningStrength = 10;
      combatStrength = 10;
   }

   // Parameterized constructor
   public Player(int fuelLevel, int supplyLevel, double miningStrength, double farmingStrength, double combatStrength)
   {
      this.fuelLevel = fuelLevel;
      this.supplyLevel = supplyLevel;
      this.miningStrength = miningStrength;
      this.farmingStrength = farmingStrength;
      this.combatStrength = combatStrength;
   }

   //All methods bellow are are getter and setter methods for the fields
   public int getFuelLevel()
   {
      return this.fuelLevel;
   }

   public void setFuelLevel(int fuelLevel)
   {
      this.fuelLevel = fuelLevel;
   }

   public int getSupplyLevel()
   {
      return this.supplyLevel;
   }

   public void setSupplyLevel(int supplyLevel)
   {
      this.supplyLevel = supplyLevel;
   }

   public double getMiningStrength()
   {
      return this.miningStrength;
   }

   public void setMiningStrength(double miningStrength)
   {
      this.miningStrength = miningStrength;
   }

   public double getFarmingStrength()
   {
      return this.farmingStrength;
   }

   public void setFarmingStrength(double farmingStrength)
   {
      this.farmingStrength = farmingStrength;
   }

   public double getCombatStrength()
   {
      return this.combatStrength;
   }

   public void setCombatStrength(double combatStrength)
   {
      this.combatStrength = combatStrength;
   }

}
