package gui;

import core.GameChangeListener;
import core.MusicIntonationInterface;

import javax.swing.*;

import static core.MusicIntonationImplementation.NOTE_COUNT;

public class MusicIntonationPanel extends JPanel implements GameChangeListener {

  private JTextArea noteName = new JTextArea("");

  MusicIntonationPanel(MusicIntonationInterface musicIntonation) {
    musicIntonation.addGameChangeListener(this);

    noteName.setText(musicIntonation.getNoteName());
    add(noteName);

    for (int i = 0; i < NOTE_COUNT; i++) {
      JButton button = new JButton();
      int finalI = i;
      button.addActionListener(e -> musicIntonation.beepNote(finalI));
      add(button);
    }

    // add the restart button
    JButton button = new JButton("Restart");
    button.addActionListener(e -> {
      musicIntonation.randomHertz();
      noteName.setText(musicIntonation.getNoteName());
    });
    add(button);
  }

}
