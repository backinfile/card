package com.backinfile.card.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import com.backinfile.dSync.DSyncGenerator;
import com.backinfile.support.SysException;

public class GenMain {
	public static final String ProtoFilePath = "proto/server.ds";

	public static void main(String[] args) {
		DSyncGenerator generator = new DSyncGenerator();
		generator.setFileName("MessageHandler.java");
		generator.setClassName("MessageHandler");
		generator.setTargetPackagePath("com.backinfile.card.gen");
//		generator.setResourceLoaderClass(GenMain.class);
//		generator.setTemplateFileDir("/template");
//		generator.setTemplateFileName("proxy.ftl");
		generator.setDsSource(getDSSource());
		generator.genFile();
	}

	private static String getDSSource() {
		String resourcePath = GenMain.class.getClassLoader().getResource(ProtoFilePath).getPath();
		Path path = Paths.get(resourcePath.substring(1));
		try {
			List<String> lines = Files.readAllLines(path);
			var sj = new StringJoiner("\n");
			for (var line : lines) {
				sj.add(line);
			}
			return sj.toString();
		} catch (IOException e) {
			throw new SysException(e);
		}
	}
}
