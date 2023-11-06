package org.assessment.demo.common;
import org.assessment.demo.utils.ExtentTestManager;
import org.assessment.demo.utils.IConf;
import org.assessment.demo.utils.Configuration;
import org.assessment.demo.utils.IThContext;
import org.assessment.demo.utils.ThContextData;
import org.assessment.demo.utils.Utils;

import com.aventstack.extentreports.ExtentTest;


// These interfaces will be used by Modules
public abstract class BaseUtilityLayer  {


	public IConf CONF = new Configuration();
	public static Utils UTILS = new Utils();
	public static IThContext tContext = new ThContextData();
	public ExtentTest LOGGER = ExtentTestManager.getTest();
	

}
   