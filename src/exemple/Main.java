/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exemple;

import com.jocode.ipan.IPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //exécuter dans le thread swing
        SwingUtilities.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                
                /************************************************************
                 * Exemple 1 Reproduction de BorderLayout
                 ***********************************************************/
                
                JFrame jf1 = createFrame();
                
                //utilisation des accolades d'initialisation pour un code plus concis et lisible....
                IPane borderLayoutPane = new IPane(){{
                    gridx       = c(0, i(3, 0), 0);
                    gridy       = c(0, r(3, 1), 2);
                    gridwidth   = c(3, r(3, 1), 3);
                    weightx     = c(0, 0, 1, 0, 0);
                    weighty     = c(0, r(3, 1), 0);
                    fill        = c(HORIZONTAL, VERTICAL, BOTH, VERTICAL, HORIZONTAL);
                }};
                
                //insère les différents éléments
                borderLayoutPane.insert(
                    new JButton("North"),
                    new JButton("West")
                );
                borderLayoutPane.insert(4, new JButton("South"));
                borderLayoutPane.insert(2, 
                    new JButton("Center"),
                    new JButton("East")
                );
                
                jf1.getContentPane().add(borderLayoutPane);
                
                initFrame(jf1);
                
                
                /************************************************************
                 * Exemple 2 Un formulaire classique
                 ***********************************************************/
                
                JFrame jf2 = createFrame();
                
                //-------------simpleForm------------------------------------
                IPane simpleForm = new IPane(){{
                    gridy       = c(i(4, 0));
                    weightx     = 1;
                    weighty     = c(0, 0, 1, 0);
                    fill        = BOTH;
                }};
                jf2.getContentPane().add(simpleForm);
                
                //-------------profilForm------------------------------------
                IPane profilForm = new IPane(){{
                    gridx       = c(r(2, i(4, 0)), 0, 1);
                    gridy       = c(i(2, r(4, 0)), 2, 2);
                    gridwidth   = c(r(9, 1), 3);
                    weightx     = c(r(5, 0, 1));
                    weighty     = c(r(10, 0));
                    fill        = BOTH;
                    anchor      = NORTHWEST;
                    insets      = new Insets(3, 3, 3, 3);
                }};
                
                profilForm.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Profil"),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5))
                );
                
                profilForm.insert(
                    new JLabel("Nom"),new JTextField(),
                    new JLabel("Prénom"),new JTextField(),
                    new JLabel("Titre"),new JTextField(),
                    new JLabel("Surnom"),new JTextField(),
                    new JLabel("Adresse"),new JTextField()
                );
                simpleForm.insert(profilForm); 
                
                //-------------emailForm------------------------------------
                IPane emailForm = new IPane(){{
                    gridx       = c(0, 1, 2, 0, 2, 2, 2);//c(i(3, 0), 0, r(3, 2))
                    gridy       = c(0, 0, 0, 1, 1, 2, 3);
                    gridwidth   = c(1, 1, 1, 2, 1, 1, 1);
                    gridheight  = c(1, 1, 1, 3, 1, 1, 1);
                    weightx     = c(0, 1, 0, 1, 0, 0, 0);
                    weighty     = c(0, 0, 0, 1, 0, 0, 0);
                    fill        = BOTH;
                    anchor      = NORTHWEST;
                    insets      = new Insets(3, 3, 3, 3);
                }};
                
                emailForm.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Email"),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5))
                );
                
                emailForm.insert(
                    new JLabel("Email adresse"),new JTextField(),
                    new JButton("Ajouter"), new JList(),
                    new JButton("Modifier"), new JButton("Supprimer"),
                    new JButton("Avancé")
                );
                simpleForm.insert(emailForm); 
                
                //-------------glue------------------------------------
                simpleForm.insert(new IPane.Glue()); 
                
                
                //-------------buttonForm------------------------------------
                IPane buttonForm = new IPane(){{
                    gridx       = c(0, 1, 2);
                    weightx     = c(1, 0, 0);
                    fill        = HORIZONTAL;
                    insets      = new Insets(3, 3, 3, 3);
                    anchor      = SOUTHEAST;
                }};
                
                buttonForm.insert(
                    new IPane.Glue(),
                    new JButton("Comfirmer"), new JButton("Annuler")
                );
                simpleForm.insert(buttonForm); 
                
                initFrame(jf2);
                
                
                /************************************************************
                 * Exemple 3 Utilisation d'un modèle
                 ***********************************************************/
                
                final JFrame jf3 = createFrame();
                
                final IPane templatePane = new IPane(){{
                    gridx       = c(0, rt(0, 1, 2));
                    gridy       = c(0, it(1, 1, 1));
                    weightx     = c(0, rt(0, 1, 2));
                    weighty     = c(0, rt(0, 1, 2));
                    fill        = c(HORIZONTAL, rt(NONE, HORIZONTAL, BOTH));
                    insets      = c(new Insets(3, 3, 3, 3), rt(
                                    new Insets(1, 1, 1, 1), 
                                    new Insets(5, 5, 5, 5), 
                                    new Insets(10, 10, 10, 01))
                                  );
                }};
                
                JButton addBt = new JButton("Ajouter un élément");
                addBt.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        templatePane.insert(new JButton("Teste"));
                        templatePane.repaint();
                        jf3.pack();
                    }
                }); 
                
                templatePane.insert(addBt);     
                
                jf3.getContentPane().add(templatePane);
                
                initFrame(jf3);
                
            }
        });
    }
    
    private static JFrame createFrame() {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jf;
    }
    
    private static void initFrame(JFrame jf){
        jf.pack();
        jf.setVisible(true);
    }
}
