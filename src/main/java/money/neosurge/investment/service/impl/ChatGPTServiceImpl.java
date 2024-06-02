package money.neosurge.investment.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import money.neosurge.investment.pojo.enums.Status;
import money.neosurge.investment.pojo.request.AIPrompt;
import money.neosurge.investment.pojo.response.GenAIResponse;
import money.neosurge.investment.service.ChatGPTService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Value("${openai.api.key}")
    private String apiKey;

    @Override
    public GenAIResponse HelpFromGenAI(AIPrompt aiPrompt) throws IOException {

        String prompt = aiPrompt.getPrompt();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(360, TimeUnit.SECONDS)
                .readTimeout(360, TimeUnit.SECONDS)
                .writeTimeout(360, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        String requestBodyContent = String.format("{\n" +
                "    \"prompt\": \"\\n\\nHuman: %s \\n\\nAssistant:\"," +
                "    \"max_tokens_to_sample\": 2048,\n" +
                "    \"stop_sequences\": [],\n" +
                "    \"model\": \"%s\",\n" +
                "    \"stream\": false\n" +
                "  }",prompt, model);

        RequestBody body2 = RequestBody.create(mediaType, requestBodyContent);

        Request request = new Request.Builder()
                .url(apiURL)
                .method("POST", body2)
                .addHeader("Content-Type", "application/json")
                .addHeader("anthropic-version", "2023-06-01")
                .addHeader("x-api-key", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return GenAIResponse.builder()
                        .status(Status.SUCCESS)
                        .data(extractResponse(responseBody))
                        .build();
            } else {
                System.out.println(response.code());
                System.out.println(response.body().string());
                System.out.println(requestBodyContent);
                return GenAIResponse.builder()
                        .status(Status.FAILED)
                        .message(response.message().toString())
                        .build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return GenAIResponse.builder()
                    .status(Status.ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    private String extractResponse(String jsonData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonData);
            System.out.println(root.get("completion").asText());
            return root.get("completion").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
