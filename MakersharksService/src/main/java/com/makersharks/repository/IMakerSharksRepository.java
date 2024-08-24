package com.makersharks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.makersharks.Entity.Supplier;
import com.makersharks.model.ManufacturingProcesses;
import com.makersharks.model.NatureOfBusiness;

public interface IMakerSharksRepository extends JpaRepository<Supplier, Integer> {
    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(
        String location, NatureOfBusiness natureOfBusiness, ManufacturingProcesses manufacturingProcess, Pageable pageable);
}
