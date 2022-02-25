/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package edu.uwplatt.csse.interplanetary.planet;

import java.util.ArrayList;
import java.util.Random;

/**
* PlanetFactory is used to generate randomized planets. It needs to have data
* passed to it through the constructor to function. This data comes from a
* PlanetFactoryData object which is unmarshaled from planet_factory_data.xml
* @author Reese
*/
public class PlanetFactory 
{
   private final ArrayList<FpdPhrase> fpdPhraseList;
   private final ArrayList<FsdPhrase> fsdPhraseList;
   private final ArrayList<PlanetImageItem> planetImageItemList;
   private final Random rand = new Random(System.nanoTime());
   private final ProbabilityDistribution pd = new ProbabilityDistribution();
   
   /**
   * Class constructor that takes in the data lists that are used to generate
   * randomized planets.
   * @param fpdPhraseList       ArrayList containing FpdPhrase objects that
   *                            describe phrases that can be used to form a
   *                            planet's descriptionFromPlanet
   * @param fsdPhraseList       ArrayList containing FsdPhrase objects that
   *                            describe phrases that can be used to form a
   *                            planet's descriptionFromSpace
   * @param planetImageItemList ArrayList containing PlanetImageItem objects
   *                            that describe images that can be used to
   *                            represent a planet on the planet select view
   */
   public PlanetFactory(ArrayList<FpdPhrase> fpdPhraseList,
         ArrayList<FsdPhrase> fsdPhraseList,
         ArrayList<PlanetImageItem> planetImageItemList)
   {
      this.fpdPhraseList = fpdPhraseList;
      this.fsdPhraseList = fsdPhraseList;
      this.planetImageItemList = planetImageItemList;
   }
   
   /**
   * Creates a randomized Planet object.
   * @return Planet that has been randomly generated
   */
   public Planet createRandomPlanet()
   {
      // Numerical values
      int farmingDiff = rand.nextInt(101);
      int miningDiff = rand.nextInt(101);
      int hostility = rand.nextInt(101);
      int advancementLevel = rand.nextInt(101);
      int size = rand.nextInt(101);
      
      Planet planet = new Planet(farmingDiff, miningDiff, hostility,
            advancementLevel, size);
      
      // Complex factory processes
      assignFullFpd(planet);
      assignFullFsd(planet);
      assignImageComponents(planet);
      
      return planet;
   }
   
   /**
   * Generates a "description from planet" for the planet passed in and
   * assigns it to the planet's descriptionFromPlanet.
   * @param planet Planet object to assign a "description from planet" to
   */
   private void assignFullFpd(Planet planet)
   {
      String fpd;
      
      int fpdListSize = fpdPhraseList.size();
      FpdPhrase chosenFpdPhrase;
      
      // Choose farming descriptor
      pd.clearPdiList();
      for (int i = 0; i < fpdListSize; i++)
      {
         FpdPhrase phrase = fpdPhraseList.get(i);
         if (phrase.getPlanetAttribute().equals("FarmingDiff"))
            pd.addPDitem(phrase, 100 * Math.pow(2, (-1 * Math.pow(((phrase.getIdealValue() - planet.getFarmingDiff())/ 12), 2))));
      }
      chosenFpdPhrase = (FpdPhrase)pd.chooseRandomObject();
      fpd = chosenFpdPhrase.getPhraseContent();
      for (String color : chosenFpdPhrase.getColorList())
         planet.addRequestedColor(color);
      
      // Choose mining descriptor
      pd.clearPdiList();
      for (int i = 0; i < fpdListSize; i++)
      {
         FpdPhrase phrase = fpdPhraseList.get(i);
         if (phrase.getPlanetAttribute().equals("MiningDiff"))
            pd.addPDitem(phrase, 100 * Math.pow(2, (-1 * Math.pow(((phrase.getIdealValue() - planet.getMiningDiff())/ 12), 2))));
      }
      chosenFpdPhrase = (FpdPhrase)pd.chooseRandomObject();
      fpd = fpd + " " + chosenFpdPhrase.getPhraseContent();
      for (String color : chosenFpdPhrase.getColorList())
         planet.addRequestedColor(color);
      
      // Choose hostility descriptor
      pd.clearPdiList();
      for (int i = 0; i < fpdListSize; i++)
      {
         FpdPhrase phrase = fpdPhraseList.get(i);
         if (phrase.getPlanetAttribute().equals("Hostility"))
            pd.addPDitem(phrase, 100 * Math.pow(2, (-1 * Math.pow(((phrase.getIdealValue() - planet.getHostility())/ 12), 2))));
      }
      chosenFpdPhrase = (FpdPhrase)pd.chooseRandomObject();
      fpd = fpd + " " + chosenFpdPhrase.getPhraseContent();
      for (String color : chosenFpdPhrase.getColorList())
         planet.addRequestedColor(color);
      
      // Choose advancement level descriptor
      pd.clearPdiList();
      for (int i = 0; i < fpdListSize; i++)
      {
         FpdPhrase phrase = fpdPhraseList.get(i);
         if (phrase.getPlanetAttribute().equals("AdvancementLevel"))
            pd.addPDitem(phrase, 100 * Math.pow(2, (-1 * Math.pow(((phrase.getIdealValue() - planet.getAdvancementLevel())/ 12), 2))));
      }
      chosenFpdPhrase = (FpdPhrase)pd.chooseRandomObject();
      fpd = fpd + " " + chosenFpdPhrase.getPhraseContent();
      for (String color : chosenFpdPhrase.getColorList())
         planet.addRequestedColor(color);
      
      planet.setDescriptionFromPlanet(fpd);
   }
   
