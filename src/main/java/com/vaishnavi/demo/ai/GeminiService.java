package com.vaishnavi.demo.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String analyzeComplaint(String complaint) {

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        """
                        Analyze the complaint and reply in exactly this format:

                        Category: <category>
                        Priority: <High/Medium/Low>
                        Department: <department>

                        Complaint:
                        """ + complaint,
                        null);

        return response.text();
    }
}