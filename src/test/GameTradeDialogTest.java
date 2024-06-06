package test.java.com.mealforks.thelastknight.game;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTradeDialogTest {

    private GameTradeDialog gameTradeDialog;
    private GameData gameData;

    @BeforeEach
    void setUp() {
        // Creiamo un oggetto GameTradeDialog di prova con un ID e un testo
        String id = "TRADE_DIALOG_1";
        String text = "Welcome to the trading system. Do you want to proceed?";
        gameTradeDialog = new GameTradeDialog(id, text);

        // Creiamo un oggetto GameData di prova
        GamePlayer player = new GamePlayer(1, 1);
        GameData gameState = new GameData();
        gameState.setGameState(GameState.TRADE);
        gameData = new GameData();
    }
}
