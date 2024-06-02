package money.neosurge.investment.pojo.request;

import lombok.Data;

import java.util.List;

@Data
public class ComparsionForm {
    private String prompt;
    private List<String> companyNames;
}
