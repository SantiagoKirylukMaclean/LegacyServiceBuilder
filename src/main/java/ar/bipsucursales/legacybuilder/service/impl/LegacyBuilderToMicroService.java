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

	@Value("${file.trx6000}")
	private String fileTrx6000;

	@Value("${file.trx32000}")
	private String fileTrx32000;

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
	
	@Value("${file.wsdl6000}")
	private String fileWsdl6000;

	@Value("${file.wsdl32000}")
	private String fileWsdl32000;

	public void createLegacyService(LegacyOrder legacyOrder) {

		//String appName = "MuleLegacy".concat(legacyOrder.getTransaction());
		String appFolder = "/MuleLegacy".concat(legacyOrder.getTransaction().concat("/"));

		try {

			// Create all Folders
			createFolders(appFolder);

			// Copy Files
			copyFiles(appFolder,legacyOrder);

			// create Files
			createFiles(appFolder,legacyOrder);






			


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createFolders(String nameApp) throws IOException {
		// Create App folder
		Helper.getInstance().createFolder(nameApp);

		// create Setting Folder and files
		Helper.getInstance().createFolder(nameApp.concat("/.settings"));

		// Create all Folder in the src
		Helper.getInstance().createFolder(nameApp.concat(directoryMainApi));
		Helper.getInstance().createFolder(nameApp.concat(directoryMainApp));
		Helper.getInstance().createFolder(nameApp.concat(directoryMainJava));
		Helper.getInstance().createFolder(nameApp.concat(directoryMainResources));
		Helper.getInstance().createFolder(nameApp.concat(directoryMf));
		Helper.getInstance().createFolder(nameApp.concat(directoryRequest));
		Helper.getInstance().createFolder(nameApp.concat(directoryResponse));
		Helper.getInstance().createFolder(nameApp.concat(directoryMainWsdl));
		Helper.getInstance().createFolder(nameApp.concat(directoryTestJava));
		Helper.getInstance().createFolder(nameApp.concat(directoryTestMunit));
		Helper.getInstance().createFolder(nameApp.concat(directoryTestResources));
	}

	private void copyFiles(String appFolder, LegacyOrder legacyOrder) throws IOException{

		// copy application.properties file
		Helper.getInstance().copyFiles(directoryRepository.concat(directoryMainApp).concat(fileMuleApp),
				directoryTarget.concat(appFolder).concat(directoryMainApp).concat(fileMuleApp));

		// Copy Log4J.xml
		Helper.getInstance().copyFiles(directoryRepository.concat(directoryMainResources).concat(fileLog4J),
				directoryTarget.concat(appFolder).concat(directoryMainResources).concat(fileLog4J));

		// copy .gitignore files
		Helper.getInstance().copyFiles(directoryRepository.concat(fileGitignore),
				directoryTarget.concat(appFolder).concat(fileGitignore));

		// Create .project file
		createFilesAndReplace(directoryRepository.concat(fileProject),
				directoryTarget.concat(appFolder).concat(fileProject), legacyOrder);

	}

	private void createFiles(String appFolder, LegacyOrder legacyOrder) throws IOException{

		// Create app.xml file
		String fileTrx = (legacyOrder.getStackSize() == 6000) ? fileTrx6000 : fileTrx32000;
		createFilesAndReplace(directoryRepository.concat(directoryMainApp).concat(fileTrx), directoryTarget
						.concat(appFolder).concat(directoryMainApp).concat(legacyOrder.getTransaction().concat(".xml")),
				legacyOrder);


		// create mule-deploy.properties
		createFilesAndReplace(directoryRepository.concat(directoryMainApp).concat(fileMuleDeploy),
				directoryTarget.concat(appFolder).concat(directoryMainApp).concat(fileMuleDeploy), legacyOrder);

		// Create wsdl file
		String fileWsdl = (legacyOrder.getStackSize() == 6000) ? fileWsdl6000 : fileWsdl32000;
		createFilesAndReplace(directoryRepository.concat(directoryMainWsdl).concat(fileWsdl),
				directoryTarget.concat(appFolder).concat(directoryMainWsdl).concat(legacyOrder.getTransaction().concat(".wsdl")), legacyOrder);

		// Create mule-project.xml File
		createFilesAndReplace(directoryRepository.concat(fileMuleProject),
				directoryTarget.concat(appFolder).concat(fileMuleProject), legacyOrder);

		// Create pom.xml File
		createFilesAndReplace(directoryRepository.concat(filePom),
				directoryTarget.concat(appFolder).concat(filePom), legacyOrder);

		// Create Request files
		Helper.getInstance().createJsonFiles(legacyOrder.getRequests(), directoryTarget.concat(appFolder.concat(directoryRequest)));


		// Create Response files
		Helper.getInstance().createJsonFiles(legacyOrder.getResponses(),
				directoryTarget.concat(appFolder.concat(directoryResponse)));

	}
	private static void createFilesAndReplace(String source, String target, LegacyOrder legacyOrder)
			throws IOException {
		Helper.getInstance().createFilesAndReplace(source, target, legacyOrder.getTransaction().toUpperCase(),
				legacyOrder.getOperation().toUpperCase(), legacyOrder.getStackSize());
	}


}
