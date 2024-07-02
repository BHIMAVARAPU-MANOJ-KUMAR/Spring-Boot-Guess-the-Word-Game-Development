package com.guesstheword.demo.utils;

import com.guesstheword.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GameUtils {
    private int maxTries = 5;

    @Autowired
    ConfigurableApplicationContext applicationContext;

    public int reduceTry(){
        maxTries = maxTries - 1;
        return maxTries;
    }

    public int getTriesRemaining(){
        return maxTries;
    }

    public void resetTries(){
        maxTries = 5;
    }

    public GameService reload(){
        GameService gameService = applicationContext.getBean(GameService.class);
        return gameService;
    }
}
