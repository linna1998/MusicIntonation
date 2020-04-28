package core;

import org.jfugue.player.Player;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class GeneralInstrument implements Instrumental {
  public static float SAMPLE_RATE = 32000f;

  // https://stackoverflow.com/questions/34611134/java-beep-sound-produce-sound-of-some-specific-frequencies
  public static void tone(double hertz, int msecs, double vol) throws LineUnavailableException {
    byte[] buf = new byte[1];
    AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open(af);
    sdl.start();
    for (int i = 0; i < msecs * 8; i++) {
      double angle = i / (SAMPLE_RATE / hertz) * 2.0 * Math.PI;
      buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
      sdl.write(buf, 0, 1);
    }
    sdl.drain();
    sdl.stop();
    sdl.close();
  }

  void tone2() {
    Player player = new Player();
    player.play("C D E F G A B");
  }

  @Override
  public void beep(double hertz) {
    try {
      tone(hertz, 5000, 10);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }

  }
}
