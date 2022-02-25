package edu.uwplatt.csse.interplanetary;

import edu.uwplatt.csse.interplanetary.planet.*;
import edu.uwplatt.csse.interplanetary.views.*;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
* InterplanetaryLauncher is the main class that runs the Interplanetary
* program. The start method unmarshals the planet factory data
* file, creates the planet factory, and opens the Start Menu on top of
* rootPane.
* @author Reese
*/
public class InterplanetaryLauncher extends Application
{
   // Graphics
   private Scene scene;
   private Pane rootPane;
   private Text debugText;
   private StartMenu startMenu;
   
   // Planet Factory
   private PlanetFactoryData planetFactoryData;
   private PlanetFactory planetFactory;
   
   // Other
   private boolean startUpSuccess = true;
   
   /**
   * Unmarshals the planet factory data file, creates the planet factory, and
   * opens the Start Menu on top of rootPane. It is called when the program is
   * opened.
   * @param primaryStage    stage the program will be displayed on
   */
   @Override
   public void start(Stage primaryStage)
   {
      // Debug Text - currently notifies the user if the planet data file
      // could not be successfully unmarshaled
      debugText = new Text();
      debugText.setFont(new Font(12.0));
      debugText.setFill(Color.DODGERBLUE);
      debugText.setText("Launching program...");
      
      // Set up PlanetFactory
      planetFactoryData = unmarshallPlanetFactoryData();
      planetFactory = new PlanetFactory(
            planetFactoryData.getFpdPhraseList(),
            planetFactoryData.getFsdPhraseList(),
            planetFactoryData.getPlanetImageItemList());
      
      // Set up root pane
      rootPane = new Pane();
      rootPane.setPrefSize(960.0, 540.0);
      rootPane.getChildren().add(debugText);
      debugText.relocate(0.0, 0.0);
      debugText.setWrappingWidth(960.0);
      
      // Set up Start Menu if start up was successful
      if (startUpSuccess)
         startMenu = new StartMenu(rootPane, planetFactory);
      
      // Set up Scene and Stage
      scene = new Scene(rootPane, 960, 540);
      primaryStage.setTitle("Interplanetary");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   /**
   * Unmarshals the planet factory data file.
   * @return the PlanetFactoryData object that was unmarshaled
   */
   private PlanetFactoryData unmarshallPlanetFactoryData()
   {
      try
      {
         JAXBContext context = JAXBContext.newInstance(PlanetFactoryData.class);
         return (PlanetFactoryData) context.createUnmarshaller().unmarshal(PlanetFactoryData.class.getResourceAsStream("/edu/uwplatt/csse/interplanetary/planet/planet_factory_data.xml"));
      }
      catch(JAXBException e)
      {
         debugText.setText(debugText.getText() + "\nJAXBException exception occured in unmarshallPlanetFactoryData.\nFactoryData not loaded.");
         System.out.println(e);
         startUpSuccess = false;
         return new PlanetFactoryData();
      }
      catch(Exception e)
      {
         debugText.setText(debugText.getText() + "\nUnknown exception occured in unmarshallPlanetFactoryData\nFactoryData not loaded.");
         System.out.println(e);
         startUpSuccess = false;
         return new PlanetFactoryData();
      }
   }
   
   /**
   * Launches the program.
   * @param args String array containing the command line arguments
   */
   public static void main(String[] args)
   {
      launch(args);
   }
}
