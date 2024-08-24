package com.makersharks.Entity;

import java.util.Set;

import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer supplier_id;
	private String company_name;
	private String website;
	private String location;
	
	@Enumerated(EnumType.STRING)
	private NatureOfBusiness natureOfBusiness;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "MANUFACTURING_PROCESSES", joinColumns = @JoinColumn(name="supplier_id"))
	@Enumerated(EnumType.STRING)
	private Set<ManufacturingProcesses> manufacturingProcesses;

}
