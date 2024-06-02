package money.neosurge.investment.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import money.neosurge.investment.pojo.enums.Status;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GenAIResponse {
    private Status status;
    private String message;
    private String data;
}
