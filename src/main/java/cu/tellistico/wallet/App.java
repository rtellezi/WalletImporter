package cu.tellistico.wallet;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Configuration");
        frame.setContentPane(new Configuration().getPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
