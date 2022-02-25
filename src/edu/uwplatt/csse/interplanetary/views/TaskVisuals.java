package edu.uwplatt.csse.interplanetary.views;

import edu.uwplatt.csse.interplanetary.*;
import edu.uwplatt.csse.interplanetary.planet.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.shape.Rectangle;

/**
*This class handles all the visuals for the task select screen
* the encounter screen and the game over  
 @author taryn/EJ
 */
public class TaskVisuals
{

   private boolean miningSelected;
   private boolean farmingSelected;
   private boolean combatSelected;
   private boolean collaborateSelected;
   private Pane taskSelectionViewBase;
   private Pane encounterOutcomeViewBase;
   private String bgImage1;
   
   /**
    *This is a start method that handles all the graphics
    *for both task sel and outcome
    *  @param Pane Player Planet Planet Factory
    */
   public void start(Pane rootNode, Player player, Planet planet, PlanetFactory planetFactory)
   {
      TaskSelection tasks = new TaskSelection(player, planet);
      taskSelectionViewBase = new Pane();
      encounterOutcomeViewBase = new Pane();
      rootNode.getChildren().add(taskSelectionViewBase);
      rootNode.getChildren().add(encounterOutcomeViewBase);
      taskSelectionViewBase.setPrefSize(rootNode.getPrefWidth(), rootNode.getPrefHeight());
      encounterOutcomeViewBase.setPrefSize(rootNode.getPrefWidth(), rootNode.getPrefHeight());
      getBackground(planet);
        //reads in a string as an image and sets it to a background
      BackgroundImage myBI = new BackgroundImage( new Image(bgImage1, rootNode.getWidth(),
        rootNode.getHeight(), false, true),    
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
      
      taskSelectionViewBase.setBackground(new Background(myBI));
      encounterOutcomeViewBase.setBackground(new Background(myBI));
      //sets up all static images
      encounterOutcomeViewBase.setVisible(false);
      Image hudBkg = new Image("edu/uwplatt/csse/interplanetary/views/player_hud_bkg.png");
      ImageView hudBkgIV = new ImageView(hudBkg);
      ImageView hudBkgIV2 = new ImageView(hudBkgIV.getImage());
      taskSelectionViewBase.getChildren().add(hudBkgIV);
      encounterOutcomeViewBase.getChildren().add(hudBkgIV2);
      hudBkgIV.relocate(210 / 4, 1683 / 4);
      hudBkgIV.setFitHeight(hudBkg.getHeight() / 4);
      hudBkgIV.setFitWidth(hudBkg.getWidth() / 4);
      hudBkgIV2.relocate(210 / 4, 1683 / 4);
      hudBkgIV2.setFitHeight(hudBkg.getHeight() / 4);
      hudBkgIV2.setFitWidth(hudBkg.getWidth() / 4);
      Label hudInfo1 = new Label("Fuel " + player.getFuelLevel() + "    " + "Supply " + player.getSupplyLevel() + "    " + "Mining " + player.getMiningStrength()
            + "    " + "Farming " + player.getFarmingStrength() + "    " + "Combat " + player.getCombatStrength());
      Label hudInfo2 = new Label();
      hudInfo1.setTranslateY(450);
      hudInfo1.setTranslateX(60);
      hudInfo1.setTextFill(Color.WHITE);
      hudInfo2.setTranslateY(450);
      hudInfo2.setTranslateX(60);
      hudInfo2.setTextFill(Color.WHITE);
      taskSelectionViewBase.getChildren().add(hudInfo1);
      encounterOutcomeViewBase.getChildren().add(hudInfo2);
      //from planet decription label
      Label fpsLable= new Label(planet.getDescriptionFromPlanet());
      fpsLable.setWrapText(true);
      fpsLable.relocate(50,15);
      fpsLable.setPrefSize(300, 400);
      fpsLable.setLineSpacing(8);
      fpsLable.setTextFill(Color.WHITE);
      Rectangle r1 = new Rectangle(40, 15, 315, 415);
      r1.setOpacity(0.5);
      
      taskSelectionViewBase.getChildren().add(r1);
      taskSelectionViewBase.getChildren().add(fpsLable);

      Label mineLabel = new Label();
      Label farmLabel = new Label();
      Label combatLabel = new Label();
      Label collabLabel = new Label();
      mineLabel.setTextFill(Color.WHITE);
      farmLabel.setTextFill(Color.WHITE);
      combatLabel.setTextFill(Color.WHITE);
      collabLabel.setTextFill(Color.WHITE);
 
      Rectangle r4 = new Rectangle(240, 35, 475, 235);
      r4.setOpacity(0.5);
      encounterOutcomeViewBase.getChildren().add(r4);
       

      encounterOutcomeViewBase.getChildren().add(mineLabel);
      encounterOutcomeViewBase.getChildren().add(farmLabel);
      encounterOutcomeViewBase.getChildren().add(combatLabel);
      encounterOutcomeViewBase.getChildren().add(collabLabel);
      mineLabel.relocate(500, 50);
      fpsLable.setTextFill(Color.WHITE);       
      farmLabel.relocate(500, 150);
      combatLabel.relocate(500, 200);
      collabLabel.relocate(250, 50);
      //sets up actoin handles for all tasks on button click
      Button miningButton = new Button("Mine");
      miningButton.setOnAction(e -> 
            {
               if (!miningSelected)
               {
                  miningSelected = true;
               }
               else
               {
                  miningSelected = false;
               }
      });
      Button farmingButton = new Button("Farm");
      farmingButton.setOnAction(e -> 
            {
               if (!farmingSelected)
               {
                  farmingSelected = true;
               }
               else
               {
                  farmingSelected = false;
               }
      });
      Button combatButton = new Button("Combat");
      combatButton.setOnAction(e -> 
            {
               if (!combatSelected)
               {
                  combatSelected = true;
               }
               else
               {
                  combatSelected = false;
               }
      });
      Button collabButton = new Button("Collaborate");
      collabButton.setOnAction(e -> 
            {
               if (!collaborateSelected)
               {
                  collaborateSelected = true;
               }
               else
               {
                  collaborateSelected = false;
               }
      });
      Button continueButton = new Button("Continue");
      continueButton.setOnAction(e -> 
            {
                
               mineLabel.setText("");
               farmLabel.setText("");
               combatLabel.setText("");
               collabLabel.setText("");
              
               taskSelectionViewBase.setVisible(false);
               encounterOutcomeViewBase.setVisible(true);
             
               if (miningSelected)
               {
                  mineLabel.setText(tasks.Mine());
               
               }
               if (farmingSelected)
               {
                  farmLabel.setText(tasks.Farm());
                
               }
               if (combatSelected)
               {
                  combatLabel.setText(tasks.Combat());
                 
               }
               if (collaborateSelected)
               {
                  collabLabel.setText(tasks.Collaborate());
                 
               }
               hudInfo2.setText("Fuel " + player.getFuelLevel() + "    " + "Supply " + player.getSupplyLevel() + "    " + "Mining " + player.getMiningStrength()
            + "    " + "Farming " + player.getFarmingStrength() + "    " + "Combat " + player.getCombatStrength());
      });
       //graphics for task buttons
      taskSelectionViewBase.getChildren().add(miningButton);
      taskSelectionViewBase.getChildren().add(farmingButton);
      taskSelectionViewBase.getChildren().add(combatButton);
      taskSelectionViewBase.getChildren().add(collabButton);
      taskSelectionViewBase.getChildren().add(continueButton);
      miningButton.relocate(775, 50);
      miningButton.setPrefSize(150, 150);   
      farmingButton.setPrefSize(150, 150);
      farmingButton.relocate(600, 50);
      combatButton.relocate(600, 225);
      combatButton.setPrefSize(150, 150);
      collabButton.setPrefSize(150, 150);
      collabButton.relocate(775, 225);
      continueButton.relocate(400, 350);
      
      Button continueButton2 = new Button("Continue");
      encounterOutcomeViewBase.getChildren().add(continueButton2);
      continueButton2.relocate(425, 300);
      //tranfsers player to planet selcet on button click or prints game 
      //over if a stat is at 0
      continueButton2.setOnAction(e -> 
            {
               if (player.getMiningStrength() <= 0 || player.getFarmingStrength() <= 0
                     || player.getCombatStrength() <= 0 || player.getSupplyLevel() <= 0
                     || player.getFuelLevel() <= 0)
               {
                  Label gameOver = new Label();
                  gameOver.relocate(175, 150);
                  gameOver.setTextFill(Color.RED);
                    gameOver.setFont(new Font(100));
                  encounterOutcomeViewBase.getChildren().add(gameOver);

                  gameOver.setText("GAME OVER!");
               }
               else
               {
                  encounterOutcomeViewBase.setVisible(false);
                  PlanetSelectView m1 = new PlanetSelectView(rootNode, planetFactory, player);

                  m1.makeNewPlanetSet();
               }
      });
   }
   /**
   * set a public string to a image location that is used for the background
   * uses the current planet image to decide background image
   * @param Planet
   */
   
   public void getBackground(Planet planet)
   {
       String i1="edu/uwplatt/csse/interplanetary/planet/planet000.png";
       String i2="edu/uwplatt/csse/interplanetary/planet/planet001.png";
       String i3= "edu/uwplatt/csse/interplanetary/planet/planet002.png";
       String i4="edu/uwplatt/csse/interplanetary/planet/planet003.png";
       String i5="edu/uwplatt/csse/interplanetary/planet/planet004.png";
       String i6="edu/uwplatt/csse/interplanetary/planet/planet005.png";
       String i7="edu/uwplatt/csse/interplanetary/planet/planet006.png";
       String i8="edu/uwplatt/csse/interplanetary/planet/planet007.png";
       String i9="edu/uwplatt/csse/interplanetary/planet/planet008.png";
       String i10="edu/uwplatt/csse/interplanetary/planet/planet009.png";
       if(planet.getImageFileName().equals(i1))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet000_bkg.jpeg";
           System.out.println(bgImage1);
              
       }
       else if(planet.getImageFileName().equals(i2))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet001_bkg.jpeg";
          
       
       }
       else if(planet.getImageFileName().equals(i3))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet002_bkg.jpeg";
  
       
       }
       else if(planet.getImageFileName().equals(i4))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet003_bkg.jpeg";
  
       
       }
       else if(planet.getImageFileName().equals(i5))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet004_bkg.jpeg";
       
       }
       else if(planet.getImageFileName().equals(i6))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet005_bkg.jpeg";
          
        }
       else if(planet.getImageFileName().equals(i7))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet006_bkg.jpeg";
        
       
       }
       else if(planet.getImageFileName().equals(i8))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet007_bkg.jpeg";
           
       
       }
       else if(planet.getImageFileName().equals(i9))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet008_bkg.jpeg";
           
       
       }
       else if(planet.getImageFileName().equals(i10))
       {
           bgImage1="edu/uwplatt/csse/interplanetary/views/planet009_bkg.jpeg";
           
       
       }
         
       
   } 

}
