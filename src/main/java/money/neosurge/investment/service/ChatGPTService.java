package money.neosurge.investment.service;

import money.neosurge.investment.pojo.request.DataInsertionForm;
import money.neosurge.investment.pojo.response.DataInsertionResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ChatGPTService {
    DataInsertionResponse HelpFromGenAI(DataInsertionForm dataInsertionForm) throws IOException;
}
