package com.capital.one.testsuites;

import org.junit.runner.*;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features", glue="com.capital.one.testsuites")

public class TestRunner {
	
	

}
