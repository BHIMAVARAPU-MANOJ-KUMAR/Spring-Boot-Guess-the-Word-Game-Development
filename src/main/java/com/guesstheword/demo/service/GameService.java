package com.guesstheword.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
@Scope("prototype")
public class GameService {

    Logger logger = Logger.getLogger(getClass().getName());

    private String randomlyChosenWord = null;

    private final String[] randomWords = {"father", "mother", "sister", "string", "hello", "light", "java", "fruit", "police",
                                            "nurse", "color", "art", "sketch", "chess", "tennis"};
    private char[] allCharactersOfTheWords;
    Random random = new Random();
    Throwable throwable = new Throwable();

    public GameService() {
        if (randomWords == null) {
            throw new NullPointerException(throwable.getMessage());
        }
        if (randomWords.length == 0) {
            throw new IllegalArgumentException("There are no Words to Select from.!", throwable.getCause());
        }
        randomlyChosenWord = randomWords[random.nextInt(randomWords.length)];
        logger.info(randomlyChosenWord);
        allCharactersOfTheWords = new char[randomlyChosenWord.length()];
    }

    @Override
    public String toString() {
        String ret = "";

        for(char c: allCharactersOfTheWords){
            if (c == '\u0000'){
                ret = ret + "_";
            }
            else {
                ret = ret + c;
            }
            ret = ret + ' ';
        }
        return  ret;
    }

    public boolean addGuess(char guessedChar) {
        boolean isGuessCorrect =false;
        for (int i = 0; i < randomlyChosenWord.length() ; i++){
            if (guessedChar == randomlyChosenWord.charAt(i)){
                allCharactersOfTheWords[i]=guessedChar;
                isGuessCorrect = true;
            }
        }
        return isGuessCorrect;
    }
}
