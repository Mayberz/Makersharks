package com.makersharks.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makersharks.DTO.SupplierResponseDTO;
import com.makersharks.DTO.SuppliersReq;
import com.makersharks.Entity.Supplier;
import com.makersharks.Entity.UserData;
import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.security.AuthRequest;
import com.makersharks.security.JwtService;
import com.makersharks.service.IMakerSharksService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/supplier")
public class MakerSharksController {

	@Autowired
	private IMakerSharksService service;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PreAuthorize("hasRole('ADMIN')")
	 @PostMapping("/add")
	    public ResponseEntity<String> addSupplier(@RequestBody Supplier supplier) {
	        String savedSupplier = service.saveSupplier(supplier);
	        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
	    }
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@PostMapping("/query")
	public ResponseEntity<Page<SupplierResponseDTO>> getSuppliers(
			  @Valid @RequestBody SuppliersReq req,
			  @RequestParam(defaultValue = "0")int page,
			  @RequestParam(defaultValue = "10")  int size) {
		 String location = req.getLocation();
	        NatureOfBusiness nob = req.getNatureOfBusiness();
	        ManufacturingProcesses mp = req.getManufacturingProcesses();
		Page<SupplierResponseDTO> noOfManufacturers = service.NumOfManufacturers(location, nob, mp, page, size);
		return req != null ? new ResponseEntity<>(noOfManufacturers,HttpStatus.FOUND) 
				: new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
	}
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserData data) {
		String userData = service.addUser(data);
		return new ResponseEntity<String>(userData, HttpStatus.CREATED);

	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.genToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}


}
