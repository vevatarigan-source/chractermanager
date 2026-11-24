package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public class AppTheme {
    // Background
    public static final Color BG_MAIN = Color.decode("#13131f");
    public static final Color BG_CARD = Color.decode("#1f1f33");

    // Text
    public static final Color TEXT_MAIN = Color.decode("#dfe6e9");
    public static final Color TEXT_MUTED = Color.decode("#a4b0be");
    public static final Color TEXT_LIGHT = Color.WHITE;
    public static final Color GOLD = Color.decode("#fdcb6e");

    // Role colors
    public static final Color WARRIOR = Color.decode("#d63031");
    public static final Color MAGE = Color.decode("#0984e3");
    public static final Color ARCHER = Color.decode("#00b894");
    public static final Color HEALER = Color.decode("#6c5ce7");

    // Font
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 28);
    public static final Font STAT_VALUE_FONT = new Font("SansSerif", Font.BOLD, 26);
    public static final Font STAT_LABEL_FONT = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font BOLD_FONT = new Font("SansSerif", Font.BOLD, 16);
    public static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 14);

    // Border
    public static final Border CARD_BORDER = BorderFactory.createEmptyBorder(12, 16, 12, 16);
    public static final Border MAIN_PADDING = BorderFactory.createEmptyBorder(16, 20, 12, 20);

    // ---------- Aturan tampilan (urusan View, bukan aturan game) ----------
    public static final int HIGH_LEVEL_THRESHOLD = 5;  // level >= ini: teks emas dan tebal
    public static final int DISABLED_LEVEL = 1;        // level <= ini: kartu dinonaktifkan

    private AppTheme() { }  // class utilitas: cegah new AppTheme()

    /** Menerjemahkan role (data Model) menjadi warna kartu (urusan View). */
    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR: return Color.decode("#d63031"); // merah
            case MAGE:    return Color.decode("#092de3"); // biru tua
            case ARCHER:  return Color.decode("#00b894"); // hijau
            case HEALER:  return Color.decode("#73e75c"); // hijau muda
            default:      return Color.GRAY;              // cadangan bila ada role baru
        }
    }


    public static Color colorFor(Enum<?> type) {
        if (type == null) {
            return BG_CARD;
        }
        switch (type.name()) {
            case "WARRIOR":
                return WARRIOR;
            case "MAGE":
                return MAGE;
            case "ARCHER":
                return ARCHER;
            case "HEALER":
                return HEALER;
            default:
                return BG_CARD;
        }
    }
}