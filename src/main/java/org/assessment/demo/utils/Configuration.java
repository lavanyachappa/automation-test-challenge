package org.assessment.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

public class Configuration implements IConf {

	public String env;
	public static String envFile;
	private static Properties properties = null;
	private String BASEURL;

	private static Configuration config = null;

	static {
		config = new Configuration();
	}

	public void init() {
		config.loadConfiguration();
	}

	private Properties getProperties() {
		return properties;
	}

	private void setEnvironment(String envValue) {
		env = envValue;
	}

	private void setConfiguration(String envFileName) {
		envFile = envFileName;
	}

	public void loadConfiguration() {
		try {

			if (env == null || env.isEmpty())
				env = "test";
			String configCommon = new File(".").getCanonicalPath() + File.separator + "config/common.properties";
			String configFile = new File(".").getCanonicalPath() + File.separator + "config/environment/" + env + "/" + envFile +".properties";
			
			FileInputStream common = new FileInputStream(configCommon);
			FileInputStream in_2 = new FileInputStream(configFile);
			
			properties = new Properties();
			properties.load(common);
			Properties envConfig = new Properties();
			envConfig.load(in_2);
		
			properties.putAll(envConfig);
			BASEURL = get("webBaseUrl");
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Problem loading configurations : " + this.getClass().getName());
		}

	}

	public Configuration() {

	}

	public static String config_get(String key) {
		try {
			if (config.getProperties() == null) {
				config.init();
			}
		} catch (Exception e) {
			throw new RuntimeException("Problem loading property for " + key);
		}
		return config.getProperties().getProperty(key);
	}

	public static void config_setExecutionEnv(String env) {
		config.setEnvironment(env);
	}

	public static void _setExecutionEnvFileName(String envFile) {
		config.setConfiguration(envFile);
	}

	public void setExecutionEnvFileName(String envFile) {
		Configuration._setExecutionEnvFileName(envFile);
	}

	public static String config_getBASEURL() {
		try {
			if (config.BASEURL == null) {
				config.init();
			}
		} catch (Exception e) {
			throw new RuntimeException("Problem loading property...");
		}
		return config.BASEURL;
	}

	public static String config_getEnv() {
		return config.env;
	}

	@Override
	public String get(String key) {
		return Configuration.config_get(key);
	}

	@Override
	public void setExecutionEnv(String env) {
		Configuration.config_setExecutionEnv(env);

	}

	@Override
	public String getBASEURL() {
		return Configuration.config_getBASEURL();
	}

	@Override
	public String getEnv() {
		return Configuration.config_getEnv();
	}

	@Override
	public String getKey(String value) {
		Set<String> keys = config.getProperties().stringPropertyNames();
		for (String key : keys) {
			if (value.contains(get(key)))
				return key;
		}
		return "";
	}

	public void setKey(String key,String value) {
		System.out.println(properties);
		properties.setProperty(key, value);
		
	}

}
