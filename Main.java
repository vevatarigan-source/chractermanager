import javax.swing.SwingUtilities;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import view.MainWindow;

public class Main {
    public static void main(String[] args) {
        CharacterManager manager = new CharacterManager();
        // Bagian yang mencetak "Karakter 'Arjuna' berhasil ditambahkan." dst.

        // 8 karakter agar menghasilkan total 8, rata-rata 4,75, tertinggi 10, dan level >= 5 berjumlah 4
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5, 150, 35));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3, 130, 25));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4, 140, 30));
        manager.addCharacter(new Character("Elara", CharacterType.HEALER, 2, 120, 20));
        manager.addCharacter(new Character("Gandalf", CharacterType.MAGE, 10, 200, 60));
        manager.addCharacter(new Character("Gatotkaca", CharacterType.WARRIOR, 7, 170, 45));
        manager.addCharacter(new Character("Morgana", CharacterType.MAGE, 1, 110, 15));
        manager.addCharacter(new Character("Artemis", CharacterType.ARCHER, 6, 160, 40));

        // PASTIKAN 4 BARIS DI BAWAH INI ADA DI MAIN.JAVA:
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);
        });
    }
}

