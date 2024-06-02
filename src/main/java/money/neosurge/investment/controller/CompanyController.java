package money.neosurge.investment.controller;

import money.neosurge.investment.pojo.request.CompanyDataInsertionForm;
import money.neosurge.investment.pojo.request.ComparsionForm;
import money.neosurge.investment.pojo.request.AIPrompt;
import money.neosurge.investment.pojo.response.ComparisionResponse;
import money.neosurge.investment.pojo.response.GenAIResponse;
import money.neosurge.investment.service.ChatGPTService;
import money.neosurge.investment.service.CompanyService;
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

    @Autowired
    private CompanyService companyService;

    @PostMapping("/insert-company-data")
    public GenAIResponse insertData(@RequestBody CompanyDataInsertionForm companyDataInsertionForm) throws IOException {
        GenAIResponse dataInsertionResponse = chatGPTService.HelpFromGenAI(
                AIPrompt.builder()
                        .prompt(companyDataInsertionForm.getData() + companyDataInsertionForm.getPrompt())
                        .build()
        );
        companyService.saveCompanyDetails(companyDataInsertionForm.getCompanyName(),dataInsertionResponse);
        return dataInsertionResponse;
    }

    @PostMapping("/compare")
    public ComparisionResponse compare(@RequestBody ComparsionForm comparsionForm) throws IOException {
        return companyService.compareCompanies(comparsionForm);
    }
}
