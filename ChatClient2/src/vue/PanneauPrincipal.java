package vue;

import com.chat.client.ClientChat;
import controleur.EcouteurChatPrive;
import controleur.EcouteurChatPublic;
import controleur.EcouteurListeConnectes;
import controleur.NouvelleClasseEcouteur;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;



/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauPrincipal  extends JPanel {
    private final ClientChat clientChat;
    private final DefaultListModel<String> connectes;
    private final JList<String> jlConnectes;
    private final PanneauChat panneauChatPublic;
    private final PanneauInvitations panneauInvitations ;
    private final JDesktopPane bureau;
    private final Map<String, PanneauChatPrive> panneauxPrives;

    private final PanneauChatPrive panneauChatPrive;













    public PanneauPrincipal(ClientChat clientChat) {
//***********************  debut question 3.3 *************************************//

        panneauInvitations = new PanneauInvitations();


        NouvelleClasseEcouteur nvClasseEcouteur = new NouvelleClasseEcouteur(clientChat, panneauInvitations);

        panneauInvitations.setEcouteur(nvClasseEcouteur);



//******************** fin question 3.3 ****************************************//



        panneauChatPrive =new PanneauChatPrive();


        this.clientChat = clientChat;

        panneauChatPublic = new PanneauChat();
        panneauChatPublic.setBorder(BorderFactory.createTitledBorder("Salon de chat public"));

        EcouteurChatPublic ecouteurChatPublic = new EcouteurChatPublic(clientChat, panneauChatPublic);
        panneauChatPublic.setEcouteur(ecouteurChatPublic);


        panneauxPrives = new HashMap<>();

        connectes = new DefaultListModel<>();

        //Composant affichant la liste des connectés :
        jlConnectes = new JList<>(connectes);
        jlConnectes.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jlConnectes.setBorder(BorderFactory.createTitledBorder("Connectés"));
        jlConnectes.setPreferredSize(new Dimension(150,500));

        //Enregistrement de l'écouteur de souris:
        jlConnectes.addMouseListener(new EcouteurListeConnectes(clientChat));

        //Le bureau :
        bureau = new JDesktopPane();
        bureau.setBackground(Color.lightGray);

        this.setLayout(new BorderLayout());
        this.add(jlConnectes, BorderLayout.WEST);
        this.add(panneauInvitations, BorderLayout.EAST);
        JSplitPane jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panneauChatPublic,bureau);
        jsp1.setDividerLocation(300);
        this.add(jsp1, BorderLayout.CENTER);
    }

    public void ajouterMessage(String msg) {
        this.panneauChatPublic.ajouter(msg);
    }
    public void ajouterConnectes(String str) {
        String[] membres = str.split(":");
        for (String s : membres) {
            System.out.println("Ajout connecte : "+s);
            if (!s.equals(clientChat.getAlias())) //On n'affiche pas l'alias de l'utilisateur dans la liste.
                connectes.addElement(s);
        }
    }

    public void ajouterInvitationRecue(String alias) {
        this.panneauInvitations.ajouterInvitationRecue(alias);
    }

    public void retirerInvitationRecue(String alias) {
        this.panneauInvitations.retirerInvitationRecue(alias);
    }

    public void retirerConnecte(String alias) {
        connectes.removeElement(alias);
        panneauInvitations.retirerInvitationRecue(alias);
        this.retirerSalonPrive(alias);
    }

    /**
     *  Créer une fenêtre interne JInternalFrame dans le bureau de titre alias
     *  etcontenant un panneau de chat, permettant de chatter avec alias
     *
     * @param alias
     */
    public void creerFenetreSalonPrive(String alias) {
        JInternalFrame iFrame = new JInternalFrame(alias);
        PanneauChatPrive pc = new PanneauChatPrive();
        this.retirerInvitationRecue(alias);

        pc.setEcouteur(new EcouteurChatPrive(alias,this.clientChat,pc));

        panneauxPrives.put(alias,pc);

        iFrame.add(pc);
        iFrame.setSize(200,200);
        iFrame.setResizable(true);
        iFrame.setVisible(true);
        bureau.add(iFrame);
    }

    public void ajouterMessagePrive(String alias, String msg) {
        String message = alias+">>"+msg;
        System.out.println("PRIVÉ : "+alias+">>"+msg);
        //à compléter

        PanneauChatPrive lepanneauChatPrive = panneauxPrives.get(alias);
        if(lepanneauChatPrive != null){
            lepanneauChatPrive.ajouter(message);//afficher le message privé dans le bon panneau de chat privé
        }

    }

    public void inviteEchecs(String alias) {
        PanneauChatPrive pc = panneauxPrives.get(alias);
        if (pc!=null) {
            pc.invitationEchecRecue();
        }
    }
    public void annuleInviteEchecs(String alias) {
        PanneauChatPrive pc = panneauxPrives.get(alias);
        if (pc!=null) {
            pc.invitationEchecAnnulee();
        }
    }

    /**
     * Vide les éléments d'interface du panneauPrincipal.
     */
    public void vider() {
        this.connectes.clear();
        this.bureau.removeAll();
        this.panneauxPrives.clear();
        this.panneauInvitations.vider();
        this.panneauChatPublic.vider();
    }

    public void retirerSalonPrive(String alias) {
        this.panneauxPrives.remove(alias);
        JInternalFrame[] fens = bureau.getAllFrames();
        for (JInternalFrame fen:fens) {
            if (fen.getTitle().equals(alias)) {
                fen.setVisible(false);
                bureau.remove(fen);
                break;
            }
        }
    }

    public void setFenetreEchecs(String alias, FenetreEchecs fenetreEchecs) {
        PanneauChatPrive pcp = panneauxPrives.get(alias);
        if (pcp != null)
            pcp.setFenetreEchecs(fenetreEchecs);
    }

    public FenetreEchecs getFenetreEchecs() {
        Iterator<PanneauChatPrive> it = panneauxPrives.values().iterator();
        FenetreEchecs fen = null;
        while (it.hasNext()) {
            fen = it.next().getFenetreEchecs();
            if (fen!=null)
                return fen;
        }
        return null;
    }

}