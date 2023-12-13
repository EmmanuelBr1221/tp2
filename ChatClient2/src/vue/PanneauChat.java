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

        this.setLayout(new BorderLayout());

        // Zone de chat public
        zoneChat = new JTextArea();
        zoneChat.setEditable(false);// Une zone de texte qui doit être non éditable
        JScrollPane scrollPane = new JScrollPane(zoneChat);// barre de defilement
        this.add(scrollPane, BorderLayout.CENTER);//il place la zone de chat dans un JScrollPane placé au centre;



        //Champ de saisie
        champDeSaisie = new JTextField();

        this.add(champDeSaisie, BorderLayout.SOUTH);// il contient le champ de saisie au sud;





        champDeSaisie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texteSaisie = champDeSaisie.getText();
                ajouter(texteSaisie);
            }
        }
        );



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