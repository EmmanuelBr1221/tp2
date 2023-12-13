package controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.chat.client.ClientChat;
import vue.PanneauInvitations;


public class NouvelleClasseEcouteur extends JFrame implements ActionListener { //classe d’écouteur d’événements d’action

    private PanneauInvitations panneauInvitations;
    protected ClientChat clientChat;


    public NouvelleClasseEcouteur(ClientChat clientChat , PanneauInvitations panneauInvitations) {

        this.clientChat = clientChat; // attribut client de chat
        this.panneauInvitations = panneauInvitations; // attribut panneau d’invitations



    }


    

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource() ;
        if (source instanceof JButton) {
            JButton b = (JButton)source ;

            String commande = b.getActionCommand() ;

            java.util.List<String> invitationsSelectionnees = panneauInvitations.getElementsSelectionnes();


            if (commande.equals("ACCEPTER")) {
                // recupere les invitations  choisi
                for (String invitation : invitationsSelectionnees) {
                    panneauInvitations.ajouterInvitationRecue(invitation);
                    clientChat.envoyer("JOIN" + invitation); // envoi au serveur commande JOIN pour chaque invitation chosi


                }
            } else if (commande.equals("REFUSER")) {

                // recupere les invitations pas choisi
                for (String invitation : invitationsSelectionnees) {
                    panneauInvitations.retirerInvitationRecue(invitation);
                    clientChat.envoyer("DECLINE" + invitation);// envoi au serveur commande DECLINE pour chaque invitation pas chosi
                }
            }

        this.panneauInvitations.vider(); // Retirer les invitations sélectionnées de la liste.

        }
    }


}