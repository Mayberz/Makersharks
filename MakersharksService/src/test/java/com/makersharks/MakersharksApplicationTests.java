package com.makersharks;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersharks.DTO.SupplierResponseDTO;
import com.makersharks.DTO.SuppliersReq;
import com.makersharks.Entity.Supplier;
import com.makersharks.Entity.UserData;
import com.makersharks.controller.MakerSharksController;
import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.security.CustomUserDetailsService;
import com.makersharks.security.JwtService;
import com.makersharks.security.Role;
import com.makersharks.service.MakerSharksServiceImpl;

@WebMvcTest(controllers = MakerSharksController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MakersharksDAOtest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	private MakerSharksServiceImpl service;
	
	@MockBean
	private CustomUserDetailsService customUserDetailsService;

	  @MockBean private JwtService jwtService;
	  
	  @MockBean private AuthenticationManager authenticationManager;
	 

	@Test
	public void testSaveSupplier() throws JsonProcessingException, Exception {

		Supplier supplier = new Supplier(1, "Respawn Industries", "http://RespawnInd.com", "India",
				NatureOfBusiness.small_scale, Set.of(ManufacturingProcesses.casting, ManufacturingProcesses.coating));
		String expectedString = "Data Added with the ID ->" + supplier.getSupplier_id();
		when(service.saveSupplier(supplier)).thenReturn(expectedString);
		mockMvc.perform(post("/api/supplier/add").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(supplier))).andExpect(status().isCreated());
	}

	 @Test
	    public void testGetSuppliers() throws Exception {
	        SuppliersReq req = new SuppliersReq("India", NatureOfBusiness.small_scale, ManufacturingProcesses.casting);
	        Pageable pageable = PageRequest.of(0, 10);
	        Page<SupplierResponseDTO> expectedPage = new PageImpl<>(List.of(new SupplierResponseDTO()), pageable, 1);

	        when(service.NumOfManufacturers("India", NatureOfBusiness.small_scale, ManufacturingProcesses.casting, 0, 10))
	                .thenReturn(expectedPage);

			mockMvc.perform(post("/api/supplier/query")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(req))
	                .param("page", "0")
	                .param("size", "10"))
	                .andExpect(status().isFound());
	    }
	 
	  @Test
	    public void testRegister() throws Exception {
	        UserData userData = new UserData(1, "john locke", "john", "fate", Role.ADMIN);
	        String expectedResponse = "User registered with ID ->" + userData.getId();
	        when(service.addUser(userData)).thenReturn(expectedResponse);

	        mockMvc.perform(post("/api/supplier/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(userData)))
	                .andExpect(status().isCreated());
	    }

}
