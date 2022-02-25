/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package edu.uwplatt.csse.interplanetary.planet;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
* This class contains all information relating to a particular planet image
* that may be chosen to represent a Planet. Objects of this class contain a
* string representing the path to use to locate the planet image and an
* ArrayList of strings naming the colors contained by the planet image.
* @author Reese
*/
@XmlRootElement(name = "PlanetImageItem")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "imageFileName", "colorList" })
public class PlanetImageItem 
{
   @XmlElement(name = "imageFileName")
   private String imageFileName;
   
   @XmlElementWrapper(name = "colorList")
   @XmlElement(name = "color")
   private ArrayList<String> colorList;
   
   /**
   * Class constructor that parameterizes all instance variables.
   * @param imageFileName string that represents the path to use to locate the
   *                      image file this object corresponds to
   * @param colorList     the ArrayList of strings naming the colors that
   *                      this planet image contains
   */
   public PlanetImageItem(String imageFileName, ArrayList<String> colorList)
   {
      this.imageFileName = imageFileName;
      this.colorList = colorList;
   }
   
   /**
   * Default class constructor.
   */
   public PlanetImageItem()
   {
      imageFileName = "/planet000.png";
      colorList = new ArrayList<>();
   }
   
   /**
   * Adds the provided color to the list of colors this image contains.
   * @param color string to add to the list of colors this image contains
   */
   public void addColor(String color)
   {
      colorList.add(color);
   }
   
   public String getImageFileName()
   {
      return imageFileName;
   }
   
   public ArrayList<String> getColorList()
   {
      return colorList;
   }
   
   public void setImageFileName(String imageFileName)
   {
      this.imageFileName = imageFileName;
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
      return "[" + imageFileName + ", " + colorList + "]";
   }
}
