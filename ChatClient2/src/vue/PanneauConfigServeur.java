package vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {

        // l'adresse ip
        JPanel leAdresse = new JPanel();// creation d'un "Panel" un textfield
        JLabel theAdresse = new JLabel(" adresse ip : ");
        JTextField nbAdresse = new JTextField(18);// Endroit ou les info seront taper les chiffres 18= nb caracter qu" on peut taper

        nbAdresse.setText(adr); // affiche adresse IP dans le champ texte

        leAdresse.add(theAdresse);// ajout du JLabel dans le Panel  pour l'adresse IP
        leAdresse.add(nbAdresse);// ajout ajout du JTextField dans le Panel pour l'adresse IP



        // le port
        JPanel lePort = new JPanel();// creation d'un "Panel" un textfield
        JLabel thePort = new JLabel(" port : ");
        JTextField nbPort = new JTextField(18);// Endroit ou les info seront taper les chiffres 18= nb caracter qu" on peut taper

        nbPort.setText(String.valueOf(port)); // Affiche numero de port dans le champ texte

        lePort.add(thePort); // ajout du JLabel dans le Panel  pour le port
        lePort.add(nbPort);// ajout ajout du JTextField dans le Panel pour le port


        this.setLayout(new GridLayout(2, 1)); // Pour afficher les panneaux l'un en dessous de l'autre
        this.add(leAdresse); // pour ajouter le Panel de l'adresse IP dans cette classe
        this.add(lePort);//pour ajouter le Panel du port dans cette classe




        //à compléter
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
