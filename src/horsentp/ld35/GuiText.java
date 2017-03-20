/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class GuiText extends Gui {

    private BufferedImage buffer;
    private String text = "";
    private boolean centered;
    private int padding = 5;
    
    public GuiText(float x, float y, BufferedImage background, GuiCoordinateSystem system) {
        super(x, y, background, system);
        buffer = new BufferedImage(background.getWidth(), background.getHeight(), background.getType());
        drawToBuffer();
    }

    @Override
    public void renderWorldCoordinates(Game game, Renderer renderer, float camX, float camY) {
        renderer.drawImage(buffer, (int)(x-camX), (int)(y-camY), Game.WORLD_GUI_DEPTH);
    }

    @Override
    public void renderScreenCoordinates(Game game, Renderer renderer, float camX, float camY) {
        renderer.drawImage(buffer, (int)x, (int)y, Game.SCREEN_GUI_DEPTH);
    }

    protected void drawToBuffer() {
        Graphics g = buffer.getGraphics();
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        ArrayList<String> lines = new ArrayList<>();
        int textAreaWidth = background.getWidth()-(padding*2);
        String curLine = "";
        FontMetrics fm = g.getFontMetrics();
        String curWord = "";
        char ch;
        
        char[] textArr = text.toCharArray();
        for (int i=0; i<textArr.length; i++) {
            ch = textArr[i];
            if (ch == ' ') {
                if (!curWord.equals("")) {
                    if (fm.stringWidth(curLine + curWord) > textAreaWidth ) {
                        lines.add(curLine);
                        curLine = curWord;
                        curLine += ' ';
                    } else {
                        curLine += curWord;
                        curLine += ' ';
                    }
                    curWord = "";
                }
            } else if (ch == '\n') {
                curLine += curWord;
                lines.add(curLine);
                curLine = "";
                curWord = "";
            } else {
                curWord += ch;
            }
        }
        curLine += curWord;
        lines.add(curLine);
        g.drawImage(background, 0, 0, null);
        int xPos;
        if (centered) {
            xPos = background.getWidth()/2 - g.getFontMetrics().stringWidth(text)/2;
        } else {
            xPos = padding;
        }
        g.setColor(Color.BLACK);
        for (int i=0; i<lines.size(); i++) {
            g.drawString(lines.get(i), xPos, padding + ((i+1)*fm.getHeight()));
        }
        g.dispose();
    }
    
    @Override
    public void onClick(Game game, float mouseX, float mouseY, float camX, float camY) {
        
    }

    @Override
    public void update(Game game, int millis, float mouseX, float mouseY) {
        
    }

    public void setText(String text) {
        this.text = text;
        drawToBuffer();
    }

    public String getText() {
        return text;
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
        drawToBuffer();
    }

    public boolean isCentered() {
        return centered;
    }
    
    
}
