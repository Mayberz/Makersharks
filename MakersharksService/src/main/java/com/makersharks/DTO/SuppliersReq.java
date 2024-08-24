package com.makersharks.DTO;

import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersReq {
	@NotBlank(message ="Location Cant be Blank")
	@Size(min = 1,max =20,message = "input must be less than 20 characters")
	private String location;
	
	@NotNull(message = "nature of buessiness cant be null")
	private NatureOfBusiness natureOfBusiness;
	
	@NotNull(message = "it cant be null")
	private ManufacturingProcesses manufacturingProcesses;

}
