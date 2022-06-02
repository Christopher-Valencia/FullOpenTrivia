package com.example.fullopentrivia;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TriviaToken {

    private String token = null;

    public void setToken() {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("https://opentdb.com/api_token.php?command=request");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            String output = response.getEntity(String.class);
            System.out.println("Output from Server ....");
            System.out.println(output + "\n");

            String trimmedOutput = output.substring(output.lastIndexOf("\"token\":"), output.length()-2);
//            System.out.println("Trimmed output ....");
//            System.out.println(trimmedOutput + "\n");

            token = trimmedOutput.substring(trimmedOutput.lastIndexOf("\":\"")+3, trimmedOutput.length());
            System.out.println("This is your token ...");
            System.out.println(token);

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void loadSavedToken(String tok) { token = tok;}

    public String getToken() { return token; }
}
