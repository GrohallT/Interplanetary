package edu.uwplatt.csse.interplanetary.views;

import edu.uwplatt.csse.interplanetary.*;
import edu.uwplatt.csse.interplanetary.planet.*;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
* PlanetSelectView holds all the graphics needed for the planet select view
* and single planet view. It also handles user interactions with those views,
* holds the list of planets on the planet select view, and positions those
* planets randomly.
* @author Reese and EJ
*/
public class PlanetSelectView
{
   // Graphics
   private final Pane rootNode;
   private final Pane planetSelectViewBase;
   private final Pane singlePlanetViewBase;
   private final ImageView planetBig = new ImageView();
   private final Label fromSpaceDesc = new Label("test");
   private Planet selectPlanet;
   private ImageView rocketIV;
   
   // Planet
   private final PlanetFactory planetFactory;
   private final ArrayList<Planet> planetList;
   
   // Other
   private final Random rand = new Random(System.nanoTime());
   
   /**
   * Class constructor with partial parameterization. The constructor also
   * handles the layout of JavaFX components for the planet select view and
   * single planet view.
   * @param rootNode        the pane that the two view bases will be placed on
   *                        top of
   * @param planetFactory   PlanetFactory that this class uses to
   *                        generate randomized planets
   * @param player1         Player object holding the player's statistics
   */
   public PlanetSelectView(Pane rootNode, PlanetFactory planetFactory, Player player1)
   {
       //preps both panes for game
      this.rootNode = rootNode;
      planetSelectViewBase = new Pane();
      singlePlanetViewBase = new Pane();

      this.rootNode.getChildren().add(planetSelectViewBase);
      this.rootNode.getChildren().add(singlePlanetViewBase);

      planetSelectViewBase.setPrefSize(rootNode.getPrefWidth(), rootNode.getPrefHeight());
      singlePlanetViewBase.setPrefSize(rootNode.getPrefWidth(), rootNode.getPrefHeight());

      this.planetFactory = planetFactory;
      planetList = new ArrayList<>();

      BackgroundImage myBI = new BackgroundImage(new Image("edu/uwplatt/csse/interplanetary/views/planet_select_bkg_2160x3840.jpeg",
      planetSelectViewBase.getPrefWidth(),
      planetSelectViewBase.getPrefHeight(), false, true),
      BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
      BackgroundSize.DEFAULT);
      planetSelectViewBase.setBackground(new Background(myBI));
      singlePlanetViewBase.setBackground(new Background(myBI));
      
      // Rocket
      Image rocket = new Image("edu/uwplatt/csse/interplanetary/views/planet_select_rocket.png");
      rocketIV = new ImageView(rocket);
      planetSelectViewBase.getChildren().add(rocketIV);
      rocketIV.relocate(1805 / 4, 1368 / 4);
      rocketIV.setFitHeight(rocket.getHeight() / 4);
      rocketIV.setFitWidth(rocket.getWidth() / 4);
      
      // HUD background
      Image hudBkg = new Image("edu/uwplatt/csse/interplanetary/views/player_hud_bkg.png");
      ImageView hudBkgIV = new ImageView(hudBkg);
      ImageView hudBkgIV2 = new ImageView(hudBkgIV.getImage());
      planetSelectViewBase.getChildren().add(hudBkgIV);
      singlePlanetViewBase.getChildren().add(hudBkgIV2);
      hudBkgIV.relocate(210 / 4, 1683 / 4);
      hudBkgIV.setFitHeight(hudBkg.getHeight() / 4);
      hudBkgIV.setFitWidth(hudBkg.getWidth() / 4);
      hudBkgIV2.relocate(210 / 4, 1683 / 4);
      hudBkgIV2.setFitHeight(hudBkg.getHeight() / 4);
      hudBkgIV2.setFitWidth(hudBkg.getWidth() / 4);
      //player stat info
      Label hudInfo1 = new Label("Fuel " + player1.getFuelLevel() + "    " + "Supply " + player1.getSupplyLevel() + "    " + "Mining " + player1.getMiningStrength()
            + "    " + "Farming " + player1.getFarmingStrength() + "    " + "Combat " + player1.getCombatStrength());
      Label hudInfo2 = new Label("Fuel " + player1.getFuelLevel() + "    " + "Supply " + player1.getSupplyLevel() + "    " + "Mining " + player1.getMiningStrength()
            + "    " + "Farming " + player1.getFarmingStrength() + "    " + "Combat " + player1.getCombatStrength());
      hudInfo1.setTranslateY(450);
      hudInfo1.setTranslateX(60);
      hudInfo1.setTextFill(Color.WHITE);
      hudInfo2.setTranslateY(450);
      hudInfo2.setTranslateX(60);
      hudInfo2.setTextFill(Color.WHITE);
      planetSelectViewBase.getChildren().add(hudInfo1);
      singlePlanetViewBase.getChildren().add(hudInfo2);
      //return to planet sel button
      Button goBackButton = new Button("Go Back");
      singlePlanetViewBase.getChildren().add(goBackButton);
      //go to next screen button
      Button launchButton = new Button("Launch");
      launchButton.setLayoutX(400);
      launchButton.setLayoutY(375);

      //set up the from space planet 
      planetBig.setFitWidth(250);
      planetBig.setFitHeight(250);
      planetBig.relocate(50, 50);
      //sets up from space disc
      fromSpaceDesc.setTranslateY(200 / 4.0);
      fromSpaceDesc.setTranslateX(1400 / 4.0);
      fromSpaceDesc.setPrefWidth(1785 / 4.0);
      fromSpaceDesc.setWrapText(true);
      fromSpaceDesc.setTextFill(Color.WHITE);

      singlePlanetViewBase.getChildren().add(fromSpaceDesc);
      singlePlanetViewBase.getChildren().add(planetBig);
      singlePlanetViewBase.getChildren().add(launchButton);
      singlePlanetViewBase.setVisible(false);
      
      //changes the screen back to planet sel on button click
      goBackButton.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            singlePlanetViewBase.setVisible(false);
            planetSelectViewBase.setVisible(true);
         }
      });
      //calls the next screen on button click
      launchButton.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            singlePlanetViewBase.setVisible(false);
            planetSelectViewBase.setVisible(false);
            TaskVisuals v1 = new TaskVisuals();
            player1.setFuelLevel(player1.getFuelLevel() - selectPlanet.getDistance());
            v1.start(rootNode, player1, selectPlanet, planetFactory);
         }
      });
   }
   
   public Pane getViewBase()
   {
      return planetSelectViewBase;
   }
   
   /**
   * Clears the planet list, generates 3-5 random planets, stores them in the
   * planetList, adds their ImageViews to the planet select view, and
   * randomizes their positions, ensuring that they don't overlap with each
   * other or the player's rocket.
   */
   public void makeNewPlanetSet()
   {
      // Remove old planets from view base
      int planetListLength = planetList.size();
      for (int i = 0; i < planetListLength; i++)
      {
         planetSelectViewBase.getChildren().remove(planetList.get(i).getImageView());
      }

      // Delete old planets
      planetList.clear();

      // Make new planets and pass this view
      int planetCount = 3 + rand.nextInt(2); // 3 to 5 new planets
      for (int i = 0; i < planetCount; i++)
      {
         Planet planet = planetFactory.createRandomPlanet();
         planetList.add(planet);
         planet.setPlanetSelectView(this);
      }

      // Add ImageView components to view base
      planetListLength = planetList.size();
      for (int i = 0; i < planetListLength; i++)
      {
         planetSelectViewBase.getChildren().add(planetList.get(i).getImageView());
      }

      // Randomize positions of planet ImageViews
      double scale = rootNode.getPrefWidth() / 3840;
      double rightBound = rootNode.getPrefWidth();
      double bottomBound = rootNode.getPrefHeight() - (477 * scale); // Leave room for player HUD
      
      // To make sure planets are allowed to be placed in the top left corner
      for (Planet planet : planetList)
         planet.getImageView().relocate(0.0, bottomBound);
         
      for (int i = 0; i < planetListLength; i++)
      {
         ImageView planetIV = planetList.get(i).getImageView();
         
         boolean validPlacement = false;
         while (!validPlacement)
         {
            planetIV.relocate(rand.nextDouble() * (rightBound - planetIV.getFitWidth()),
               rand.nextDouble() * (bottomBound - planetIV.getFitHeight()));
            
            // Decide if placement was valid
            validPlacement = true;
            for (Planet otherPlanet : planetList)
            {
               ImageView otherPlanetIV = otherPlanet.getImageView();
               if (planetIV != otherPlanetIV && planetIV.intersects(planetIV.sceneToLocal(otherPlanetIV.localToScene(otherPlanetIV.getBoundsInLocal()))))
                  validPlacement = false;
            }
            if (planetIV.intersects(planetIV.sceneToLocal(rocketIV.localToScene(rocketIV.getBoundsInLocal()))))
               validPlacement = false;
         }
      }
   }
   
   /*
   *sets the BigPlanet image and fsd label to the data from the 
   *current clicked planet
   *@param planet
   */
   public void planetClicked(Planet planet)
   {
      singlePlanetViewBase.setVisible(true);
      planetSelectViewBase.setVisible(false);
      planetBig.setImage(planet.getImage());
      fromSpaceDesc.setText("Diameter: " + (int)(23657 * (1 + (6 * planet.getSize() / 100.0))) + " km\n" + planet.getDescriptionFromSpace());
      selectPlanet = planet;
      fromSpaceDesc.setVisible(true);
   }
}
