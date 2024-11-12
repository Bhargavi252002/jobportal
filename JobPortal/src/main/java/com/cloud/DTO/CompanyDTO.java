package com.cloud.DTO;
import com.cloud.entity.Jobs;
import com.cloud.util.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO extends ResponseDTO {

    private UUID companyId;
    private String companyName;
    private String companyAddress;
    private List<Jobs> jobs;
    private Boolean isCompanyDeleted=false;
}
