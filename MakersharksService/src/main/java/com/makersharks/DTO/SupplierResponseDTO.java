package com.makersharks.DTO;

import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDTO {
    private Integer supplierId;
    private String companyName;
    private String website;
    private String location;
    private NatureOfBusiness natureOfBusiness;
    private Set<ManufacturingProcesses> manufacturingProcesses;
}
