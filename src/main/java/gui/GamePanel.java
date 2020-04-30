package gui;

import core.MusicIntonationInterface;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

  GamePanel(MusicIntonationInterface musicIntonation) {
    setLayout(new BorderLayout());

    ControlPanel controlPanel = new ControlPanel(musicIntonation);
    add(controlPanel, BorderLayout.NORTH);

    MusicPanel musicPanel = new MusicPanel(musicIntonation);
    add(musicPanel, BorderLayout.CENTER);

    // add the confirm button
    JButton button = new JButton("Confirm");
    button.addActionListener(e -> {
      musicIntonation.updateStatus();
      musicIntonation.randomHertz();
      controlPanel.updateStatus(musicIntonation);
      musicPanel.clearSelections();
    });
    add(button, BorderLayout.SOUTH);
  }
}
