package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauChat extends JPanel {
    protected JTextArea zoneChat;
    protected JTextField champDeSaisie;

    public PanneauChat() {
        //setLayout(new BorderLayout());
        //à compléter.

        // zone de chat public
        JPanel zoneChatPublic =new JPanel();


        zoneChat.setEditable(false);// Une zone de texte qui doit être non éditable
        zoneChatPublic.add(new JScrollPane(zoneChat));// barre de defilement
        this.add(zoneChatPublic,BorderLayout.NORTH);
        zoneChatPublic.setLayout(new FlowLayout(FlowLayout.CENTER));//il place la zone de chat dans un JScrollPane placé au centre;

        zoneChatPublic.add(zoneChat);


        //Champ de saisie
        JPanel leSaisie= new JPanel();

        champDeSaisie =new JTextField(50);
        champDeSaisie.add(champDeSaisie,BorderLayout.SOUTH); // il contient le champ de saisie au sud;

        leSaisie.add(champDeSaisie);

       /* saisie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
*/


    }

    public void ajouter(String msg) {
        zoneChat.append("\n"+msg);
    }
    public void setEcouteur(ActionListener ecouteur) {
        //Enregistrer l'écouteur auprès du champ de saisie
       champDeSaisie.addActionListener(ecouteur);
    }

    public void vider() {
        this.zoneChat.setText("");
    }
}