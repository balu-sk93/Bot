package com.bot.handler;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bot.config.BotConfig;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

/**
 * @author Balu S
 * @since Jul 18, 2017
**/

public class ApiHandler {
    
    private static Logger logger = LogManager.getLogger(ApiHandler.class);
  
    public String getApiResponse(String queryString) {

        String queryResponse = null;

        try {

            AIConfiguration configuration = new AIConfiguration(BotConfig.CLIENT_ACCESS_TOKEN);

            AIDataService dataService = new AIDataService(configuration);

                    try {
                        AIRequest request = new AIRequest(queryString);

                        AIResponse response = dataService.request(request);
                        
                        logger.info("API Response : "+response.toString());

                        if (response.getStatus().getCode() == 200) {
                            queryResponse = response.getResult().getFulfillment().getSpeech();
                        } else {
                            queryResponse = response.getStatus().getErrorDetails();
                        }
                        
                        if (queryResponse == null || queryResponse.equalsIgnoreCase("")) {
                          queryResponse = "Sorry No Response From AI ";
                        }
                          
                          
                          
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

        } catch (Exception e) {
            e.printStackTrace();
            queryResponse = "Error Occured!";
        }

        return queryResponse;
    
  }
  
  
}
