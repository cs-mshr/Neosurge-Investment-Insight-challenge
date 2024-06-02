package money.neosurge.investment.service;

import money.neosurge.investment.pojo.request.ComparsionForm;
import money.neosurge.investment.pojo.response.ComparisionResponse;
import money.neosurge.investment.pojo.response.GenAIResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public interface CompanyService {
    void saveCompanyDetails(String companyName, GenAIResponse genAIResponse);
    ComparisionResponse compareCompanies(ComparsionForm comparsionForm) throws IOException;
}
