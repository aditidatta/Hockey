import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Mesa on 10/20/2016.
 */
public class Menu extends JPanel implements Runnable{

    Controller controller;
    public ArrayList<Controller> foundControllers = new ArrayList<>();

    Color color;

    int height;
    int width;
    int horizontalMiddle;
    int verticalCenter;
    int circleDiameter;

    int xAxisPercentage = 0;
    int yAxisPercentage = 0;
    String buttonIndex = "";
    int buttonInputLimitFrames = 0;
    int stickInputLimitFrames = 0;
    ImageIcon okButtonIcon = new ImageIcon("img/letterA.png");
    JButton okButton = new JButton("OK", okButtonIcon);

    Image colorNum;

    Image rednum1 = Toolkit.getDefaultToolkit().getImage("img/redone.png");
    Image bluenum2 = Toolkit.getDefaultToolkit().getImage("img/bluetwo.png");

    int centerX;
    int centerY1;
    int centerY2;
    int centerY3;
    int centerY4;

    int leftX;
    int leftYRed;
    int leftYBlue;

    int rightX;
    int rightYYellow;
    int rightYGreen;

    Player[] activePlayers;
    Player[] playerPositions;
    Point[] positions;
    Point[] centerPositions;

    int lineX1;
    int lineX2;
    int lineY;

    //Controller[] controllerArray = new Controller[4];
    Thread t;

    boolean paintRedButton = false;

