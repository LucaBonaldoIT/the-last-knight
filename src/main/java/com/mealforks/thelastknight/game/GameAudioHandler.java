package main.java.com.mealforks.thelastknight.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class GameAudioHandler {
    private final ExecutorService audioThreadPool;

    private static GameAudioHandler _intance;
    public static GameAudioHandler getInstance()
    {
        if (_intance == null)
        {
            _intance = new GameAudioHandler(2);
        }
        return _intance;
    }

    public GameAudioHandler(int poolSize) {
        audioThreadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void process(GameData d) {
        GameSound sound = d.dequeueSound();

        if (sound.equals(GameSound.NONE))
        {
            return;
        }

        File audioFile = GameConstants.getSound(sound);

        if (audioFile != null) {
            try {
                audioThreadPool.submit(new AudioTask(audioFile));
            } catch (RejectedExecutionException e) {
                d.addSoundToBuffer(sound);
            }
        }
    }

    public void shutdown() {
        audioThreadPool.shutdown();
    }

    private static class AudioTask implements Runnable {
        private final File audioFile;

        public AudioTask(File audioFile) {
            this.audioFile = audioFile;
        }

        @Override
        public void run() {
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
                Clip audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);

                // Add a listener to release the clip once it's done playing
                audioClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        audioClip.close();
                    }
                });

                audioClip.start();

                while (!audioClip.isRunning()) {
                    Thread.sleep(100);
                }
                // Keep the thread alive while the audio is playing
                while (audioClip.isRunning()) {
                    Thread.sleep(100);
                }

            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}