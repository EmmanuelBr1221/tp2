package controleur;

import com.chat.client.ClientChat;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeConnectes extends MouseAdapter {

    private ClientChat clientChat;
    public EcouteurListeConnectes(ClientChat clientChat) {
        this.clientChat = clientChat;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        //à compléter
        if (evt.getClickCount() == 2){
            JList<String> source = (JList<String>) evt.getSource(); // recupere la source de l'evenement (la liste)


            String alias = source.getSelectedValue();// recupere element choisi  dans la liste

            //envoyer au serveur avec une commande JOIN
            if (clientChat != null && alias != null) {
                clientChat.envoyer("JOIN " + alias);
            }
        }
    }
}

