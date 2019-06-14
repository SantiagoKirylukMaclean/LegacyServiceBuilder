package ar.bipsucursales.legacybuilder.service;

import org.springframework.stereotype.Service;

import ar.bipsucursales.legacybuilder.model.LegacyOrder;

@Service
public interface BuilderService {
	
	public void createLegacyService(LegacyOrder legacyOrder);

}
