package core;

public interface MusicIntonationInterface {
  void addGameChangeListener(GameChangeListener listener);

  void beepNote(int index);

  void randomHertz();

  String getNoteName();

  int getLevel();

  int getHealthPoints();
}
