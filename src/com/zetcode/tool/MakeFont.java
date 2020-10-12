package com.zetcode.tool;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MakeFont {
    private float size;
    public Font font;

    public MakeFont(float size){
        this.size = size;
        try {
            //create the font to use. Specify the size!
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/powerpixel.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getFont(){
        return font;
    }

}
