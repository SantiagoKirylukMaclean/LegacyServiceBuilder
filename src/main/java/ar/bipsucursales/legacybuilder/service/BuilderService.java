package ar.bipsucursales.legacybuilder.service;

import org.springframework.stereotype.Service;

import ar.bipsucursales.legacybuilder.model.LegacyOrder;

@Service
public interface BuilderService {
	
	void createLegacyService(LegacyOrder legacyOrder);

}
