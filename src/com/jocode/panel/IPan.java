/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocode.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 * 
 * @author Jo
 */
public class IPan extends JPanel{
    
    //réplication des constantes de GridBagConstraints pour pouvoir être 
    //utilisé sans passer par la reférence de la classe GridBagConstraints
    //lors d'initialisation par accolade
    public static final int RELATIVE = -1;
    public static final int REMAINDER = 0;
    public static final int NONE = 0;
    public static final int BOTH = 1;
    public static final int HORIZONTAL = 2;
    public static final int VERTICAL = 3;
    public static final int CENTER = 10;
    public static final int NORTH = 11;
    public static final int NORTHEAST = 12;
    public static final int EAST = 13;
    public static final int SOUTHEAST = 14;
    public static final int SOUTH = 15;
    public static final int SOUTHWEST = 16;
    public static final int WEST = 17;
    public static final int NORTHWEST = 18;
    public static final int PAGE_START = 19;
    public static final int PAGE_END = 20;
    public static final int LINE_START = 21;
    public static final int LINE_END = 22;
    public static final int FIRST_LINE_START = 23;
    public static final int FIRST_LINE_END = 24;
    public static final int LAST_LINE_START = 25;
    public static final int LAST_LINE_END = 26;
    public static final int BASELINE = 0x100;
    public static final int BASELINE_LEADING = 0x200;
    public static final int BASELINE_TRAILING = 0x300;
    public static final int ABOVE_BASELINE = 0x400;
    public static final int ABOVE_BASELINE_LEADING = 0x500;
    public static final int ABOVE_BASELINE_TRAILING = 0x600;
    public static final int BELOW_BASELINE = 0x700;
    public static final int BELOW_BASELINE_LEADING = 0x800;
    public static final int BELOW_BASELINE_TRAILING = 0x900;
    
    //modèle ou liste de modèle de contrainte par défault
    //voir la méthode append(Component[]...)
    public Object default_model;
    
    /**
     * la classe de modèle de contrainte
     */
    static public class TC{
        
        //valeur par défaut des contraintes
        public Object gridx = 0;
        public Object gridy = 0;
        public Object gridwidth = 1;
        public Object gridheight = 1;
        public Object weightx = 0;
        public Object weighty = 0;
        public Insets insets = null;
        public int fill = 0;
        public int anchor = 0x400;
        public Object ipadx = 0;
        public Object ipady = 0;
        
        //Le GridBagConstraints de base
        protected GridBagConstraints gbc = new GridBagConstraints();
    
        /**
         * Applique les valeurs des contraintes de la classe à l'objet délégué GridBagConstraints
         * Si cette valeur est du type TemplateMethod, 
         * alors on applique la méthode getIndex de l'objet.
         * 
         * @return GridBagConstraints
         */
        protected GridBagConstraints getConstraints(){
            
            gbc.gridx =  gridx instanceof TemplateMethod ? ((TemplateMethod)  gridx).getIndex() 
                    : (Integer) gridx;
            gbc.gridy =  gridy instanceof TemplateMethod ? ((TemplateMethod)  gridy).getIndex() 
                    : (Integer) gridy;
            gbc.gridwidth =  gridwidth instanceof TemplateMethod ? ((TemplateMethod)  gridwidth).getIndex() 
                    : (Integer) gridwidth;
            gbc.gridheight =  gridheight instanceof TemplateMethod ? ((TemplateMethod)  gridheight).getIndex() 
                    : (Integer) gridheight;
            gbc.weightx =  weightx instanceof TemplateMethod ? ((TemplateMethod)  weightx).getIndex() 
                    : (Integer) weightx;
            gbc.weighty =  weighty instanceof TemplateMethod ? ((TemplateMethod)  weighty).getIndex()  
                    : (Integer) weightx;
            gbc.ipadx =  ipadx instanceof TemplateMethod ? ((TemplateMethod)  ipadx).getIndex() 
                    : (Integer) ipadx;
            gbc.ipadx =  ipadx instanceof TemplateMethod ? ((TemplateMethod)  ipadx).getIndex() 
                    : (Integer) ipadx;
            
            gbc.fill = fill;
            gbc.anchor = anchor;
            
            if(insets != null) {
                gbc.insets = insets;
            }
            
            return gbc;
        }
        
    }
    
