package view;

import static view.AppTheme.*;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;
        initUI();
    }

    private void initUI() {
        setTitle("Character Manager v0.5");
        setSize(740, 520);
        setMinimumSize(new Dimension(680, 480));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout(0, 12));
        rootPanel.setBackground(BG_MAIN);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(14, 18, 10, 18));

        // 1. Bagian atas: Header + 4 Kartu Statistik
        List<Character> characters = manager.getAllCharacters();
        int totalCount = characters.size();
        double avgLevel = calculateAverageLevel(characters);
        int maxLevel = calculateMaxLevel(characters);
        int highLevelCount = calculateHighLevelCount(characters);

        JPanel topSection = new JPanel(new BorderLayout(0, 12));
        topSection.setBackground(BG_MAIN);
        topSection.add(createHeaderPanel(totalCount), BorderLayout.NORTH);
        topSection.add(createStatsGrid(totalCount, avgLevel, maxLevel, highLevelCount), BorderLayout.CENTER);

        // 2. Bagian tengah: Daftar karakter (kiri) + Legenda Role (kanan)
        JPanel centerSection = new JPanel(new BorderLayout(16, 0));
        centerSection.setBackground(BG_MAIN);
        centerSection.add(createCharacterListPanel(characters), BorderLayout.CENTER);
        centerSection.add(createLegendPanel(), BorderLayout.EAST);

        // 3. Bagian bawah: Status Bar
        JPanel statusBar = createStatusBar(totalCount);

        rootPanel.add(topSection, BorderLayout.NORTH);
        rootPanel.add(centerSection, BorderLayout.CENTER);
        rootPanel.add(statusBar, BorderLayout.SOUTH);

        setContentPane(rootPanel);
    }

    private JPanel createHeaderPanel(int totalCount) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_MAIN);

        JLabel titleLabel = new JLabel("Character Manager");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_LIGHT);

        JLabel countLabel = new JLabel(totalCount + " karakter");
        countLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        countLabel.setForeground(GOLD);

        header.add(titleLabel, BorderLayout.WEST);
        header.add(countLabel, BorderLayout.EAST);
        return header;
    }

    private JPanel createStatsGrid(int totalCount, double avgLevel, int maxLevel, int highLevelCount) {
        JPanel grid = new JPanel(new GridLayout(2, 2, 12, 12));
        grid.setBackground(BG_MAIN);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("id-ID"));
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("0.##", symbols);

        grid.add(createStatCard("Total karakter", String.valueOf(totalCount)));
        grid.add(createStatCard("Rata-rata level", df.format(avgLevel)));
        grid.add(createStatCard("Level tertinggi", String.valueOf(maxLevel)));
        grid.add(createStatCard("Level tinggi (>= " + HIGH_LEVEL_THRESHOLD + ")", String.valueOf(highLevelCount)));

        return grid;
    }

    private JPanel createStatCard(String labelText, String valueText) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setForeground(TEXT_MUTED);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel value = new JLabel(valueText);
        value.setFont(new Font("SansSerif", Font.BOLD, 24));
        value.setForeground(GOLD);
        value.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(label);
        card.add(Box.createVerticalStrut(4));
        card.add(value);
        return card;
    }

    private JScrollPane createCharacterListPanel(List<Character> characters) {
        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(BG_MAIN);

        for (Character c : characters) {
            // BorderLayout menempatkan teks tepat di tengah vertikal tanpa terpotong
            JPanel rowCard = new JPanel(new BorderLayout());
            rowCard.setBackground(colorFor(c.getType()));
            rowCard.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14));
            rowCard.setPreferredSize(new Dimension(0, 44));
            rowCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            rowCard.setAlignmentX(Component.LEFT_ALIGNMENT);

            String info = String.format(
                "%s | %s | Lv.%d | HP:%d | ATK:%d",
                c.getName(),
                c.getType(),
                c.getLevel(),
                c.getHp(),
                c.getAtk()
            );

            JLabel infoLabel = new JLabel(info);
            infoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            infoLabel.setForeground(c.getLevel() <= DISABLED_LEVEL ? TEXT_MUTED : GOLD);

            // Menyamakan warna teks: Arjuna, Gandalf, Gatotkaca, Artemis emas; yang lain putih
            if (c.getLevel() >= HIGH_LEVEL_THRESHOLD) {
                infoLabel.setForeground(GOLD);
            } else if (c.getLevel() <= DISABLED_LEVEL) {
                infoLabel.setForeground(TEXT_MUTED);
            } else {
                infoLabel.setForeground(TEXT_LIGHT);
            }

            rowCard.add(infoLabel, BorderLayout.CENTER);
            listContainer.add(rowCard);
            listContainer.add(Box.createVerticalStrut(8));
        }

        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BG_MAIN);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private JPanel createLegendPanel() {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBackground(BG_MAIN);
        legendPanel.setPreferredSize(new Dimension(135, 0));

        JLabel title = new JLabel("Legenda Role");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        title.setForeground(TEXT_LIGHT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        legendPanel.add(title);
        legendPanel.add(Box.createVerticalStrut(10));

        for (CharacterType type : CharacterType.values()) {
            JLabel badge = new JLabel(type.name(), SwingConstants.CENTER);
            badge.setFont(new Font("Monospaced", Font.BOLD, 12));
            badge.setForeground(TEXT_LIGHT);
            badge.setBackground(colorFor(type));
            badge.setOpaque(true);
            badge.setPreferredSize(new Dimension(115, 34));
            badge.setMaximumSize(new Dimension(115, 34));
            badge.setAlignmentX(Component.CENTER_ALIGNMENT);

            legendPanel.add(badge);
            legendPanel.add(Box.createVerticalStrut(8));
        }

        return legendPanel;
    }

    private JPanel createStatusBar(int totalCount) {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(BG_MAIN);
        status.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

        JLabel left = new JLabel("Total: " + totalCount + " karakter | Siap");
        left.setFont(new Font("Monospaced", Font.PLAIN, 12));
        left.setForeground(TEXT_MUTED);

        JLabel right = new JLabel("v0.5");
        right.setFont(new Font("Monospaced", Font.PLAIN, 12));
        right.setForeground(TEXT_MUTED);

        status.add(left, BorderLayout.WEST);
        status.add(right, BorderLayout.EAST);
        return status;
    }

    private double calculateAverageLevel(List<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (Character c : characters) {
            sum += c.getLevel();
        }
        return (double) sum / characters.size();
    }

    private int calculateMaxLevel(List<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            return 0;
        }
        int max = characters.get(0).getLevel();
        for (Character c : characters) {
            if (c.getLevel() > max) {
                max = c.getLevel();
            }
        }
        return max;
    }

    private int calculateHighLevelCount(List<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (Character c : characters) {
            if (c.getLevel() >= HIGH_LEVEL_THRESHOLD) {
                count++;
            }
        }
        return count;
    }
}