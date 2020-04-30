package core;

import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;

public class GeneralInstrumentTest {

  @Test
  public void tone() throws LineUnavailableException {
    GeneralInstrument.tone(440, 4000);
  }
}