package money.neosurge.investment.controller;

import money.neosurge.investment.pojo.request.DataInsertionForm;
import money.neosurge.investment.pojo.response.DataInsertionResponse;
import money.neosurge.investment.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/insert-company-data")
    public DataInsertionResponse insertData(@RequestBody DataInsertionForm dataInsertionForm) throws IOException {
        DataInsertionResponse dataInsertionResponse = chatGPTService.HelpFromGenAI(dataInsertionForm);
        return dataInsertionResponse;
    }
}
