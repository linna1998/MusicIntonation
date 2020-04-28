package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class MusicIntonationImplementation implements MusicIntonationInterface {
  public static final int NOTE_COUNT = 3;
  private List<Double> noteList = new ArrayList<>(NOTE_COUNT);
  private Instrumental instrument;
  private List<GameChangeListener> gameChangeListeners = new ArrayList<>();
  private int correctNoteIndex;
  private Note correctNote;

  public MusicIntonationImplementation(Instrumental instrument) {
    this.instrument = instrument;
    randomHertz();
  }

  @Override
  public void addGameChangeListener(GameChangeListener listener) {
    gameChangeListeners.add(listener);
  }

  @Override
  public void beepNote(int index) {
    instrument.beep(noteList.get(index));
  }

  @Override
  public void randomHertz() {
    // first, select the correct one
    int correctIndex = new Random().nextInt(Note.values().length);
    correctNote = Note.values()[correctIndex];

    // range (-delta, delta)
    double delta = Double.MAX_VALUE;
    if (correctIndex > 0) {
      delta = min(delta, correctNote.getHertz() - Note.values()[correctIndex - 1].getHertz());
    }
    if (correctIndex + 1 < Note.values().length) {
      delta = min(delta, Note.values()[correctIndex + 1].getHertz() - correctNote.getHertz());
    }
    delta = delta / 2;

    noteList.clear();
    noteList.add(correctNote.getHertz());
    for (int i = 1; i < NOTE_COUNT; i++) {
      noteList.add(correctNote.getHertz() + (Math.random() - 0.5) * delta);
    }

    Collections.shuffle(noteList);

    // set correct note Index
    for (int i = 0; i < NOTE_COUNT; i++) {
      if (noteList.get(i).equals(correctNote.getHertz())) {
        correctNoteIndex = i;
      }
    }
  }

  @Override
  public String getNoteName() {
    return correctNote.getName();
  }
}
