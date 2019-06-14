package ar.bipsucursales.legacybuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.bipsucursales.legacybuilder.model.LegacyOrder;
import ar.bipsucursales.legacybuilder.service.BuilderService;



@RestController
public class LegacyServiceBuilderController {
	
	@Autowired
	private BuilderService builderService;
	
	@RequestMapping("/api/v1.0/create/legacy")
	public void createLegacyFilesToMicroServices(@RequestBody LegacyOrder legacyOrder) {
		builderService.createLegacyService(legacyOrder);

	}

}
