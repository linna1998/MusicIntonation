package gui;

import core.GameChangeListener;
import core.MusicIntonationInterface;

import javax.swing.*;
import java.awt.*;

public class MusicIntonationPanel extends JPanel implements GameChangeListener {

  private ControlPanel controlPanel;
  private MusicPanel musicPanel;

  MusicIntonationPanel(MusicIntonationInterface musicIntonation) {
    musicIntonation.addGameChangeListener(this);

    setLayout(new BorderLayout());

    controlPanel = new ControlPanel(musicIntonation);
    add(controlPanel, BorderLayout.NORTH);

    musicPanel = new MusicPanel(musicIntonation);
    add(musicPanel, BorderLayout.CENTER);

    // add the confirm button
    JButton button = new JButton("Confirm");
    button.addActionListener(e -> {
      musicIntonation.randomHertz();
    });
    add(button, BorderLayout.SOUTH);
  }

}
