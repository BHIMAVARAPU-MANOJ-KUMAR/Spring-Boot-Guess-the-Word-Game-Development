package com.guesstheword.demo.controller;

import com.guesstheword.demo.service.GameService;
import com.guesstheword.demo.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class GameController {

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUtils gameUtils;

    public GameController(GameService gameService, GameUtils gameUtils) {
        this.gameService = gameService;
        this.gameUtils = gameUtils;
    }

    @GetMapping("/game-home")
    public String showGameHomePage(@RequestParam(value ="guessedCharacter", required = false) String guessedChar, Model model){
        logger.info("Captured Word :- " + guessedChar);
        String randomWord = gameService.toString();
        if (guessedChar != null){
            boolean isGuessCorrect = gameService.addGuess(guessedChar.charAt(0));
            randomWord = gameService.toString();
            if (isGuessCorrect == false){
                gameUtils.reduceTry();
            }
        }
        logger.info("Number of Tries Remaining: - " + gameUtils.getTriesRemaining());
        model.addAttribute("wordToDisplay", randomWord);
        model.addAttribute("triesLeft",gameUtils.getTriesRemaining());
        return "game-home-page";
    }

    @GetMapping("/reload")
    public String reloadGame(){
        gameService = gameUtils.reload();
        //reset the number of tries to its initial value
        gameUtils.resetTries();
        return "redirect:/game-home";
    }
}
