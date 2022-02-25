package edu.uwplatt.csse.interplanetary.planet;

import edu.uwplatt.csse.interplanetary.views.*;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

/**
* This class represents a planet. Each planet has numerical statistics, text
* descriptions, and an image that represents it on the planet select view and
* single planet view. Randomized planets are created by PlanetFactory.
* @author Reese
*/
public class Planet 
{
   // Numerical values
   private final Random rand;
   private int farmingDiff;
   private int miningDiff;
   private int hostility;
   private int advancementLevel;
   private int size;
   private int distance;
   
   // Visual information
   private ArrayList<String> requestedColors;
   private String imageFileName;
   private Image planetImage;
   private ImageView planetIV;
   
   // Text descriptions
   private String descriptionFromSpace;
   private String descriptionFromPlanet;
   
   // Other
   private PlanetSelectView planetSelectView;
   
   /**
   * Class constructor with partial parameterization. This constructor is used
   * by the PlanetFactory class in the process of creating a randomized
   * planet.
   * @param farmingDiff       integer value that rates the difficulty of
   *                          farming on this planet
   * @param miningDiff        integer value that rates the difficulty of
   *                          mining on this planet
   * @param hostility         integer value that rates the hostility of life
   *                          forms on this planet
   * @param advancementLevel  integer value that rates the advancement of life
   *                          forms on this planet
   * @param size              integer value representing the size of this
   *                          planet
   */
   public Planet(int farmingDiff, int miningDiff, int hostility,
         int advancementLevel, int size)
   {
      rand = new Random();
      this.farmingDiff = farmingDiff;
      this.miningDiff = miningDiff;
      this.hostility = hostility;
      this.advancementLevel = advancementLevel;
      this.size = size;
      this.distance = rand.nextInt(5) + 1;
      
      requestedColors = new ArrayList<>();
      imageFileName = "edu/uwplatt/csse/interplanetary/planet/planet000.png";
      planetImage = new Image(imageFileName);
      planetIV = new ImageView(planetImage);
      
      descriptionFromSpace = "";
      descriptionFromPlanet = "";
      
      planetSelectView = null;
   }
   
   /**
   * Adds a color to the ArrayList of colors that the image chosen for this
   * planet should likely include.
   * @param color String that identifies what color to add to the requested
   *              color ArrayList
   */
   public void addRequestedColor(String color)
   {
      requestedColors.add(color);
   }
   
   public int getFarmingDiff()
   {
      return farmingDiff;
   }
   
   public int getMiningDiff()
   {
      return miningDiff;
   }
   
   public int getHostility()
   {
      return hostility;
   }
   
   public int getAdvancementLevel()
   {
      return advancementLevel;
   }
   
   public int getSize()
   {
      return size;
   }
   
   public int getDistance()
   {
      return distance;
   }
   
   public ArrayList<String> getRequestedColors()
   {
      return requestedColors;
   }
   
   public String getImageFileName()
   {
      return imageFileName;
   }
   
   public Image getImage()
   {
      return planetImage;
   }
   
   public ImageView getImageView()
   {
      return planetIV;
   }
   
   public String getDescriptionFromPlanet()
   {
      return descriptionFromPlanet;
   }
   
   public String getDescriptionFromSpace()
   {
      return descriptionFromSpace;
   }
   
   /**
   * Replaces planetImage and planetIV with new objects using the image file
   * name that was passed. Sets imageFileName to the new name. Resizes the new
   * planetIV based on size. Sets the mouse clicked event for the new
   * planetIV.
   * @param ifn String containing the image file name to use for this planet's
   *            visuals
   */
   public void setImageComponents(String ifn)
   {
      imageFileName = ifn;
      planetImage = new Image(imageFileName);
      planetIV = new ImageView(planetImage);
      planetIV.setFitHeight(50 + (1.0 * size));
      planetIV.setFitWidth(50 + (1.0 * size));
      
      // Set event handlers
      planetIV.setOnMouseClicked(e -> {
         if(planetSelectView != null)
            planetSelectView.planetClicked(this);
         e.consume();
      });
   }
   
   public void setDescriptionFromPlanet(String fpd)
   {
      descriptionFromPlanet = fpd;
   }
   
   public void setDescriptionFromSpace(String fsd)
   {
      descriptionFromSpace = fsd;
   }
   
   public void setPlanetSelectView(PlanetSelectView planetSelectView)
   {
      this.planetSelectView = planetSelectView;
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
      return "Planet Object\nfarmingDiff: " + farmingDiff
            + "\nminingDiff: " + miningDiff
            + "\nhostility: " + hostility
            + "\nadvancementLevel: " + advancementLevel
            + "\nsize: " + size
            + "\nrequestedColors: " + requestedColors
            + "\nimageFileName: " + imageFileName
            + "\nplanetImage: " + planetImage
            + "\nplanetIV: " + planetIV
            + "\ndescriptionFromSpace: " + descriptionFromSpace
            + "\ndescriptionFromPlanet: " + descriptionFromPlanet;
   }
}