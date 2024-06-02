package money.neosurge.investment.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import money.neosurge.investment.pojo.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComparisionResponse {
    private Status status;
    private String message;
}