   /**
   * Generates a "description from space" for the planet passed in and assigns
   * it to the planet's descriptionFromSpace.
   * @param planet Planet object to assign a "description from space" to
   */
   private void assignFullFsd(Planet planet)
   {
      // Calculate quantities (biologicalActivity and geology)
      double biologicalActivity = (0.5 * (100 - planet.getFarmingDiff())) + (0.2 * planet.getHostility()) + (0.3 * planet.getAdvancementLevel());
      double geology = (0.7 * (100 - planet.getMiningDiff())) + (0.1 * (100 - planet.getFarmingDiff())) + (0.2 * planet.getAdvancementLevel());
      
      String fsd;
      
      int fsdListSize = fsdPhraseList.size();
      FsdPhrase chosenFsdPhrase;
      
      // Choose biologicalActivity descriptor
      pd.clearPdiList();
      for (int i = 0; i < fsdListSize; i++)
      {
         FsdPhrase phrase = fsdPhraseList.get(i);
         if (phrase.getQuantityID().equals("BiologicalActivity"))
            pd.addPDitem(phrase, 100 * Math.pow(2, (-1 * Math.pow(((phrase.getIdealValue() - biologicalActivity)/ 12), 2))));
      }
      chosenFsdPhrase = (FsdPhrase)pd.chooseRandomObject();
      fsd = chosenFsdPhrase.getPhraseContent();
      
      // Choose geology descriptor
      pd.clearPdiList();
      for (int i = 0; i < fsdListSize; i++)
      {
         FsdPhrase phrase = fsdPhraseList.get(i);
         if (phrase.getQuantityID().equals("Geology"))
            pd.addPDitem(phrase, 100 * Math.pow(2, (-1 * Math.pow(((phrase.getIdealValue() - geology)/ 12), 2))));
      }
      chosenFsdPhrase = (FsdPhrase)pd.chooseRandomObject();
      fsd = fsd + " " + chosenFsdPhrase.getPhraseContent();
      
      planet.setDescriptionFromSpace(fsd);
   }
   
   /**
   * Chooses an appropriate image for the planet passed in by comparing the
   * planet's requestedColors to the colorList of each PlanetImageItem. Each
   * PlanetImageItem is put into pd and given a probability proportional to
   * the number of requested colors it matches. A PlanetImageItem is then
   * randomly selected from pd and its image file name is used to set the
   * image components of the planet.
   * @param planet Planet object to assign image components to
   */
   private void assignImageComponents(Planet planet)
   {
      pd.clearPdiList();
      int matchCount;
      for (PlanetImageItem pii : planetImageItemList)
      {
         matchCount = 0;
         for (String color1 : planet.getRequestedColors())
            for (String color2 : pii.getColorList())
               if (color1.equals(color2))
                  matchCount++;
         pd.addPDitem(pii, Math.pow(4, matchCount));
      }
      String ifn = ((PlanetImageItem)pd.chooseRandomObject()).getImageFileName();
      
      planet.setImageComponents("edu/uwplatt/csse/interplanetary/planet" + ifn);
   }
   
   /**
   * Returns a cleanly formatted string that lists the toStrings of all
   * instance variables of this object.
   * @return the string that lists out the toStrings of all instance variables
   *         in this object
   */
   @Override
   public String toString()
   {
      return fpdPhraseList.toString() + "\n" + fsdPhraseList + "\n" + planetImageItemList;
   }
}
