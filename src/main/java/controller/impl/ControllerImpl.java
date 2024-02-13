package controller.impl;

import java.util.HashSet;
import java.util.Set;

import controller.api.Controller;
import model.api.Entities;
import model.api.Game;
import model.api.World;
import model.impl.GameImpl;
import model.impl.LevelImpl;
import model.impl.Pair;
import model.impl.WorldImpl;
import view.api.View;
import view.impl.SwingViewImpl;
/**
 * Class that implements Controller.
 */
public final class ControllerImpl implements Controller {

    private static final long PERIOD = 100;

    private World world;
    private View view;
    private Game game;

    @Override
    public void initGame() {
        this.world = new WorldImpl();
        this.view = new SwingViewImpl(this);
    }

    @Override
    public void callMainloop() {
        new Thread(this::mainLoop).start();
    }

    private void mainLoop() {
        // this.view.setScene(SwingViewImpl.GAME_PANEL_CONSTRAINT);
        this.world.setLevel(new LevelImpl(world));
        this.game = new GameImpl(this.world);
        this.world.setGame(game);
        long startTime = System.currentTimeMillis();
        // System.out.println("tempo di inizio gioco: " + startTime);
        while (!this.game.isOver()) {
            long currentStartTime = System.currentTimeMillis();
            // System.out.println("tempo di inizio ciclo: " + currentStartTime);
            long elapsed = currentStartTime - startTime;
            // System.out.println("tempo delta: " + elapsed);
            this.game.update(elapsed);
            this.view.update();
            waitForNextFrame(currentStartTime);
            startTime = currentStartTime - startTime;
        }
    }

    private void waitForNextFrame(final long currentStartTime) {
        long dt = System.currentTimeMillis() - currentStartTime;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
    }

    @Override
    public void notifyMouseEvent(final Pair<Integer, Integer> clickPos) {
        this.game.mouseEvent(clickPos);
    }

    @Override
    public Set<Entities> getEntities() {
        if (this.game == null) {
            return new HashSet<Entities>();
        }

        return this.game.getEntities();
    }

    /*
     * @Override
     * public void notifyWorldEvent(WorldEvent ev) {
     * //qua ho un dubbio, se è gia il model che gestisce le collisioni interne
     * //già lui controlla che ci siano state cose
     * //il controller lo comunica alla view?
     * }
     */
}
