package main.java.com.mealforks.thelastknight.game;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GameFrame.getInstance().run();
    }
}