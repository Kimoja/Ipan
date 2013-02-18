/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocode.panel;

import java.awt.Component;

/**
 *
 * @author Jo
 */
public class ListPan  extends IPan{
    
    public ListPan(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public ListPan() {
    }
    
    //initialise le modèle par défaut
    {
        default_model = new TC(){{
                gridy  = new Inc(1);
                weightx = 1;
                fill = HORIZONTAL;
                anchor = FIRST_LINE_START;
            }};
    }
}
