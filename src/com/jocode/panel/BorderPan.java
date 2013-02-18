/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocode.panel;


/**
 *
 * @author Jo
 */
public class BorderPan  extends IPan{

    public BorderPan(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public BorderPan() {
    }
    
    public final TC TOP = new TC(){{
        gridx = 0;
        gridy  = 0;
        gridwidth = 3;
        gridheight = 1;
        weightx = 1;
        weighty = 0;
        fill = BOTH;
    }};
    
    public final TC LEFT = new TC(){{
        gridx = 0;
        gridy  = 1;
        gridwidth = 1;
        gridheight = 1;
        weightx = 0;
        weighty = 1;
        fill = BOTH;
    }};
    
    public final TC MIDDLE = new TC(){{
        gridx = 1;
        gridy  = 1;
        gridwidth = 1;
        gridheight = 1;
        weightx = 1;
        weighty = 1;
        fill = BOTH;
    }};
    
    public final TC RIGHT = new TC(){{
        gridx = 2;
        gridy  = 1;
        gridwidth = 1;
        gridheight = 1;
        weightx = 0;
        weighty = 1;
        fill = BOTH;
    }};
    
    public final TC BOTTOM = new TC(){{
        gridx = 0;
        gridy  = 2;
        gridwidth = 3;
        gridheight = 1;
        weightx = 1;
        weighty = 0;
        fill = BOTH;
    }};
}