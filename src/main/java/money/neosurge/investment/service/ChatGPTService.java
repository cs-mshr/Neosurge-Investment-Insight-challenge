package money.neosurge.investment.service;

import money.neosurge.investment.pojo.request.AIPrompt;
import money.neosurge.investment.pojo.response.GenAIResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ChatGPTService {
    GenAIResponse HelpFromGenAI(AIPrompt dataInsertionForm) throws IOException;
}
