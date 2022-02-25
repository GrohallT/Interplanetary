/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package edu.uwplatt.csse.interplanetary.planet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

/**
* This class contains all information relating to a particular descriptor that
* may be included in the fromSpaceDescription of a Planet. These phrases are 
* used to construct a very basic description of the environment on a
* particular planet that the player can view by clicking on the planet in the
* planet select view.
* @author Reese
*/
@XmlRootElement(name = "FsdPhrase")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "quantityID", "idealValue", "phraseContent" })
public class FsdPhrase 
{
   @XmlElement(name = "quantityID")
   private String quantityID;
   
   @XmlElement(name = "idealValue")
   private int idealValue;
   
   @XmlElement(name = "phraseContent")
   private String phraseContent;
   
   /**
   * Class constructor that parameterizes all instance variables.
   * @param quantityID    string that identifies which quantity this phrase
   *                      describes. The quantity is determined by some
   *                      combination of a planet's statistics.
   * @param idealValue    the integer value of a quantity that this phrase is
   *                      thought to most accurately represent
   * @param phraseContent the string that is displayed to the user, also known
   *                      as the descriptor
   */
   public FsdPhrase(String quantityID, int idealValue, String phraseContent)
   {
      this.quantityID = quantityID;
      this.idealValue = idealValue;
      this.phraseContent = phraseContent;
   }
   
   /**
   * Default class constructor.
   */
   public FsdPhrase()
   {
      quantityID = "";
      idealValue = 0;
      phraseContent = "";
   }
   
   public String getQuantityID()
   {
      return quantityID;
   }
   
   public int getIdealValue()
   {
      return idealValue;
   }
   
   public String getPhraseContent()
   {
      return phraseContent;
   }
   
   public void setQuantityID(String quantityID)
   {
      this.quantityID = quantityID;
   }
   
   public void setIdealValue(int idealValue)
   {
      this.idealValue = idealValue;
   }
   
   public void setPhraseContent(String phraseContent)
   {
      this.phraseContent = phraseContent;
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
      return "[" + quantityID + ", " + idealValue + ", " + phraseContent + "]";
   }
}
