import javax.swing.*;
import java.awt.*;

public class UIHelper {
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(135, 206, 250));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        return button;
    }
}
