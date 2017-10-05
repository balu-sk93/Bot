package com.bot.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.bot.config.BotConfig;

/**
 * @author Balu S
 * @since Jul 18, 2017
**/

public class BotHandler extends TelegramLongPollingBot {

  
  private static Logger logger = LogManager.getLogger(BotHandler.class);

  public String getBotUsername() {
    return BotConfig.USERNAME;
  }

  public String getBotToken() {
    return BotConfig.BOTTOKEN;
  }

  public void onUpdateReceived(Update update) {
    
    if (update.hasMessage()) {
      Message message = update.getMessage();
      String inputText;
      SendMessage sendMessageRequest = new SendMessage();
      String outputText = null;
      logger.info("Chat ID : "+message.getChatId());
      try {
        if(message.hasText()){
          sendMessageRequest.setChatId(message.getChatId().toString());
          sendMessageRequest.enableHtml(true);
          inputText = message.getText();
          logger.info("Input : "+inputText);
          outputText = new ApiHandler().getApiResponse(inputText);
          logger.info("Output : "+outputText);
          sendMessageRequest.setText(outputText);
          sendMessage(sendMessageRequest);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
