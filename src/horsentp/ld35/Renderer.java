/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import static horsentp.ld35.LD35.HEIGHT;
import static horsentp.ld35.LD35.WIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author Jonathon
 */
public class Renderer {

    private final int ALPHA_MASK = 0xFF000000;

    private int width;
    private int height;
    private BufferedImage backBuffer;
    private float[] depthBuffer;
    private int[] pixels;

    public void setRasterSize(int width, int height) {
        this.width = width;
        this.height = height;
        depthBuffer = new float[width * height];
        backBuffer = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        pixels = new int[width * height];
    }

    public void clearDepthBuffer(float clearVal) {
        if (depthBuffer != null) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    depthBuffer[i + (j * width)] = clearVal;
                }
            }
        }
    }

    public void clearScreen(int r, int g, int b) {
        int pixel = (0xFF << 24) | (r << 16) | (g << 8) | b;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[(j * width) + i] = pixel;
            }
        }
    }

    /**
     * Draw an image to the raster
     *
     * @param image the image to draw
     * @param x the x location of the top left pixel
     * @param y the y location of the top left pixel
     */
    public void drawImage(BufferedImage image, int x, int y, float depth) {
        int xPlusWidth = x + image.getWidth();
        int yPlusHeight = y + image.getHeight();
        if (x > width || y > height || xPlusWidth < 0 || yPlusHeight < 0) {
            return; //Not worth it to check every pixel; not in bounds
        }
        int xSpot;
        int ySpot;
        int index;
        int pixel;
        if (x > 0 && xPlusWidth < width && y > 0 && yPlusHeight < height) {
            //No need for bounds checking
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    xSpot = x + i;
                    ySpot = y + j;
                    index = (ySpot * width) + xSpot;
                    pixel = image.getRGB(i, j);
                    if ((pixel & ALPHA_MASK) != 0 && depthBuffer[index] >= depth) {
                        pixels[index] = pixel;
                        depthBuffer[index] = depth;
                    }
                }
            }
        } else {
            //Need to do bounds checking
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    xSpot = x + i;
                    ySpot = y + j;
                    index = (ySpot * width) + xSpot;
                    pixel = image.getRGB(i, j);                    
                    if ((pixel & ALPHA_MASK) != 0 && xSpot > 0 && xSpot < width && ySpot > 0 && ySpot < height && depthBuffer[index] >= depth) {
                        pixels[index] = pixel;
                        depthBuffer[index] = depth;
                    } else {
                    }
                }
            }
        }
    }

    public BufferedImage getBackBuffer() {
        return backBuffer;
    }

    public void start() {
        //backBuffer.getRGB(0, 0, width, height, pixels, 0, width);
    }

    public void stop() {
        backBuffer.setRGB(0, 0, width, height, pixels, 0, width);
    }
}
