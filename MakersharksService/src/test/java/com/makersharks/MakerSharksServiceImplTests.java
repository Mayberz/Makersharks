package com.makersharks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.makersharks.DTO.SupplierResponseDTO;
import com.makersharks.Entity.Supplier;
import com.makersharks.Entity.UserData;
import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.repository.IMakerSharksRepository;
import com.makersharks.repository.UserRepository;
import com.makersharks.service.MakerSharksServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MakerSharksServiceImplTests {

	@Mock
	private IMakerSharksRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private MakerSharksServiceImpl service;
	
	
	@Test
	public void testSaveSupplier() {
	    Supplier supplier = new Supplier(
	    		1,
	    		"Respawn Industries",
	    		"http://RespawnInd.com",
	    		"India",
	            NatureOfBusiness.small_scale, 
	            Set.of(ManufacturingProcesses.casting, ManufacturingProcesses.coating));
	    when(repository.save(any(Supplier.class))).thenReturn(supplier);
	    String result = service.saveSupplier(supplier);
	    assertEquals("Data saved with id" + supplier.getSupplier_id(), result);
	}
	
	@Test
	public void testNumOfManufacturers() {
	    Supplier supplier = new Supplier(
	    		1, 
	    		"Respawn Industries", 
	    		"http://RespawnInd.com",
	    		"India",
	            NatureOfBusiness.small_scale, 
	            Set.of(ManufacturingProcesses.casting,
	            		ManufacturingProcesses.coating));
	    Pageable pageable = PageRequest.of(0, 10);
	    Page<Supplier> suppliers = new PageImpl<>(Collections.singletonList(supplier), pageable, 1);
	    when(repository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(eq("India"), 
	            eq(NatureOfBusiness.small_scale), eq(ManufacturingProcesses.casting), eq(pageable)))
	            .thenReturn(suppliers);

	    Page<SupplierResponseDTO> result = service.NumOfManufacturers("India", NatureOfBusiness.small_scale, ManufacturingProcesses.casting, 0, 10);

	    assertEquals(1, result.getTotalElements());
	    SupplierResponseDTO responseDTO = result.getContent().get(0);
	    assertEquals(supplier.getSupplier_id(), responseDTO.getSupplierId());
	    assertEquals(supplier.getCompany_name(), responseDTO.getCompanyName());
	}
	
	@Test
	public void testAddUser() {
	    UserData userData = new UserData();
	    userData.setUsername("Sawyer");
	    userData.setPassword("Frekles");
	    when(passwordEncoder.encode("Frekles")).thenReturn("encodedPassword");
	    when(userRepository.save(any(UserData.class))).thenReturn(userData);
	    String result = service.addUser(userData);
	    assertEquals("user added to system ", result);
	    assertEquals("encodedPassword", userData.getPassword());
	}

	

}
