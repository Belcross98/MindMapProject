package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.*;

public class Info extends JDialog {

    private JLabel labelaIme =new JLabel("Ime: ");
    private JLabel labelaPrezime = new JLabel("Prezime:");
    private JLabel labelaBrojIndeksa = new JLabel("Broj indeksa:");
    private JLabel labelaSlikaStudenta = new JLabel("Slika: ");

    private JLabel ime = new JLabel("Katarina");
    private JLabel prezime = new JLabel("Račić");
    private JLabel brojIndeksa = new JLabel("98/2022RN");
    private JLabel slikaStudenta = new JLabel("/");

    private JLabel ime1 = new JLabel("Teodor");
    private JLabel prezime1 = new JLabel("Jakovljević");
    private JLabel brojIndeksa1 = new JLabel("96/2022RN");
    private JLabel slikaStudenta1 = new JLabel("/");

        public Info(Frame parent, String title, boolean modal){
            super(parent, title, modal);

            setSize(300, 300);
            setLocationRelativeTo(parent);

            JPanel jPanel = new JPanel();

            jPanel.setLayout(new GridLayout(4,4));

            jPanel.add(labelaIme);
            jPanel.add(ime);
            jPanel.add(ime1);
            jPanel.add(labelaPrezime);
            jPanel.add(prezime);
            jPanel.add(prezime1);
            jPanel.add(labelaBrojIndeksa);
            jPanel.add(brojIndeksa);
            jPanel.add(brojIndeksa1);
            jPanel.add(labelaSlikaStudenta);
            jPanel.add(slikaStudenta);
            jPanel.add(slikaStudenta1);

                    this.add(jPanel);
                    jPanel.setVisible(true);


    }
}
