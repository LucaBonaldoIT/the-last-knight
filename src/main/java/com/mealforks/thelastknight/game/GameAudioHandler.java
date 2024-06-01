package main.java.com.mealforks.thelastknight.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class GameAudioHandler {
    private final ExecutorService audioThreadPool;

    private ArrayList<GameSound> _playingSounds;

    private static GameAudioHandler _intance;

    public void removePlayingSound(GameSound sound)
    {
        _playingSounds.remove(sound);
    }

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
        _playingSounds = new ArrayList<>();
    }

    public void process(GameData d) {
        GameSound sound = d.dequeueSound();

        if (sound.equals(GameSound.NONE) || _playingSounds.contains(sound))
        {
            return;
        }

        _playingSounds.add(sound);

        File audioFile = GameConstants.getSound(sound);

        if (audioFile != null) {
            try {
                audioThreadPool.submit(new AudioTask(sound, audioFile));
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
        private final GameSound sound;

        public AudioTask(GameSound sound, File audioFile) {
            this.audioFile = audioFile;
            this.sound = sound;
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

                GameAudioHandler.getInstance().removePlayingSound(sound);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}