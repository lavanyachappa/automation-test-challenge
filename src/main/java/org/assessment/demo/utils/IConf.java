package org.assessment.demo.utils;


public interface IConf {
	public String get(String key);
	public void setExecutionEnv(String env);
	public void setExecutionEnvFileName(String env);
	public String getBASEURL();
	public String getEnv();
	public void init();
	public String getKey(String value);
	public void setKey(String key, String value);

}

