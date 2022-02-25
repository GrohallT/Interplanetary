package edu.uwplatt.csse.interplanetary.planet;

import java.util.ArrayList;
import java.util.Random;

/**
* ProbabilityDistribution is used to give a each item in a group of objects a
* relative share of probability and to then choose an item from the group
* according to their probabilities.
* @author Reese
*/
public class ProbabilityDistribution 
{
   private final ArrayList<PDitem> pdiList;
   private final Random rand;
   
   /**
   * Class constructor.
   */
   public ProbabilityDistribution()
   {
      pdiList = new ArrayList<>();
      rand = new Random(System.nanoTime());
   }
   
   /**
   * Creates a PDitem from an item and its relative share of probability, then
   * adds the PDitem to the item list.
   * @param objRef    Object reference of the item to be added
   * @param probShare double to use as the relative share of probability for
   *                  the item
   */
   public void addPDitem(Object objRef, double probShare)
   {
      pdiList.add(new PDitem(objRef, probShare));
   }
   
   /**
   * Clears pdiList, the list of PDitems containing items and their
   * probability shares.
   */
   public void clearPdiList()
   {
      pdiList.clear();
   }
   
   /**
   * Chooses a random item from the group.
   * @return Object that was chosen
   */
   public Object chooseRandomObject()
   {
      // Find total of all shares
      double total = 0.0;
      int pdiListSize = pdiList.size();
      for (int i = 0; i < pdiListSize; i++)
         total += pdiList.get(i).probShare;
      
      // Choose value within range that will determine selection
      double chosenVal = total * rand.nextDouble();
      
      // Determine which item was selected.
      Object chosenObj = null;
      double searchSum = 0.0;
      int i = 0;
      while (i < pdiListSize && chosenObj == null)
      {
         searchSum += pdiList.get(i).probShare;
         if (searchSum > chosenVal)
            chosenObj = pdiList.get(i).objRef;
         i++;
      }
      return chosenObj;
   }
   
   /**
   * PDitem holds an item of the group and the item's associated relative
   * share of probability.
   * @author Reese
   */
   private class PDitem
   {
      private final Object objRef; // private members can be accessed by outer class
      private final double probShare;
      
      /**
      * Class constructor.
      * @param objRef    Object reference of the item
      * @param probShare double to use as the relative share of probability
      *                  for the item
      */
      public PDitem(Object objRef, double probShare)
      {
         this.objRef = objRef;
         this.probShare = probShare;
      }
   }
}
