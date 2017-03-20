/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Jonathon
 */
public class LD35 {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window window = null;
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode displayMode = device.getDisplayMode();
        int insetsLeft = 0;
        int insetsTop = 0;
        if (args.length >= 1) {
            if (args[0].equals("fullscreen")) {
                window = new Window(null);
                window.setIgnoreRepaint(true);
                device.setDisplayMode(new DisplayMode(WIDTH, HEIGHT, displayMode.getBitDepth(), displayMode.getRefreshRate()));
                device.setFullScreenWindow(window);
            } else if (args[0].equals("window")) {
                //Window
                window = new JFrame("Monster Village");
                JFrame frame = (JFrame) window;
                frame.setIgnoreRepaint(true);
                frame.setVisible(true);
                Insets ins = frame.getInsets();
                insetsLeft = ins.left;
                insetsTop = ins.top;
                int screenWidth = device.getDisplayMode().getWidth();
                int screenHeight = device.getDisplayMode().getHeight();
                int w = WIDTH + ins.left + ins.right;
                int h = HEIGHT + ins.top + ins.bottom;
                frame.setBounds(
                        (screenWidth / 2) - (w / 2),
                        (screenHeight / 2) - (h / 2),
                        w,
                        h
                );
                frame.setResizable(false);
                frame.setFocusable(true);
                ins = frame.getInsets();
                insetsLeft = ins.left;
                insetsTop = ins.top;
            } else {
                throw new IllegalArgumentException(args[0] + " is not a valid argument");
            }
        } else {
            //Window
                window = new JFrame("Monster Village");
                JFrame frame = (JFrame) window;
                frame.setIgnoreRepaint(true);
                frame.setVisible(true);
                Insets ins = frame.getInsets();
                insetsLeft = ins.left;
                insetsTop = ins.top;
                int screenWidth = device.getDisplayMode().getWidth();
                int screenHeight = device.getDisplayMode().getHeight();
                int w = WIDTH + ins.left + ins.right;
                int h = HEIGHT + ins.top + ins.bottom;
                frame.setBounds(
                        (screenWidth / 2) - (w / 2),
                        (screenHeight / 2) - (h / 2),
                        w,
                        h
                );
                frame.setResizable(false);
                frame.setFocusable(true);
                ins = frame.getInsets();
                insetsLeft = ins.left;
                insetsTop = ins.top;
        }
        window.setIconImage(ImageBank.getImage("images/icon.png"));

        window.createBufferStrategy(2);
        BufferStrategy bs = window.getBufferStrategy();

        final ArrayList<KeyEventWrapper> keyEvents = new ArrayList<>();
        final ArrayList<MouseEventWrapper> mouseEvents = new ArrayList<>();

        Running running = new Running();
        running.setRunning(true);

        window.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                running.setRunning(false);
            }

        });

        window.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                keyEvents.add(new KeyEventWrapper(e, true));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyEvents.add(new KeyEventWrapper(e, false));
            }

        });

        window.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mouseEvents.add(new MouseEventWrapper(e, true));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEvents.add(new MouseEventWrapper(e, false));
            }

        });

        Game game = new Game();

        //BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Renderer renderer = new Renderer();
        renderer.setRasterSize(WIDTH, HEIGHT);

        long b, d = 30;

        Graphics g;
        MouseEventWrapper me;
        KeyEventWrapper ke;
        while (running.isRunning()) {
            b = System.currentTimeMillis();

            renderer.start();
            //Update and render
            renderer.clearScreen(0, 255, 0);
            renderer.clearDepthBuffer(100000);
            game.update(30);
            game.render(renderer);
            renderer.stop();

            g = bs.getDrawGraphics();
            g.drawImage(renderer.getBackBuffer(), insetsLeft, insetsTop, null);
            g.dispose();
            bs.show();
            //Poll events

            while (!mouseEvents.isEmpty()) {
                me = mouseEvents.get(0);
                mouseEvents.remove(0);
                game.mouseInput(game, me.getEvent().getX() - insetsLeft, me.getEvent().getY() - insetsTop, me.getEvent().getButton(), me.isPressed());
            }

            while (!keyEvents.isEmpty()) {
                ke = keyEvents.get(0);
                keyEvents.remove(0);
                game.keyInput(ke.getEvent().getKeyCode(), ke.isPressed());
            }

            d = System.currentTimeMillis() - b;
            if (d < 30) {
                try {
                    Thread.sleep(30 - d);
                } catch (Exception e) {
                }
            }
        }
        if (bs != null) {
            bs.dispose();
        }
        window.dispose();
    }

}
