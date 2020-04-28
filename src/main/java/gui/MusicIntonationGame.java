package gui;

import core.GeneralInstrument;
import core.Instrumental;
import core.MusicIntonationImplementation;

import javax.swing.*;

public class MusicIntonationGame extends JPanel {
  private MusicIntonationImplementation musicIntonationImplementation;
  private MusicIntonationPanel panel;

  MusicIntonationGame() {
    Instrumental instrumental = new GeneralInstrument();
    musicIntonationImplementation = new MusicIntonationImplementation(instrumental);
    panel = new MusicIntonationPanel(musicIntonationImplementation);

    add(panel);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      // add frame and set its closing operation
      JFrame frame = new JFrame("Music Intonation");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      frame.add(new MusicIntonationGame());

      //display the JFrame
      frame.pack();
      frame.setResizable(true);
      frame.setVisible(true);
    });
  }
}
