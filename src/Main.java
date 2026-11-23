import java.awt.Component;
import java.awt.Font;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

    static class CharacterData {
        String name;
        String type;
        int level;
        int hp;
        int atk;

        CharacterData(String name, String type, int level) {
            this.name = name;
            this.type = type;
            this.level = level;

            // Menghitung HP dan ATK
            this.hp = 100 + (level * 10);
            this.atk = 10 + (level * 5);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Membuat daftar karakter
            CharacterData[] characters = {
                new CharacterData("Arjuna", "WARRIOR", 5),
                new CharacterData("Merlin", "MAGE", 3),
                new CharacterData("Athena", "ARCHER", 4),
                new CharacterData("Elara", "HEALER", 2),
                new CharacterData("Gandalf", "MAGE", 10)
            };

            // Membuat jendela
            JFrame frame = new JFrame("Character Manager v0.3");
            frame.setSize(750, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Panel utama
            JPanel panel = new JPanel();
            panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));

            // Memberikan jarak dari tepi jendela
            panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
            );

            // Judul daftar karakter
            JLabel title = new JLabel("=== Daftar Karakter ===");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(title);

            // Menghitung total level
            int totalLevel = 0;

            // Menampilkan seluruh karakter
            for (CharacterData character : characters) {

                String info = character.name
                    + " (" + character.type + ")"
                    + " - Level " + character.level
                    + " | HP: " + character.hp
                    + " | ATK: " + character.atk;

                JLabel label = new JLabel(info);
                label.setFont(new Font("Arial", Font.PLAIN, 18));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);

                panel.add(Box.createVerticalStrut(12));
                panel.add(label);

                totalLevel += character.level;
            }

            // Menghitung rata-rata level
            double average = (double) totalLevel / characters.length;

            panel.add(Box.createVerticalStrut(35));

            JLabel averageLabel = new JLabel(
                "Rata-rata Level: " +
                String.format(
                    Locale.forLanguageTag("id-ID"),
                    "%.2f",
                    average
                )
            );

            averageLabel.setFont(
                new Font("Arial", Font.PLAIN, 18)
            );

            averageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(averageLabel);

            // Memasukkan panel ke jendela
            frame.add(panel);

            // Menampilkan jendela di tengah layar
            frame.setLocationRelativeTo(null);

            // Menampilkan aplikasi
            frame.setVisible(true);
        });
    }
}
