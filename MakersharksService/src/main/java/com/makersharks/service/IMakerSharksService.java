package com.makersharks.service;


import org.springframework.data.domain.Page;

import com.makersharks.DTO.SupplierResponseDTO;
import com.makersharks.Entity.Supplier;
import com.makersharks.Entity.UserData;
import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;

public interface IMakerSharksService {
	
	public  String saveSupplier(Supplier supplier);
	
	public Page<SupplierResponseDTO> NumOfManufacturers(String loc, NatureOfBusiness nob,ManufacturingProcesses mp,int page,int size);

	
	public String addUser(UserData userData);
}