    public Menu(int width, int height, Player[] players){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        horizontalMiddle = height/2;
        verticalCenter = width/2;
        circleDiameter = height/10;

        centerX = verticalCenter;
        centerY1 = (height / 6);
        centerY2 = ((height / 6) * 2);
        centerY3 = ((height / 6) * 3);
        centerY4 = ((height / 6) * 4);

        //lineX1 = 5*(width/1000);
        //lineX2 = 995*(width/1000);
        lineX1 = 0;
        lineX2 = width;
        //lineY = centerY4 + ((circleDiameter)*(height/550));
        lineY = centerY4 + (height / 6);

        leftX = width/4;
        leftYRed = height/4;
        leftYBlue = height/4*2;

        rightX = width/4*3;
        rightYYellow = height/4;
        rightYGreen = height/4*2;

        /*
        controller1X = centerX;
        controller1Y = centerY1;
        controller2X = centerX;
        controller2Y = centerY2;
        controller3X = centerX;
        controller3Y = centerY3;
        controller4X = centerX;
        controller4Y = centerY4;
        */
        this.activePlayers = players;
        this.playerPositions = new Player[4];
        this.positions = new Point[]{new Point(leftX, leftYRed),
                new Point(leftX, leftYBlue),
                new Point(rightX, rightYYellow),
                new Point(rightX, rightYGreen)};

        this.centerPositions = new Point[]{new Point(centerX, centerY1),
                                        new Point(centerX, centerY2),
                                        new Point(centerX, centerY3),
                                        new Point(centerX, centerY4)};

        for(int i = 0; i < 4; i++){
            activePlayers[i].setLocation(centerPositions[i]);
        }
        okButton.setLocation(new Point(1200,600));
        add(okButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.GRAY);

        g2d.fillOval(leftX - circleDiameter/2, leftYRed - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(leftX - circleDiameter/2, leftYBlue - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(rightX - circleDiameter/2, rightYGreen - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(rightX - circleDiameter/2, rightYYellow - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(centerX - circleDiameter/2, centerY1 - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(centerX - circleDiameter/2, centerY2 - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(centerX - circleDiameter/2, centerY3 - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(centerX - circleDiameter/2, centerY4 - circleDiameter/2, circleDiameter, circleDiameter);


        //g2d.drawImage(rednum1, xLocationRed, yLocationRed, circleDiameter, circleDiameter, this);

        for(Player player : activePlayers){
            player.drawImage(g2d);
        }

        g2d.drawLine(lineX1, lineY, lineX2, lineY);

        /*
        g2d.setColor(Color.BLACK);
        g2d.fillOval(verticalCenter - circleDiameter/2, height/5 - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(verticalCenter - circleDiameter/2, height/5 * 2 - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(verticalCenter - circleDiameter/2, height/5 * 3 - circleDiameter/2, circleDiameter, circleDiameter);
        g2d.fillOval(verticalCenter - circleDiameter/2, height/5 * 4 - circleDiameter/2, circleDiameter, circleDiameter);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospace", Font.BOLD, 22));
        g2d.drawString("1", verticalCenter- 7, height/5+ 7);
        g2d.drawString("2", verticalCenter, height/5 * 2);
        g2d.drawString("3", verticalCenter, height/5 * 3);
        g2d.drawString("4", verticalCenter, height/5 * 4);
        */


    }

    public void assignPlayers(){
        int xNeutral = 49;
        int yNeutral = 49;
        LinkedList<Integer> unused = new LinkedList<>();

        for( int i = 0; i < activePlayers.length; i++){
            if(activePlayers[i].controller == null) {
                unused.add(i);
                continue;
            }

            activePlayers[i].gamepad();
            //everything will happen here
            if(activePlayers[i].xAxisPercentage < xNeutral - 40){
                if(activePlayers[i].location.x == centerX) {
                    if (playerPositions[0] == null) {
                        playerPositions[0] = activePlayers[i];
                        playerPositions[0].setLocation(positions[0]);
                    } else if (playerPositions[1] == null) {
                        playerPositions[1] = activePlayers[i];
                        playerPositions[1].setLocation(positions[1]);
                    }
                }
                else if(activePlayers[i].location.x == leftX){
                    System.out.println("already left");
                }
                else{
                    System.out.println("from right to center");
                    activePlayers[i].setLocation(centerPositions[i]);
                    if(playerPositions[2] == activePlayers[i])
                        playerPositions[2] = null;
                    else if(playerPositions[3] == activePlayers[i])
                        playerPositions[3] = null;
                }
            }
            else if(activePlayers[i].xAxisPercentage > xNeutral + 40){
                if(activePlayers[i].location.x == centerX) {
                    if (playerPositions[2] == null) {
                        playerPositions[2] = activePlayers[i];
                        playerPositions[2].setLocation(positions[2]);
                    } else if (playerPositions[3] == null) {
                        playerPositions[3] = activePlayers[i];
                        playerPositions[3].setLocation(positions[3]);
                    }
                }
                else if(activePlayers[i].location.x == rightX){
                    System.out.println("already right");
                }
                else{
                    System.out.println("from left to center");
                    activePlayers[i].setLocation(centerPositions[i]);
                    if(playerPositions[0] == activePlayers[i])
                        playerPositions[0] = null;
                    else if(playerPositions[1] == activePlayers[i])
                        playerPositions[1] = null;
                }
            }

            if(activePlayers[i].yAxisPercentage > yNeutral + 40) { //down
                if(activePlayers[i].location.x == leftX
                        && activePlayers[i].location.y == leftYRed
                        && playerPositions[1] == null){
                    playerPositions[1] = activePlayers[i];
                    playerPositions[1].setLocation(positions[1]);
                    playerPositions[0] = null;
                }
                else if(activePlayers[i].location.x == rightX
                        && activePlayers[i].location.y == rightYYellow
                        && playerPositions[3] == null){
                    playerPositions[3] = activePlayers[i];
                    playerPositions[3].setLocation(positions[3]);
                    playerPositions[2] = null;
                }
            }
            else if(activePlayers[i].yAxisPercentage < yNeutral - 40) { //up
                if(activePlayers[i].location.x == leftX
                        && activePlayers[i].location.y == leftYBlue
                        && playerPositions[0] == null){
                    playerPositions[0] = activePlayers[i];
                    playerPositions[0].setLocation(positions[0]);
                    playerPositions[1] = null;
                }
                else if(activePlayers[i].location.x == rightX
                        && activePlayers[i].location.y == rightYGreen
                        && playerPositions[2] == null){
                    playerPositions[2] = activePlayers[i];
                    playerPositions[2].setLocation(positions[2]);
                    playerPositions[3] = null;
                }
            }



            //System.out.println(xAxisPercentage);
            /*
            if(stickInputLimitFrames >10) {

                if (xAxisPercentage < xNeutral - 40) {
                    //move left

                    if (controllerArray[2] == controller) {
                        controllerArray[2] = null;
                        System.out.println("center 3");
                    }
                    else if (controllerArray[3] == controller) {
                        controllerArray[3] = null;
                        System.out.println("center 4");
                    }
                    else {

                        if (controllerArray[0] == null) {
                            controllerArray[0] = controller;
                            System.out.println("left 1");

                            if(controller.getPortNumber() == 0){
                                paintRedButton = true;
                            }
                            if(controller.getPortNumber() == 1){
                                paintRedButton = true;
                            }


                        } else if (controllerArray[0] == controller) {
                            System.out.println("already in left top spot");
                            paintRedButton = true;
                        }
                        else if (controllerArray[1] == null) {
                            controllerArray[1] = controller;
                            System.out.println("left 2");
                        } else if (controllerArray[1] == controller) {
                            System.out.println("already in left bottom spot");
                        }
                    }
                }
                else if (xAxisPercentage > xNeutral + 40) {
                    if (controllerArray[0] == controller) {
                        controllerArray[0] = null;
                        System.out.println("center 1");
                        paintRedButton = false;
                    } else if (controllerArray[1] == controller) {
                        controllerArray[1] = null;
                        System.out.println("center 2");
                    }
                    else {
                        if (controllerArray[2] == null) {
                            controllerArray[2] = controller;
                            System.out.println("right 1");
                        } else if (controllerArray[2] == controller) {
                            System.out.println("already in right top spot");
                        }

                        else if (controllerArray[3] == null) {
                            controllerArray[3] = controller;
                            System.out.println("right 2");
                        } else if (controllerArray[3] == controller) {
                            System.out.println("already in right bottom spot");
                        }
                    }
                }
                stickInputLimitFrames = 0;
            }*/

        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        System.out.println("run");

        while(true) {

            try {
                Thread.sleep(130);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            assignPlayers();
            repaint();
        }
    }
}
