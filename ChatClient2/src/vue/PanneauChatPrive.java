package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauChatPrive extends PanneauChat {
    private JButton bAccepterOuInviter, bRefuser;
    private FenetreEchecs fenetreEchecs;
    public PanneauChatPrive() {
        bAccepterOuInviter = new JButton("Inviter échec");
        bRefuser = new JButton("Refuser");

        bAccepterOuInviter.setActionCommand("ACCEPTER");
        bRefuser.setActionCommand("REFUSER");

        //à compléter
        setLayout(new BorderLayout());

        JPanel chatPrive =new JPanel();

        chatPrive.add(bAccepterOuInviter);
        chatPrive.add(bRefuser);

        //ajouter au nord les 2 boutons
        this.add(bAccepterOuInviter,BorderLayout.NORTH);
        this.add(bRefuser,BorderLayout.NORTH);

        //cacher le bouton qui sert à refuser une partie d’échecs.
        bRefuser.setVisible(false);



    }
    @Override
    public void setEcouteur(ActionListener ecouteur) {
        super.setEcouteur(ecouteur);
        bAccepterOuInviter.addActionListener(ecouteur);
        bRefuser.addActionListener(ecouteur);
    }





    //La première change le texte du premier bouton à "Accepter" et fait apparaître le bouton pour refuser
    public void invitationEchecRecue() {
        //à compléter
        bAccepterOuInviter.setText("Accepter");
        bRefuser.setVisible(true);
    }


    //La seconde remet le texte du premier bouton à "Inviter échec" et cache le bouton pour refuser
    public void invitationEchecAnnulee() {
        //à compléter
        bAccepterOuInviter.setText(" Inviter échec");
        bRefuser.setVisible(false);
    }

    public void setFenetreEchecs(FenetreEchecs fenetreEchecs) {
        if (this.fenetreEchecs!=null){
            this.fenetreEchecs.setVisible(false);
            this.fenetreEchecs.dispose();
        }
        this.fenetreEchecs = fenetreEchecs;
    }
    public FenetreEchecs getFenetreEchecs() {
        return this.fenetreEchecs;
    }
}