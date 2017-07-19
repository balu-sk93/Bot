package com.bot.handler;

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


    public String getApiResponse(String queryString) {

        String queryResponse = null;

        try {

            AIConfiguration configuration = new AIConfiguration(BotConfig.CLIENT_ACCESS_TOKEN);

            AIDataService dataService = new AIDataService(configuration);

                    try {
                        AIRequest request = new AIRequest(queryString);

                        AIResponse response = dataService.request(request);

                        if (response.getStatus().getCode() == 200) {
                            queryResponse = response.getResult().getFulfillment().getSpeech();
                        } else {
                            queryResponse = response.getStatus().getErrorDetails();
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
