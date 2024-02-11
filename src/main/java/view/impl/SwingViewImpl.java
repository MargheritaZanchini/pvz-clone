package view.impl;

import java.awt.Dimension;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.api.View;

public class SwingViewImpl implements View {
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 700;

    public static final String APPLICATION_TITLE = "Plants Vs Zombies";
    
    public static final String MENU_PANEL_CONSTRAINT = "MENU";
    public static final String GAME_PANEL_CONSTRAINT = "GAME";
    
    private static final boolean IS_APPLICATION_RESIZABLE = false;
    
    protected static final String MENU_BACKGROUND = "/images/menuBackground.jpeg";
    //private static final KeyCombination EXIT_FULLSCREEN_KEY_COMBINATION = KeyCombination.valueOf("ESC");
    //private static final String EXIT_FULLSCREEN_MESSAGE = "Press " + EXIT_FULLSCREEN_KEY_COMBINATION + " to exit Fullscreen Mode!";

    private JFrame frame;
    private JPanel panel;
    private CardLayout sceneManager = new CardLayout();

    public SwingViewImpl() {
        this.frame = new JFrame(APPLICATION_TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setMinimumSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        this.frame.setResizable(IS_APPLICATION_RESIZABLE);
        
        this.panel = new JPanel(sceneManager);
        this.panel.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        this.panel.add(new MenuPanel(APPLICATION_WIDTH, APPLICATION_HEIGHT), MENU_PANEL_CONSTRAINT);
        this.panel.add(new GamePanel(APPLICATION_WIDTH, APPLICATION_HEIGHT), GAME_PANEL_CONSTRAINT);
        
        this.frame.getContentPane().add(panel);

        this.frame.setVisible(true);
    }

    @Override
    public void setScene(String scene) {
        this.sceneManager.show(this.panel, scene);
    }

    @Override
    public JPanel getScene() {
        return this.panel;
    }

    @Override
    public String getSceneConstraint() {
        throw new UnsupportedOperationException("Unimplemented method 'getSceneContraint'");
    }

    @Override
    public void update(JPanel scene) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
