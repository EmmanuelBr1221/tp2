package controleur;

import com.chat.client.ClientChat;
import com.chat.commun.net.Connexion;
import vue.PanneauChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurChatPublic implements ActionListener {
    protected ClientChat clientChat;
    protected PanneauChat panneauChat;

    public EcouteurChatPublic(ClientChat clientChat, PanneauChat panneauChat) {
        this.clientChat = clientChat;
        this.panneauChat = panneauChat;
        this.panneauChat.setEcouteur(this);
    }



    @Override
    public void actionPerformed(ActionEvent evt) {

        Object source = evt.getSource();

        if (source instanceof JTextField) {

            JTextField texte = (JTextField) source;

            String message = texte.getText(); // recupere  texte saisi

            if (!message.isEmpty()) { // verifie si le texte est pas vide

                // envoi du message au serveur
                clientChat.envoyer("MSG " + message);


                // ajout du message au panneau de chat
                panneauChat.ajouter("MOI>>" + message);

                // vide le champ de texte
                texte.setText("");
            }
        }




    }
}