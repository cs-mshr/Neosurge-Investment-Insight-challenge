package money.neosurge.investment.service.impl;

import money.neosurge.investment.entity.Company;
import money.neosurge.investment.pojo.enums.Status;
import money.neosurge.investment.pojo.request.AIPrompt;
import money.neosurge.investment.pojo.request.ComparsionForm;
import money.neosurge.investment.pojo.response.ComparisionResponse;
import money.neosurge.investment.pojo.response.GenAIResponse;
import money.neosurge.investment.repository.CompanyRepository;
import money.neosurge.investment.service.ChatGPTService;
import money.neosurge.investment.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private ChatGPTService chatGPTService;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void saveCompanyDetails(String companyName, GenAIResponse genAIResponse) {
        System.out.println(genAIResponse.getData());
        companyRepository.save(
            Company.builder()
                    .id(UUID.randomUUID())
                    .companyName(companyName)
                    .companyData(genAIResponse.getData())
                    .build()
        );
    }

    @Override
    public ComparisionResponse compareCompanies(ComparsionForm comparsionForm) throws IOException {
        List<String> companies = comparsionForm.getCompanyNames();

        Optional<Company> c1 = companyRepository.getByCompanyName(companies.get(0));
        Optional<Company> c2 = companyRepository.getByCompanyName(companies.get(1));

        if(c1.isPresent() && c2.isPresent()) {

            Company company = c1.get();
            Company company2 = c2.get();

            String data = "FIRST COMPANY NAME " + company.getCompanyName();
            data += company.getCompanyData();

            data += "SECOND COMPANY NAME " + company2.getCompanyName();
            data += company2.getCompanyData();

            return ComparisionResponse.builder()
                    .status(Status.SUCCESS)
                    .message(
                            chatGPTService.HelpFromGenAI(
                                    AIPrompt.builder()
                                            .prompt(comparsionForm.getPrompt() + data )
                                            .build()
                            ).getData()
                    )
                    .build();
        }

        return ComparisionResponse.builder()
                .status(Status.ERROR)
                .message("Company not found in database")
                .build();
    }
}
