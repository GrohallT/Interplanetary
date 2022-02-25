/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package edu.uwplatt.csse.interplanetary.planet;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
* This class contains all information relating to a particular descriptor that
* may be included in the fromPlanetDescription of a Planet. These phrases are 
* used to construct a detailed description of the environment on the planet
* when the player lands.
* @author Reese
*/
@XmlRootElement(name = "FpdPhrase")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "planetAttribute", "idealValue", "phraseContent", "colorList" })
public class FpdPhrase 
{
   @XmlElement(name = "planetAttribute")
   private String planetAttribute;
   
   @XmlElement(name = "idealValue")
   private int idealValue;
   
   @XmlElement(name = "phraseContent")
   private String phraseContent;
   
   @XmlElementWrapper(name = "colorList")
   @XmlElement(name = "color")
   private ArrayList<String> colorList;
   
   /**
   * Class constructor that parameterizes all instance variables.
   * @param planetAttribute string that identifies which statistic of the
   *                        planet this phrase describes
   * @param idealValue      the integer value of a planet statistic that this
   *                        phrase is thought to most accurately represent
   * @param phraseContent   the string that is displayed to the user, also
   *                        known as the descriptor
   * @param colorList       the ArrayList of strings naming the colors that
   *                        this descriptor requests for the planet image to
   *                        contain
   */
   public FpdPhrase(String planetAttribute, int idealValue,
         String phraseContent, ArrayList<String> colorList)
   {
      this.planetAttribute = planetAttribute;
      this.idealValue = idealValue;
      this.phraseContent = phraseContent;
      this.colorList = colorList;
   }
   
   /**
   * Default class constructor.
   */
   public FpdPhrase()
   {
      planetAttribute = "";
      idealValue = 0;
      phraseContent = "";
      colorList = new ArrayList<>();
   }
   
   /**
   * Adds the provided color to the list of colors associated with this
   * descriptor.
   * @param color string to add to the list of colors associated with this
   *              descriptor
   */
   public void addColor(String color)
   {
      colorList.add(color);
   }
   
   public String getPlanetAttribute()
   {
      return planetAttribute;
   }
   
   public int getIdealValue()
   {
      return idealValue;
   }
   
   public String getPhraseContent()
   {
      return phraseContent;
   }
   
   public ArrayList<String> getColorList()
   {
      return colorList;
   }
   
   public void setPlanetAttribute(String planetAttribute)
   {
      this.planetAttribute = planetAttribute;
   }
   
   public void setIdealValue(int idealValue)
   {
      this.idealValue = idealValue;
   }
   
   public void setPhraseContent(String phraseContent)
   {
      this.phraseContent = phraseContent;
   }
   
   public void setColorList(ArrayList<String> colorList)
   {
      this.colorList = colorList;
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
      return "[" + planetAttribute + ", " + idealValue + ", " + phraseContent
            + ", " + colorList + "]";
   }
}
