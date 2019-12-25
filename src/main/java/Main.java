import screensavers.ScreenSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawPanel panel = new DrawPanel();
        frame.add(panel);
        frame.setVisible(true);
        JPanel funcPanel = new JPanel();
        JFrame frame2 = new JFrame();
        funcPanel.setSize(300, 500);
        frame2.setSize(300, 500);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setContentPane(funcPanel);
        frame2.setVisible(true);
        frame2.setTitle("Control panel");
        funcPanel.setVisible(true);
        funcPanel.setLayout(new GridLayout(8, 1));
        JButton btn;

        ScreenSaver[] templates = new ScreenSaver[2];

        HashMap<JButton, ScreenSaver> jButtonCuttingHashMap = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            btn = new JButton();
            if(i<2)
            jButtonCuttingHashMap.put(btn, templates[i]);
            btn.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.setTemplate(jButtonCuttingHashMap.get(e.getSource()));
                }
            });
            funcPanel.add(btn);
        }
    }
}
