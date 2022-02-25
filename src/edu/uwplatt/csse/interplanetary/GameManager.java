package edu.uwplatt.csse.interplanetary;

import edu.uwplatt.csse.interplanetary.planet.*;
import edu.uwplatt.csse.interplanetary.views.*;

import javafx.scene.layout.Pane;

/**
* This class holds all components of a game session. It is created when a
* player starts a new game, and it is deleted when the player exits the game.
* @author Reese
*/
public class GameManager 
{
   // Graphics
   private Pane rootPane;
   private PlanetSelectView planetSelectView;
   
   // Other game components
   private PlanetFactory planetFactory;
   private Player player;
   
   /**
   * Class constructor that parameterizes some instance variables and
   * constructs the rest.
   * @param rootPane        the pane that this game session's views will be
   *                        placed on top of
   * @param planetFactory   PlanetFactory that this game session uses to
   *                        generate randomized planets
   */
   public GameManager(Pane rootPane, PlanetFactory planetFactory)
   {
      // Parameterized
      this.rootPane = rootPane;
      this.planetFactory = planetFactory;
      
      // Constructed
      player = new Player(100, 50, 50, 50, 50);
      planetSelectView = new PlanetSelectView(rootPane, planetFactory, player);
      
      // Initialize game
      planetSelectView.makeNewPlanetSet();
   }
}
