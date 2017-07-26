/**
 * 
 */
package com.bot.config;


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
  
  
  public void registerBot() {
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    try {
        ApiContextInitializer.init();
        telegramBotsApi.registerBot(new BotHandler());
    } catch (TelegramApiException e) {
        System.out.println("Error : -> " + e.getMessage());
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
