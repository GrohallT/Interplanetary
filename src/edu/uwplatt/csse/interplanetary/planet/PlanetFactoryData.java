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
* This class holds the ArrayLists of information that are unmarshaled from XML
* and passed to the PlanetFactory. This information includes all objects of
* FpdPhrase, FsdPhrase, and PlanetImageItem. This information is used in the
* process of randomly generating planets.
* @author Reese
*/
@XmlRootElement(name = "PlanetFactoryData")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "fpdPhraseList", "fsdPhraseList", "planetImageItemList" })
public class PlanetFactoryData 
{
   @XmlElementWrapper(name = "fpdPhraseList")
   @XmlElement(name = "FpdPhrase")
   private ArrayList<FpdPhrase> fpdPhraseList;
   
   @XmlElementWrapper(name = "fsdPhraseList")
   @XmlElement(name = "FsdPhrase")
   private ArrayList<FsdPhrase> fsdPhraseList;
   
   @XmlElementWrapper(name = "planetImageItemList")
   @XmlElement(name = "PlanetImageItem")
   private ArrayList<PlanetImageItem> planetImageItemList;
   
   /**
   * Default class constructor.
   */
   public PlanetFactoryData()
   {
      fpdPhraseList = new ArrayList<>();
      fsdPhraseList = new ArrayList<>();
      planetImageItemList = new ArrayList<>();
   }
   
   /**
   * Adds the provided FpdPhrase to the list of FPD descriptor objects.
   * @param fpdPhrase FpdPhrase object that contains information relevant to a
   *                  particular FPD descriptor
   */
   public void addFpdPhrase(FpdPhrase fpdPhrase)
   {
      fpdPhraseList.add(fpdPhrase);
   }
   
   /**
   * Adds the provided FsdPhrase to the list of FSD descriptor objects.
   * @param fsdPhrase FsdPhrase object that contains information relevant to a
   *                  particular FSD descriptor
   */
   public void addFsdPhrase(FsdPhrase fsdPhrase)
   {
      fsdPhraseList.add(fsdPhrase);
   }
   
   /**
   * Adds the provided PlanetImageItem to the list of PlanetImageItem objects.
   * @param planetImageItem PlanetImageItem object that contains information 
   *                        relevant to a particular planet image
   */
   public void addPlanetImageItem(PlanetImageItem planetImageItem)
   {
      planetImageItemList.add(planetImageItem);
   }
   
   public ArrayList<FpdPhrase> getFpdPhraseList()
   {
      return fpdPhraseList;
   }
   
   public ArrayList<FsdPhrase> getFsdPhraseList()
   {
      return fsdPhraseList;
   }
   
   public ArrayList<PlanetImageItem> getPlanetImageItemList()
   {
      return planetImageItemList;
   }
   
   public void setFpdPhraseList(ArrayList<FpdPhrase> fpdPhraseList)
   {
      this.fpdPhraseList = fpdPhraseList;
   }
   
   public void setFsdPhraseList(ArrayList<FsdPhrase> fsdPhraseList)
   {
      this.fsdPhraseList = fsdPhraseList;
   }
   
   public void setPlanetImageItemList(ArrayList<PlanetImageItem> planetImageItemList)
   {
      this.planetImageItemList = planetImageItemList;
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
