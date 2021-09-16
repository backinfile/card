package com.backinfile.card.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import com.backinfile.dSync.DSyncGenerator;
import com.backinfile.support.SysException;

public class GenMessageHandler {
	public static final String ServerProtoFilePath = "proto/server.ds";
	public static final String GenClassName = "GameMessageHandler";

	public static void main(String[] args) {
		DSyncGenerator generator = new DSyncGenerator();
		generator.setFileName(GenClassName + ".java");
		generator.setClassName(GenClassName);
		generator.setTargetPackagePath("com.backinfile.card.gen");
		generator.setResourceLoaderClass(GenMessageHandler.class);
		generator.setTemplateFileDir("/template");
		generator.setTemplateFileName("proxy.ftl");
		generator.setDsSource(getDSSource(ServerProtoFilePath));
		generator.genFile();
	}

	private static String getDSSource(String filePath) {
		String resourcePath = GenMessageHandler.class.getClassLoader().getResource(filePath).getPath();
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
