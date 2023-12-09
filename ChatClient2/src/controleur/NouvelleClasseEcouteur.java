package controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.chat.client.ClientChat;
import vue.PanneauInvitations;


public class NouvelleClasseEcouteur extends JFrame implements ActionListener { //classe d’écouteur d’événements d’action

    private PanneauInvitations panneauInvitations;
    protected ClientChat clientChat;


    public NouvelleClasseEcouteur(PanneauInvitations panneauInvitations, ClientChat clientChat) {

        this.panneauInvitations = panneauInvitations; // attribut panneau d’invitations
        this.clientChat = clientChat; // attribut client de chat


    }

    

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource() ;
        if (source instanceof JButton) {
            JButton b = (JButton)source ;

            String commande = b.getActionCommand() ;

            if (commande.equals("ACCEPTER")) {

            // recupere les invitations choisi
            java.util.List<String> invitationChosi = panneauInvitations.getElementsSelectionnes();

            // envoi au serveur commande JOIN pour chaque invitation sélectionnée
            for (String invitation : invitationChosi) {
                clientChat.envoyer("JOIN" + invitation);
            }

        } else if (commande.equals("REFUSER")) {

            // recupere les invitations pas choisi
            java.util.List<String> invitationPasChoisi = panneauInvitations.getElementsSelectionnes();

            // envoi au serveur commande DECLINE pour chaque invitation sélectionnée
            for (String invitation : invitationPasChoisi) {
                clientChat.envoyer("DECLINE" + invitation);
            }
        }

        this.panneauInvitations.vider(); // Retirer les invitations sélectionnées de la liste.

        }
    }


}