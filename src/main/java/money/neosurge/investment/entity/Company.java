package money.neosurge.investment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Company {

    @Id
    private UUID id;

    private String companyName;
    private String companyData;
}
