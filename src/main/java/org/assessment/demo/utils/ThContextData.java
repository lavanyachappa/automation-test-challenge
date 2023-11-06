package org.assessment.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class ThContextData implements IThContext {

	private static ThreadLocal<Utils> utility = new ThreadLocal<Utils>();
	private static ThreadLocal<IConf> config = new ThreadLocal<IConf>();

	@Override
	public IConf getConf() {
		return config.get();
	}

	@Override
	public Utils getUtilityLayer() {
		return utility.get();
	}

	@Override
	public void setConf(IConf cfg) {
		config.set(cfg);

	}

	@Override
	public void setUtilityLayer(Utils util) {
		utility.set(util);

	}

}
