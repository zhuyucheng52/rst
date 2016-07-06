package com.echo.rst.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by echo on 16-7-6.
 */
public class ErrorCodesResolver {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Properties properties = new Properties();
	private final String fileName = "ErrorCodes.properties";


	public ErrorCodesResolver() {
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String fileWithPath = path + fileName;
		FileInputStream fis = null;
		try {
			logger.info("load file {}", fileWithPath);
			File file = new File(fileWithPath);
			fis = new FileInputStream(file);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			logger.warn("load file {} failure", fileWithPath, e);
		} catch (IOException e) {
			logger.warn("load file {} failure", fileWithPath, e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
					fis = null;
				}
			} catch (Exception e) {
				logger.warn("close file input stream to file={} failure", fileWithPath, e);
			}
		}
	}

	public String getValue(String key) {
		return (String) properties.get(key);
	}
}
