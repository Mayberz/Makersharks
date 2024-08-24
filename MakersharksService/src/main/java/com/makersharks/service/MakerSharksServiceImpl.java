package com.makersharks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.makersharks.DTO.SupplierResponseDTO;
import com.makersharks.Entity.Supplier;
import com.makersharks.Entity.UserData;
import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.repository.IMakerSharksRepository;
import com.makersharks.repository.UserRepository;

@Service
public final class MakerSharksServiceImpl implements IMakerSharksService {

	@Autowired
	IMakerSharksRepository repository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String saveSupplier(Supplier supplier) {

		repository.save(supplier);
		return "Data saved with id" + supplier.getSupplier_id();
	}

	@Override
	public Page<SupplierResponseDTO> NumOfManufacturers(String loc, NatureOfBusiness nob, ManufacturingProcesses mp, int page,
			int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Supplier> suppliers = repository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(loc, nob, mp, pageable);
		 return suppliers.map(supplier -> new SupplierResponseDTO(
		            supplier.getSupplier_id(),
		            supplier.getCompany_name(),
		            supplier.getWebsite(),
		            supplier.getLocation(),
		            supplier.getNatureOfBusiness(),
		            supplier.getManufacturingProcesses()
		        ));
	}
	

	@Override
	public String addUser(UserData userData) {
		userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		userRepository.save(userData);
		return "user added to system ";
	}

}
