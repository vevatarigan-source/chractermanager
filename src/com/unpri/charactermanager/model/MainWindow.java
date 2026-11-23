package com.unpri.charactermanager.model;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.ArrayList;

/**
 * Jendela utama aplikasi Character Manager.
 * Menampilkan daftar karakter yang diterima dari luar (bukan dibuat sendiri).
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;   // data yang akan ditampilkan

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.3");    // judul title bar
        this.characterList = characterList;  // simpan data yang diterima dari Main.java

        initComponents();

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        mainPanel = new JPanel();                 // container utama

        JLabel titleLabel = new JLabel("=== Daftar Karakter ===");
        mainPanel.add(titleLabel);                 // tambahkan judul ke panel

        JList<Character> characterJList = new JList<>(characterList.toArray(new Character[0]));
        mainPanel.add(new JScrollPane(characterJList));

        add(mainPanel);   // "add" di sini method milik JFrame (this), menempatkan panel ke jendela
    }
    
}