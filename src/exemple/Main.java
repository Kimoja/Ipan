/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exemple;

import com.jocode.panel.FieldsPan;
import com.jocode.panel.FieldsPan.Field;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class Main {
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                
                JFrame jf1 = new JFrame();
                jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                jf1.getContentPane().add(new FieldsPan(){{

                        setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("Test"),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5))
                        );
                        Field fr = append(new JLabel("Nom"), new JTextField(), new JLabel("Votre saisie est erroné"), new JLabel("Attention je suis un warning!"));
                        
                        append(new JLabel("Prénom"), new JTextField());
                        append(new JLabel("Adresse"), new JTextField());
                        append(new JLabel("Ville"), new JTextField());
                        append(new JLabel("Code postale"), new JTextField(), new JLabel("Votre saisie est erroné"), new JLabel("Attention je suis un warning!"));
                        append(new JLabel("Email"), new JTextField());
                    }});
                
                jf1.pack();
                jf1.setVisible(true);
            }
        });
    }
}
