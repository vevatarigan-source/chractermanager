import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;

public class Main {

    // Data karakter
    static class CharacterData {
        String name;
        String role;
        int level;
        int hp;
        int atk;
        boolean highlighted;
        boolean inactive;

        CharacterData(String name, String role, int level,
                      boolean highlighted, boolean inactive) {
            this.name = name;
            this.role = role;
            this.level = level;
            this.hp = 100 + (level * 10);
            this.atk = 10 + (level * 5);
            this.highlighted = highlighted;
            this.inactive = inactive;
        }
    }

    // Warna berdasarkan role
    static Color getRoleColor(String role) {
        switch (role) {
            case "WARRIOR":
                return new Color(220, 46, 50);

            case "MAGE":
                return new Color(0, 132, 225);

            case "ARCHER":
                return new Color(0, 183, 153);

            case "HEALER":
                return new Color(105, 85, 230);

            default:
                return Color.GRAY;
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Membuat 7 karakter
            CharacterData[] characters = {
                new CharacterData("Arjuna", "WARRIOR", 5, true, false),
                new CharacterData("Merlin", "MAGE", 3, false, false),
                new CharacterData("Athena", "ARCHER", 4, false, false),
                new CharacterData("Elara", "HEALER", 2, false, false),
                new CharacterData("Gandalf", "MAGE", 10, true, false),
                new CharacterData("Gatotkaca", "WARRIOR", 7, true, false),
                new CharacterData("Morgana", "MAGE", 1, false, true)
            };

            // Membuat JFrame
            JFrame frame = new JFrame("Character Manager v0.4");
            frame.setSize(650, 680);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Panel utama
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(15, 20));
            mainPanel.setBackground(new Color(18, 18, 31));

            mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
            );

            // Judul
            JLabel title = new JLabel(
                "Daftar Karakter (7)",
                SwingConstants.CENTER
            );

            title.setFont(new Font("SansSerif", Font.BOLD, 30));
            title.setForeground(Color.WHITE);

            mainPanel.add(title, BorderLayout.NORTH);

            // Panel daftar karakter
            JPanel listPanel = new JPanel();
            listPanel.setLayout(
                new BoxLayout(listPanel, BoxLayout.Y_AXIS)
            );

            listPanel.setBackground(new Color(18, 18, 31));

            int totalLevel = 0;

            // Menampilkan karakter menggunakan loop
            for (CharacterData character : characters) {

                String info = character.name
                    + " | " + character.role
                    + " | Lv." + character.level
                    + " | HP:" + character.hp
                    + " | ATK:" + character.atk;

                JLabel label = new JLabel(info);

                // Warna berdasarkan role
                label.setOpaque(true);
                label.setBackground(getRoleColor(character.role));

                // Warna teks
                if (character.inactive) {
                    label.setForeground(new Color(155, 155, 155));
                } else if (character.highlighted) {
                    label.setForeground(new Color(255, 211, 85));
                } else {
                    label.setForeground(Color.WHITE);
                }

                // Mengatur font
                if (character.highlighted) {
                    label.setFont(
                        new Font("SansSerif", Font.BOLD, 19)
                    );
                } else {
                    label.setFont(
                        new Font("SansSerif", Font.PLAIN, 19)
                    );
                }

                // Jarak teks dari tepi label
                label.setBorder(
                    BorderFactory.createEmptyBorder(12, 18, 12, 10)
                );

                // Mengatur ukuran label
                label.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 53)
                );

                label.setAlignmentX(Component.LEFT_ALIGNMENT);

                listPanel.add(label);
                listPanel.add(Box.createVerticalStrut(12));

                totalLevel += character.level;
            }

            mainPanel.add(listPanel, BorderLayout.CENTER);

            // Menghitung rata-rata level
            double average = (double) totalLevel / characters.length;

            JLabel averageLabel = new JLabel(
                "Rata-rata Level: "
                + String.format(
                    Locale.forLanguageTag("id-ID"),
                    "%.2f",
                    average
                ),
                SwingConstants.CENTER
            );

            averageLabel.setFont(
                new Font("Monospaced", Font.PLAIN, 18)
            );

            averageLabel.setForeground(Color.WHITE);

            mainPanel.add(averageLabel, BorderLayout.SOUTH);

            // Memasukkan panel ke frame
            frame.add(mainPanel);

            // Posisi jendela di tengah layar
            frame.setLocationRelativeTo(null);

            // Menampilkan jendela
            frame.setVisible(true);
        });
    }
}
