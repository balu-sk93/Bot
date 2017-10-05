/**
 * 
 */
package com.bot.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.bot.handler.BotHandler;


/**
 * @author Balu S
 * @version 1.0.0.0
 * @since May 24, 2017
**/
public class Adapter {
  
  
  private static Logger logger = LogManager.getLogger(Adapter.class);
  
  public void registerBot() {
    
    TelegramBotsApi telegramBotsApi = null;
    
    try {

        logger.info("Starting BOT ==>>> ");
        ApiContextInitializer.init();
        telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new BotHandler());
    } catch (TelegramApiException e) {
        logger.error("Error : -> " + e.getMessage());
        e.printStackTrace();
    }
  }

  public static void main(String[] args) {
      try {
        new Adapter().registerBot();
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
  
}
