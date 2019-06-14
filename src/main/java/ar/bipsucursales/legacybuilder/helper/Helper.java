package ar.bipsucursales.legacybuilder.helper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
	private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
	private static final Helper INSTANCE = new Helper();

	private Helper() {
	}

	public static Helper getInstance() {
		return INSTANCE;
	}

	public void createFolder(String name) throws IOException {

		// Create Directory
		File theDir = new File("Legacy/".concat(name));
		if (!theDir.exists()) {
			LOGGER.info("creating directory: " + theDir.toString());
			theDir.mkdirs();
		}
	}

	public void copyFiles(String source, String target) throws IOException {

		Files.copy(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
		LOGGER.info("Copy File from: " + Paths.get(source) + " to: " + Paths.get(target));
	}

	public void createFilesAndReplace(String source, String target, String appName) throws IOException {

		String fileName = source;
		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			list = stream.map(line -> line.replace("$varAppName$", appName)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Files.write(Paths.get(target), list, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
		
		LOGGER.info("Create File: "+target);

	}

}
