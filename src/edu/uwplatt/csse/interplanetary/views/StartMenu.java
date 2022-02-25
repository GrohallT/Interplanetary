package edu.uwplatt.csse.interplanetary.views;

import edu.uwplatt.csse.interplanetary.*;
import edu.uwplatt.csse.interplanetary.planet.*;

import javafx.application.Platform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
* StartMenu holds all components needed by the Start Menu view. The layout of
* the view is handled in the constructor. The user can perform multiple tasks
* on the Start Menu, including exiting the program, starting a new game,
* loading an in progress game, and viewing saved content from previously
* completed game session.
* @author Reese
*/
public class StartMenu 
{
   // Graphics
   private Pane rootPane;
   private Pane startMenuViewBase;
   
   // Other game components
   private GameManager gameManager;
   private PlanetFactory planetFactory;
   
   /**
   * Class constructor with partial parameterization. The constructor also
   * handles the layout of JavaFX components.
   * @param rootPane        the pane that this game session's views will be
   *                        placed on top of
   * @param planetFactory   PlanetFactory that this game session uses to
   *                        generate randomized planets
   */
   public StartMenu(Pane rootPane, PlanetFactory planetFactory)
   {
      // Parameterized
      this.rootPane = rootPane;
      this.planetFactory = planetFactory;
      
      // Constructed
      startMenuViewBase = new Pane();
      
      // Start Menu Layout
      rootPane.getChildren().add(startMenuViewBase);
      startMenuViewBase.setPrefSize(960.0, 540.0);
      
      BackgroundImage myBI = new BackgroundImage(new Image("edu/uwplatt/csse/interplanetary/views/start_menu_bkg.jpg",
            startMenuViewBase.getPrefWidth(),
            startMenuViewBase.getPrefHeight(), false, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
      startMenuViewBase.setBackground(new Background(myBI));
      
      // New Game Button
      Image newGameButton = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_new_game.png");
      Image newGameButtonHover = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_new_game_hover.png");
      ImageView newGameButtonIV = new ImageView(newGameButton);
      startMenuViewBase.getChildren().add(newGameButtonIV);
      newGameButtonIV.relocate(1415 / 4, 1211 / 4);
      newGameButtonIV.setFitHeight(newGameButton.getHeight() / 4);
      newGameButtonIV.setFitWidth(newGameButton.getWidth() / 4);
      newGameButtonIV.setOnMouseEntered(e -> {
         newGameButtonIV.setImage(newGameButtonHover);
         e.consume();
      });
      newGameButtonIV.setOnMouseExited(e -> {
         newGameButtonIV.setImage(newGameButton);
         e.consume();
      });
      newGameButtonIV.setOnMouseClicked(e -> {
         gameManager = new GameManager(rootPane, planetFactory);
         e.consume();
      });
      
      // Load Game Button
      Image loadGameButton = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_load_game.png");
      Image loadGameButtonHover = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_load_game_hover.png");
      ImageView loadGameButtonIV = new ImageView(loadGameButton);
      startMenuViewBase.getChildren().add(loadGameButtonIV);
      loadGameButtonIV.relocate(1415 / 4, 1396 / 4);
      loadGameButtonIV.setFitHeight(loadGameButton.getHeight() / 4);
      loadGameButtonIV.setFitWidth(loadGameButton.getWidth() / 4);
      loadGameButtonIV.setOnMouseEntered(e -> {
         loadGameButtonIV.setImage(loadGameButtonHover);
         e.consume();
      });
      loadGameButtonIV.setOnMouseExited(e -> {
         loadGameButtonIV.setImage(loadGameButton);
         e.consume();
      });
      
      // Previous Games Button
      Image previousGamesButton = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_previous_games.png");
      Image previousGamesButtonHover = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_previous_games_hover.png");
      ImageView previousGamesButtonIV = new ImageView(previousGamesButton);
      startMenuViewBase.getChildren().add(previousGamesButtonIV);
      previousGamesButtonIV.relocate(1415 / 4, 1581 / 4);
      previousGamesButtonIV.setFitHeight(previousGamesButton.getHeight() / 4);
      previousGamesButtonIV.setFitWidth(previousGamesButton.getWidth() / 4);
      previousGamesButtonIV.setOnMouseEntered(e -> {
         previousGamesButtonIV.setImage(previousGamesButtonHover);
         e.consume();
      });
      previousGamesButtonIV.setOnMouseExited(e -> {
         previousGamesButtonIV.setImage(previousGamesButton);
         e.consume();
      });
      
      // Interplanetary Logo
      Image logo = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_logo.png");
      ImageView logoIV = new ImageView(logo);
      startMenuViewBase.getChildren().add(logoIV);
      logoIV.relocate(475 / 4, 226 / 4);
      logoIV.setFitHeight(logo.getHeight() / 4);
      logoIV.setFitWidth(logo.getWidth() / 4);
      
      // Exit Button
      Image exit = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_exit.png");
      ImageView exitIV = new ImageView(exit);
      startMenuViewBase.getChildren().add(exitIV);
      exitIV.relocate(3650 / 4, 52 / 4);
      exitIV.setFitHeight(exit.getHeight() / 4);
      exitIV.setFitWidth(exit.getWidth() / 4);
      exitIV.setOnMouseClicked(e -> {
         e.consume();
         Platform.exit();
      });
      
      // Sliding cursor
      Image cursor = new Image("edu/uwplatt/csse/interplanetary/views/start_menu_sliding_cursor.png");
      ImageView cursorIV = new ImageView(cursor);
      startMenuViewBase.getChildren().add(cursorIV);
      cursorIV.relocate(1494 / 4, 0 / 4);
      cursorIV.setFitHeight(cursor.getHeight() / 4);
      cursorIV.setFitWidth(cursor.getWidth() / 4);
      startMenuViewBase.setOnMouseMoved(e -> {
         cursorIV.relocate(1494 / 4, e.getY() - 12);
         e.consume();
      });
      
      logoIV.toFront();
   }
}
