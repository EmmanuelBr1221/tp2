package controleur;

import com.chat.client.ClientChat;
import vue.PanneauChat;
import java.awt.event.ActionListener;

import vue.PanneauChatPrive;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurChatPrive extends EcouteurChatPublic {
    private String alias;

    public EcouteurChatPrive(String alias, ClientChat clientChat, PanneauChat panneauChat) {
        super(clientChat, panneauChat);
        this.alias = alias;
    }
    //à compléter (redéfinir la méthode actionPerformed())


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {

            JButton d = (JButton) source;

            String command = d.getActionCommand() ;

            //Si c’est le bouton pour accepter de jouer aux échecs
            if (command.equals("ACCEPTER")){
                clientChat.envoyer("CHESS");// envoit au serveur command CHESS

            }
            //Si c’est le bouton pour refuser de jouer aux échecs
            else if (command.equals("REFUSER")){
                clientChat.envoyer("DECLINE");// envoit au serveur command DECLINE
            }

        }

        if (source instanceof JTextField){

            JTextField text = (JTextField) source;

            String leMessage = text.getText(); // recupere  texte saisi


            //Si le texte saisi est QUIT
            if(leMessage.equals("QUIT")){
                clientChat.envoyer("QUIT");// envoit au serveur command QUIT
            }

            //Si le texte saisi est ABANDON
            else if (leMessage.equals("ABANDON")){
                clientChat.envoyer("ABANDON");// envoit au serveur command ABANDON
            }
            else{
                clientChat.envoyer("PRV" + leMessage);
            }



        }

    }
}