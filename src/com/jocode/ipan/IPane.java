/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocode.ipan;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Jo
 */
public class IPane  extends JPanel{
    
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

    public Object gridx = 0;
    public Object gridy = 0;
    public Object gridwidth = 1;
    public Object gridheight = 1;
    public Object weightx = 0;
    public Object weighty = 0;
    public Object insets;
    public Object fill;
    public Object anchor;
    public Object ipadx;
    public Object ipady;
    private int index = 0;

    protected GridBagConstraints gbc = new GridBagConstraints();
    
    private static final String[] constraints = new String[]{
        "gridx", "gridy", "gridwidth", "gridheight", "weightx",
        "weighty", "insets", "fill", "anchor", "ipadx", "ipady"
    };
    
    public static class Glue extends Component{
    }          
    
    public IPane() {
        this(true);
    }

    public IPane(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        setLayout(new GridBagLayout());
    }
    
    
    public IPane insert(Component... cmps){
        
        for(int i = 0, l = cmps.length; i < l; i++){
            
            Component cmp = cmps[i];
            initContraints();
            
            if(cmp != null) {
                add(cmps[i], gbc);
            }
            
            index++;
        }
        return this;
    }
    
    public IPane insert(int j, Component... cmps){
        
        index = j;
        return insert(cmps);
    }
    
    protected void initContraints(){
        
        for(int i = 0; i < constraints.length; i++){
            
            try {
                
                Object field = getContraintValue(constraints[i]);
                
                if(field != null){
                    
                    if(field instanceof Template) {
                        field = ((Template) field).progress();
                    }
                    
                    GridBagConstraints.class.getField(constraints[i]).set(gbc, field);
                }
                    
            } catch (Exception ex) {
            }
        }
    }
    
    protected Object getContraintValue(String constraint) {
        
        Object field = null;
        
        try {
            
            field = IPane.class.getField(constraint).get(this);
            
            if (field != null && field.getClass().isArray()){
                
                int i = index;
                if(Array.getLength(field) <= i ) {
                    i = Array.getLength(field) - 1;
                }
                
                field = Array.get(field, i);
            }
            
        } catch (Exception ex) {
        }
        
        return field;
    }
    
    
    //incrémente
    public Object[] i(int loop, Object... increment){
        
        List<Integer> res = new ArrayList<Integer>();
        
        for(int i = 0; i < loop; i++) {
            i0(res, increment, i);
        }
        
        return (Object[]) res.toArray();
    }
    
    private  void i0(List res, Object[] increments, int cu){
        
        for(int i = 0; i < increments.length; i++){
            
            Object increment = increments[i];
            
            if (increment != null) {
                
                if (increment.getClass().isArray()) {
                    i0(res, (Object[]) increment, cu);
                }else{
                    res.add(((Integer) increments[i]) + cu);
                }
            }
        }
    }

    //répète
    public Object r(int occ, Object... repeat){
        
        Object[] res = new Object[occ];
        
        for(int i = 0; i < occ; i++){
            res[i] = repeat;
        }
        
        return res;
    }

    //concat
    public Object c(Object... concats){
        
        List res = new ArrayList();
        c0(res, concats);
        
        return res.toArray();
    }

    private  void c0(List res, Object[] concats){
        
        for(int i = 0; i < concats.length; i++){
            
            Object concat = concats[i];
            
            if (concat != null) {
                
                if (concat.getClass().isArray()) {
                    c0(res, (Object[]) concat);
                }else{
                    res.add(concat);
                }
            }
        }
    }
    
    //modele
    abstract class Template{
        
        int index = 0;
        final Object[] template;
        
        Template(Object[] template) {
            
            this.template = template;
        }
        
        void setIndex(){
            
            index= index == template.length - 1 ? 0 : index +1;
        }
        
        abstract Object progress();
    }
    
    //model de répétition
    public Object rt(Object... template){
        
        return new RTemplate(template);
    }
    
    class RTemplate extends Template{
        
        RTemplate(Object[] template) {
            super(template);
        }
        
        @Override
        Object progress(){
            
            Object value = template[index];
            setIndex();
            
            return value;
        }
    }
    
    //model d'incrémentation
    public Object it(Object... template){
        return new ITemplate(template);
    }
    
    class ITemplate extends Template{
        
        int increment = 0;
        
        ITemplate(Object[] template) {
            
            super(template);
        }
        
        @Override
        Object progress(){
            
            Object value = ((Integer) template[index]) + increment;
            
            increment = index == template.length - 1 ? increment + 1 : increment;
            setIndex();
            
            return value;
        }
    }
}
