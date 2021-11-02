package pl.justmedia.trexframeworkgame.objectgame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sound {
    private Clip audioClip;
    boolean playCompleted;
    private String path;

    public Sound(String path) {

        File audioFile = new File(path);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.addLineListener(new LineListener() {

                @Override
                public void update(final LineEvent event) {
                    if (event.getType().equals(LineEvent.Type.STOP)) {
                        audioClip.setFramePosition(0);
                    }
                }
            });
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    public void play(int level) {


        FloatControl volume = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        if (volume != null) {
            volume.setValue(level*(6/100));
        }
        audioClip.start();



    }

    public void loop(int level) {
        FloatControl volume = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        if (volume != null) {
            volume.setValue(level*(6/100));
        }
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        audioClip.stop();

    }

}