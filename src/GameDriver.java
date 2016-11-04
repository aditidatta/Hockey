import net.java.games.input.*;
import net.java.games.input.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main Controller class for the game. It calls update() and step()
 * @author Aditi Datta
 */


//test1
public class GameDriver {
    UI       ui;
    Rink     rink;
    Player   p1;
    Player   p2;
    Player   p3;
    Player   p4;
    Goalie1  g1;
    Goalie2  g2;
    Puck     puck;

    Controller controller = null;
    Image num1 = Toolkit.getDefaultToolkit().getImage("img/one.png");
    Image num2 = Toolkit.getDefaultToolkit().getImage("img/two.png");
    Image num3 = Toolkit.getDefaultToolkit().getImage("img/three.png");
    Image num4 = Toolkit.getDefaultToolkit().getImage("img/four.png");


    MouseEvent e;

    public ArrayList<Controller> foundControllers = new ArrayList<>();



   // Component.Identifier componentIdentifier = component.getIdentifier();

    private void searchForControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        System.out.println(controllers.length);
        for(int i = 0; i < controllers.length; i++){
            controller = controllers[i];

            if (controller.getType() == net.java.games.input.Controller.Type.GAMEPAD ) {
                // Add new controller to the list of all controllers.
                foundControllers.add(controller);

                // Add new controller to the list on the window.
                //window.addControllerName(controller.getName() + " - " + controller.getType().toString() + " type");
            }

        }
        //System.out.println(foundControllers);
    }



    public GameDriver(){
        ui      = new UI("Hockey");

        int width = 1366;
        int height = 768;
        int horizontalMiddle = height/2;
        int verticalCenter = width/2;
        int circleRadius = height/20;
        int centerY1 = height/5;
        int centerY2 = height/5 * 2;
        int centerY3 = height/5 * 3;
        int centerY4 = height/5 * 4;


        // Moving objects
        puck = new Puck(0,new Point(500, 275), 0, 0, 8, Color.BLACK);


        // CREATING PLAYERS AND GOALIES mmm

        p1   = new Player(1,new Point(verticalCenter, centerY1), 3, 3*Math.PI - 0.523599, circleRadius, num1, puck);
        p2   = new Player(2,new Point(verticalCenter, centerY2), 0, 3*Math.PI - 0.523599, circleRadius, num2, puck);
        p3   = new Player(3,new Point(verticalCenter, centerY3), 3, 4*Math.PI - 0.523599, circleRadius, num3, puck);
        p4   = new Player(4,new Point(verticalCenter, centerY4), 3, 4*Math.PI - 0.523599, circleRadius, num4, puck);


        Rink.selectedPlayer = p1;
        Rink.selectedPlayer2 = p2;
        Rink.selectedPlayer3 = p3;
        Rink.selectedPlayer4 = p4;


        searchForControllers();

        Player[] players = new Player[]{p1, p2, p3, p4};

        for(int i = 0; i < foundControllers.size(); i++){
            players[i].controller = foundControllers.get(i);
        }

        JFrame menuFrame = new JFrame();
        //JPanel mainPanel = new JPanel();
        Menu menu = new Menu(width, height, players);
        //JPanel buttonPanel
        menuFrame.add(menu);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuFrame.pack();
        menuFrame.setVisible(true);
        /*
        g1   = new Goalie1(5,new Point(190+20, 275), 3, 4*Math.PI - 0.523599, 10, Color.LIGHT_GRAY, puck);
        g2   = new Goalie2(6,new Point(810-20, 275), 3, Math.PI, 10, Color.LIGHT_GRAY, puck);
        rink.addKeys();
        //s1.setPlayer(p1);
        // GIVING PUCK REFERENCE TO GOALIES
        //g1.setPuck(puck);
        //g2.setPuck(puck);

        // ADDING OBJECTS TO THE RINK
        rink.add(p1);
        rink.add(p2);
        rink.add(p3);
        rink.add(p4);

        rink.add(g1);
        rink.add(g2);
        rink.add(puck);
        // added moving objects

        ui.add(rink);
        ui.pack();
        ui.setVisible(true);


        rink.addMouseMotionListener(rink);
        */
       // rink.addKeyListener(rink);
    }

    private class KBListener implements KeyListener {

        /**
         * Invoked when a key has been typed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key typed event.
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if(c == 's' ){
                if( Rink.selectedPlayer == p1)
                    Rink.selectedPlayer = p2;
                else
                    Rink.selectedPlayer = p1;
            }
        }

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            if(c == 's' ){
                if( Rink.selectedPlayer == p1)
                    Rink.selectedPlayer = p2;
                else
                    Rink.selectedPlayer = p1;
            }
        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            char c = e.getKeyChar();
            if(c == 's' ){
                if( Rink.selectedPlayer == p1)
                    Rink.selectedPlayer = p2;
                else
                    Rink.selectedPlayer = p1;
            }
        }
    }


}
