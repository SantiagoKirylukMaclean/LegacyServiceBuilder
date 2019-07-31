package ar.bipsucursales.legacybuilder.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.bipsucursales.legacybuilder.helper.Helper;
import ar.bipsucursales.legacybuilder.model.LegacyOrder;
import ar.bipsucursales.legacybuilder.service.BuilderService;

@Service
public class LegacyBuilderToMicroService implements BuilderService {

	@Value("${directory.settings}")
	private String directorySettings;
	
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

	@Value("${directory.mf}")
	private String directoryMf;

	@Value("${directory.request}")
	private String directoryRequest;

	@Value("${directory.response}")
	private String directoryResponse;

	@Value("${file.mule-app}")
	private String fileMuleApp;

	@Value("${file.trx}")
	private String fileTrx;

	@Value("${file.mule-deploy}")
	private String fileMuleDeploy;

	@Value("${file.gitignore}")
	private String fileGitignore;
	
	@Value("${file.log4j}")
	private String fileLog4J;

	@Value("${file.project}")
	private String fileProject;

	@Value("${file.pom}")
	private String filePom;

	@Value("${file.mule-project}")
	private String fileMuleProject;
	
	@Value("${file.wsdl}")
	private String fileWsdl;

	public void createLegacyService(LegacyOrder legacyOrder) {

		//String appName = "MuleLegacy".concat(legacyOrder.getTransaction());
		String appFolder = "/MuleLegacy".concat(legacyOrder.getTransaction().concat("/"));

		try {
			// Create App folder
			createBaseFolder(appFolder);

			// create Setting Folder and files
			createSettingsFolder(appFolder);
			Helper.getInstance().copyFiles(directoryRepository.concat(fileSettings),
					directoryTarget.concat(appFolder).concat(fileSettings));

			// Create all Folder in the src
			createSrcFolder(appFolder, directoryMainApi);
			createSrcFolder(appFolder, directoryMainApp);
			createSrcFolder(appFolder, directoryMainJava);
			createSrcFolder(appFolder, directoryMainResources);
			createSrcFolder(appFolder, directoryMf);
			createSrcFolder(appFolder, directoryRequest);
			createSrcFolder(appFolder, directoryResponse);
			createSrcFolder(appFolder, directoryMainWsdl);
			createSrcFolder(appFolder, directoryTestJava);
			createSrcFolder(appFolder, directoryTestMunit);
			createSrcFolder(appFolder, directoryTestResources);

			// Create app xml file
			createFilesAndReplace(directoryRepository.concat(directoryMainApp).concat(fileTrx), directoryTarget
					.concat(appFolder).concat(directoryMainApp).concat(legacyOrder.getTransaction().concat(".xml")),
					legacyOrder);

			// copy application.properties file
			Helper.getInstance().copyFiles(directoryRepository.concat(directoryMainApp).concat(fileMuleApp),
					directoryTarget.concat(appFolder).concat(directoryMainApp).concat(fileMuleApp));

			// create mule-deploy.properties
			createFilesAndReplace(directoryRepository.concat(directoryMainApp).concat(fileMuleDeploy),
					directoryTarget.concat(appFolder).concat(directoryMainApp).concat(fileMuleDeploy), legacyOrder);

			// Create Request files
			createRequestFiles(legacyOrder, directoryTarget.concat(appFolder.concat(directoryRequest)));

			// Create Response files
			Helper.getInstance().createJsonFiles(legacyOrder.getResponses(),
					directoryTarget.concat(appFolder.concat(directoryResponse)));
			
			// Create wsdl file
			createFilesAndReplace(directoryRepository.concat(directoryMainWsdl).concat(fileWsdl),
					directoryTarget.concat(appFolder).concat(directoryMainWsdl).concat(legacyOrder.getTransaction().concat(".wsdl")), legacyOrder);
			
			// Copy Log4J.xml
			Helper.getInstance().copyFiles(directoryRepository.concat(directoryMainResources).concat(fileLog4J),
					directoryTarget.concat(appFolder).concat(directoryMainResources).concat(fileLog4J));
			

			// copy .gitignore files
			Helper.getInstance().copyFiles(directoryRepository.concat(fileGitignore),
					directoryTarget.concat(appFolder).concat(fileGitignore));

			// Create .project file
			createFilesAndReplace(directoryRepository.concat(fileProject),
					directoryTarget.concat(appFolder).concat(fileProject), legacyOrder);
			

			// Create mule-project.xml File
			createFilesAndReplace(directoryRepository.concat(fileMuleProject),
					directoryTarget.concat(appFolder).concat(fileMuleProject), legacyOrder);

			// Create pom.xml File
			createFilesAndReplace(directoryRepository.concat(filePom),
					directoryTarget.concat(appFolder).concat(filePom), legacyOrder);


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

	private static void createFilesAndReplace(String source, String target, LegacyOrder legacyOrder)
			throws IOException {
		Helper.getInstance().createFilesAndReplace(source, target, legacyOrder.getTransaction().toUpperCase(),
				legacyOrder.getOperation().toUpperCase(), legacyOrder.getStackSize());
	}

	private static void createRequestFiles(LegacyOrder legacyOrder, String directoryRequest) throws IOException {
		Helper.getInstance().createJsonFiles(legacyOrder.getRequests(), directoryRequest);
	}
}
