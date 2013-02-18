/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocode.panel;

import java.awt.Component;
import java.awt.Insets;
import javax.swing.JLabel;

/**
 *
 * @author Jo
 */ 
public class FieldsPan extends IPan{
    
    
    /**
     * On créé d'abord un nouveau panneau englobant la listes des champs de formulaire.
     * Permet de définit une contrainte de base sur les éléments, à svoir de ne pas prendre
     * toute la hauteur de la page lorsque l'on agrandit le panneau.
     * C'est à partir de ce tableau que l'on définit les contraintes des champs
     */
    public final IPan wrapper_pan = new IPan(){{
        
        //contraintes des champs
        default_model = new LTC(
                
                //le label
                new TC(){{
                    gridx = 0;
                    gridy  = new Inc(2);
                    weightx = 0;
                    weighty = 0;
                    fill = HORIZONTAL;
                    anchor = FIRST_LINE_START;
                    insets = new Insets(2, 2, 2, 2);
                }},
            
                //le champ de formulaire
                new TC(){{
                    gridx = 1;
                    gridy  = new Inc(2);
                    weightx = 1;
                    weighty = 0;
                    fill = HORIZONTAL;
                    anchor = FIRST_LINE_START;
                    insets = new Insets(2, 2, 2, 2);
                }},
            
                //la zone d'information
                new TC(){{
                    gridx = 0;
                    gridy  = new Inc(2, 1);
                    gridwidth = 2;
                    weightx = 0;
                    weighty = 0;
                    fill = HORIZONTAL;
                    anchor = FIRST_LINE_START;
                    insets = new Insets(2, 2, 2, 2);
                }}
            );
        
    }};
    
    //initialise le panneau en ajoutant à celui-ci le panneau englobant
    {
        append(
            new TC(){{
                weightx = 1;
                fill = HORIZONTAL;
                anchor = FIRST_LINE_START;
            }}, 
            wrapper_pan
        );
    }
    
    public FieldsPan(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public FieldsPan() {
    }
    
    /**
     * Pour renvoyer lors de l'ajout d'un champ, tout les éléments de celui-ci
     */
    public class Field {
        
        public JLabel label;
        public Component field;
        public ListPan meta_pan = new ListPan();
        public Component[] meta;

        public Field(JLabel label, Component field, Component... meta) {
            
            this.label = label;
            this.field = field;
            this.meta = meta;
            
            meta_pan.append(meta);
        }
    }
    
    /**
     * redéfinit append pour déléguer l'insertion d'un champ, au panneau englobant
     * Renvoie un objet récapitulatif des éléments.
     * @param label
     * @param field
     * @param meta
     * @return 
     */
    public Field append(JLabel label, Component field, Component... meta) {
        
        Field f = new Field(label, field, meta);
        wrapper_pan.append(label, field, f.meta_pan);
        
        return f;
    }
}
