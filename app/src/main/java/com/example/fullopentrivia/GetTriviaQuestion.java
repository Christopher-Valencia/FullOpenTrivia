package com.example.fullopentrivia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetTriviaQuestion {
    public TriviaQuestion getTriviaQuestion(String token) {
        try {

            Client client = Client.create();

            WebResource webResource;
            if (token == null) {
                webResource = client.resource("https://opentdb.com/api.php?amount=1");
            }
            else {
                String urlToken = "https://opentdb.com/api.php?amount=1&token=" + token;
                System.out.println("URLToken Inside getTriviaQuestion:");
                System.out.println(urlToken);
                webResource = client.resource(urlToken);
            }

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            String output = response.getEntity(String.class);
//            System.out.println("Output from Server ....");
//            System.out.println(output + "\n");

            String trimmedOutput = output.substring(output.lastIndexOf("{\"category\""), output.length()-2);
//            System.out.println("Trimmed output ....");
//            System.out.println(trimmedOutput + "\n");

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            builder.setLenient();
            Gson gson = builder.create();

            com.example.fullopentrivia.TriviaQuestion triviaQuestion = gson.fromJson(trimmedOutput, com.example.fullopentrivia.TriviaQuestion.class);

            System.out.println("Question as GSON object ....");
            System.out.println(triviaQuestion.getQuestion() + "\n");
            return triviaQuestion;

        } catch (Exception e) {

            System.out.println(e);
            return null;
        }

    }

}