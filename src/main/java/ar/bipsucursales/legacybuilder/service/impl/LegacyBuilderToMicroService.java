package ar.bipsucursales.legacybuilder.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.bipsucursales.legacybuilder.helper.Helper;
import ar.bipsucursales.legacybuilder.model.LegacyOrder;
import ar.bipsucursales.legacybuilder.service.BuilderService;

@Service
public class LegacyBuilderToMicroService implements BuilderService {

	@Value("${directory.main.api}")
	private String directoryMainApi;

	@Value("${directory.main.app}")
	private String directoryMainApp;

	@Value("${directory.main.java}")
	private String directoryMainJava;

	@Value("${directory.main.resources}")
	private String directoryMainResources;

	@Value("${directory.main.wsdl}")
	private String directoryMainWsdl;

	@Value("${directory.test.java}")
	private String directoryTestJava;

	@Value("${directory.test.munit}")
	private String directoryTestMunit;

	@Value("${directory.test.resources}")
	private String directoryTestResources;
	
	@Value("${directory.repository}")
	private String directoryRepository;
	
	@Value("${file.settings}")
	private String fileSettings;
	
	@Value("${directory.target}")
	private String directoryTarget;
	
	public void createLegacyService(LegacyOrder legacyOrder) {

		String appName = "/MuleLegacy".concat(legacyOrder.getTransaction());

		try {
			// Create App folder
			createBaseFolder(appName);

			// create Setting Folder and files
			createSettingsFolder(appName);
			Helper.getInstance().copyFiles(directoryRepository.concat(fileSettings),directoryTarget.concat(appName).concat(fileSettings));

			// Create src Folder
			createSrcFolder(appName, directoryMainApi);
			createSrcFolder(appName, directoryMainApp);
			createSrcFolder(appName, directoryMainJava);
			createSrcFolder(appName, directoryMainResources);
			createSrcFolder(appName, directoryMainWsdl);
			createSrcFolder(appName, directoryTestJava);
			createSrcFolder(appName, directoryTestMunit);
			createSrcFolder(appName, directoryTestResources);
			
			

			// Create .gitignore files
			Helper.getInstance().copyFiles("Ejemplos/.gitignore", "Legacy/" + appName + "/.gitignore");

			// Create .project file
			Helper.getInstance().createFilesAndReplace("Ejemplos/.project", "Legacy/" + appName + "/.project", appName);

			// Create mule-project.xml File
			Helper.getInstance().createFilesAndReplace("Ejemplos/mule-project.xml",
					"Legacy/" + appName + "/mule-project.xml", appName);

			// Create pom.xml File
			Helper.getInstance().createFilesAndReplace("Ejemplos/pom.xml", "Legacy/" + appName + "/pom.xml", appName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createBaseFolder(String nameApp) throws IOException {
		Helper.getInstance().createFolder(nameApp);
	}

	private static void createSettingsFolder(String nameApp) throws IOException {
		Helper.getInstance().createFolder(nameApp.concat("/.settings"));
	}

	private static void createSrcFolder(String nameApp, String path) throws IOException {
		Helper.getInstance().createFolder(nameApp.concat(path));
	}


}
