package pvzclone.controller.api;

import java.util.Optional;
import java.util.Set;

import pvzclone.model.api.Entities;
import pvzclone.model.impl.Pair;

/**
 * This interface models a Controller for the game.
 * It extends the ViewEventListener interface.
 */
public interface Controller {

    /**
     * Initializes the game.
     * This method should be called once to start the game.
     */
    void initGame();

    /**
     * Calls the main loop of the game.
     * This method should be called after the game is initialized.
     * It is responsible for managing the game loop.
     */
    void callMainloop();

    /**
     * Retrieves the set of entities in the game.
     * Entities represent the elements present in the game world.
     *
     * @return a Set of Entities representing the elements in the game world.
     */
    Set<Entities> getEntities();

    /**
     * this method communicate with the model
     * telling him the position of the new plant.
     * 
     * @param pos is the position of the new plant.
     */
    void newPlant(Pair<Integer, Integer> pos);

    /**
     * Increases the points obtained by the suns in GameState.
     */
    void increaseSunPoints();

    /**
     * Retrieves the points from the GameState.
     * 
     * @return points obtained at the moment.
     */
    int getSunScore();


    /**
     * Sets the number of the Level to the number of the argument passed.
     * 
     * @param numberOfTheLevel
     */
    void chooseLevel(int numberOfTheLevel);

    /**
     * Retrieves the number of the chosen level if the level was chosen,
     * if not returns an empty Optional.
     * 
     * @return number of the level the we chose.
     */
    Optional<Integer> getChosenLevel();

    /**
     * Returns the number of levels available in the application.
     * 
     * @return number of levels available in the application.
     */
    int getLevelCount();
}
