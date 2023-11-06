package org.assessment.demo.utils;


import java.util.Map;

public interface IThContext {
	

	public IConf getConf();
	public void setConf(IConf cfg);
	public Utils getUtilityLayer();
	public void setUtilityLayer(Utils util);
}