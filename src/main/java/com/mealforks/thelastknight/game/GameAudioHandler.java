package main.java.com.mealforks.thelastknight.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * The GameAudioHandler class manages audio playback in the game.
 * It handles the queuing and processing of audio files.
 */
public class GameAudioHandler {
    private final ExecutorService audioThreadPool;

    private ArrayList<GameSound> _playingSounds;

    private static GameAudioHandler _instance;

    /**
     * Constructor for the GameAudioHandler class.
     *
     * @param poolSize The size of the thread pool for audio playback.
     */
    public GameAudioHandler(int poolSize) {
        audioThreadPool = Executors.newFixedThreadPool(poolSize);
        _playingSounds = new ArrayList<>();
    }

    /**
     * Removes a playing sound from the list of currently playing sounds.
     *
     * @param sound The sound to be removed.
     */
    public void removePlayingSound(GameSound sound) {
        _playingSounds.remove(sound);
    }

    /**
     * Retrieves the singleton instance of the GameAudioHandler class.
     *
     * @return The singleton instance of GameAudioHandler.
     */
    public static GameAudioHandler getInstance() {
        if (_instance == null) {
            _instance = new GameAudioHandler(2);
        }
        return _instance;
    }

    /**
     * Processes the game data, including playing queued sounds.
     *
     * @param d The game data to be processed.
     */
    public void process(GameData d) {
        GameSound sound = d.dequeueSound();

        if (sound.equals(GameSound.NONE) || _playingSounds.contains(sound)) {
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

    /**
     * Shuts down the audio thread pool.
     */
    public void shutdown() {
        audioThreadPool.shutdown();
    }

    /**
     * A task for playing audio files asynchronously.
     */
    private static class AudioTask implements Runnable {
        private final File audioFile;
        private final GameSound sound;

        /**
         * Constructor for the AudioTask class.
         *
         * @param sound     The sound to be played.
         * @param audioFile The audio file to be played.
         */
        public AudioTask(GameSound sound, File audioFile) {
            this.audioFile = audioFile;
            this.sound = sound;
        }

        /**
         * Runs the audio playback task.
         */
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