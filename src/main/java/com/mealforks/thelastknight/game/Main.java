package main.java.com.mealforks.thelastknight.game;

import javax.json.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GameAudioHandler audioHandler = GameAudioHandler.getInstance();

        File backgroundMusicFile = new File("src\\main\\resources\\sound\\background.wav");

        audioHandler.playBackgroundMusic(backgroundMusicFile);

        GameFrame.getInstance().run();
    }
}