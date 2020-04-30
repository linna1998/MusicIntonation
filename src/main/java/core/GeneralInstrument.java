package core;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class GeneralInstrument implements Instrumental {
  public static float SAMPLE_RATE = 44100f;

  // https://stackoverflow.com/questions/1932490/java-generating-sound
  public static void tone(double hertz, int msecs) throws LineUnavailableException {
    byte[] buf = new byte[2];

    AudioFormat af = new AudioFormat(SAMPLE_RATE, 16, 1, true, false);
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open();
    sdl.start();
    for (int i = 0; i < msecs * SAMPLE_RATE / 1000; i++) {
      double angle = i / ((SAMPLE_RATE / hertz) / 2.0) * Math.PI;
      short a = (short) (Math.sin(angle) * 32767);
      buf[0] = (byte) (a & 0xFF);
      buf[1] = (byte) (a >> 8);
      sdl.write(buf, 0, 2);
    }
    sdl.drain();
    sdl.stop();
  }

  @Override
  public void beep(double hertz) {
    try {
      tone(hertz, 1000);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }

  }
}