    /**
     * La classe de liste de modèle de contraintes (en faite un tableau),
     * tableau qui sera utilisé lors de l’ajout d’un tableau d’éléments, 
     * avec une correspondance sur les index.
     */
    static public class LTC{
        
        protected TC[] constraints;
        
        public LTC(TC... constraints) {
            
            this.constraints = constraints;
        }
    }
    
    /**
     * L'interface pour les méthodes de modèle
     * A compléter, plein de méthode sont imaginable
     */
    interface TemplateMethod{
        int getIndex();
    }
    
    
    /**
     * Classe de méthode de modèle qui incrémente d'une certaine valeur
     * un index lors de son appel, avec une référence sur l'ancienne valeur de l'index
     * Le membre start permet simlepement de partir l'incrémentation via une valeur de base
     */
    public class Inc implements TemplateMethod{

        int inc = 1;
        int start = 0;
        int index = 0;
        int last_index = 0;

        Inc(int inc, int start) {
            this.inc = inc;
            this.start = start;
            index = start;
            last_index = start;
        }

        Inc(int inc) {
            this.inc = inc;
        }

        Inc() {}

        @Override
        public int getIndex() {
            
            last_index = index;
            index += inc;
            
            return last_index;
        }
    }
    
    /**
     * Classe de méthode de modèle qui référence un incrémentateur,
     * et retourne l'ancienne valeur de l'index de ceui-ci, plus une valeur optionelle
     */
    public class Last implements TemplateMethod{

        Inc inc;
        int addto = 0;

        Last(Inc inc, int addto) {
            this.inc = inc;
            this.addto = addto;
        }
        
        Last(Inc inc) {
            this.inc = inc;
        }
        
        public int getIndex() {

            return inc.last_index + addto;
        }
    }
    
    /**
     * Classe de méthode de modèle qui référence un incrémentateur,
     * et retourne la valeur de l'index de ceui-ci, plus une valeur optionelle
     */
    public class Ref implements TemplateMethod{

        Inc inc;
        int addto = 0;

        Ref(Inc inc, int addto) {
            this.inc = inc;
            this.addto = addto;
        }
        
        Ref(Inc inc) {
            this.inc = inc;
        }
        
        public int getIndex() {

            return inc.index + addto;
        }
    }
    
    //initialise le layout
    {
        setLayout(new GridBagLayout());
    }

    public IPan(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public IPan() {
    }
    
    
    /**
     * Ajoute un tableau de composants avec un modèle ou une liste 
     * de modèle de contrainte initialisé par défaut
     * 
     * @param constraints
     * @param comps 
     */
    public void append(Component... comps) {
        
        if(default_model instanceof TC){
            
            append((TC) default_model, comps);
        }
        else if(default_model instanceof LTC){
            
            append((LTC) default_model, comps);
        }
    }
    
    
    /**
     * Ajoute un tableau de composants avec un modèle de contrainte.
     * 
     * @param constraints
     * @param comps 
     */
    public void append(TC constraints, Component... comps) {
        
        int i = 0;
        int l = comps.length;
        
        for(; i < l; i++){
            addImpl(comps[i], constraints.getConstraints(), -1);
        }
    }
    
    
    /**
     * Ajoute un tableau de composants avec une liste de modèle de contrainte.
     * Les index du tableau de composants, correspondent aux index du tableau de contrainte 
     * 
     * @param constraints
     * @param comps 
     */
    public void append(LTC constraints, Component... comps) {
        
        int i = 0;
        int l = comps.length;
        
        for(; i < l; i++){
            addImpl(comps[i], constraints.constraints[i].getConstraints(), -1);
        }
    }
}
