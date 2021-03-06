package lv.ctco.cukesrest.loadrunner.junit;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.Filter;
import lv.ctco.cukesrest.loadrunner.LoadRunnerFilter;
import lv.ctco.cukesrest.internal.CukesInternalException;
import cucumber.runtime.Runtime;
import cucumber.runtime.junit.FeatureRunner;
import cucumber.runtime.junit.JUnitReporter;
import cucumber.runtime.model.CucumberFeature;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

class LoadRunnerFeature extends FeatureRunner {

    public static final String LOADRUNNER_OUTPUT_DIR = "target/loadrunner_output";

    private Logger logger = Logger.getLogger(LoadRunnerFeature.class.getName());

    private final String featureName;
    private final LoadRunnerFilter filter;

    public LoadRunnerFeature(CucumberFeature cucumberFeature, Runtime runtime, JUnitReporter jUnitReporter, LoadRunnerFilter filter) throws InitializationError {
        super(cucumberFeature, runtime, jUnitReporter);
        this.filter = filter;
        this.featureName = cucumberFeature.getGherkinFeature().getName();
    }

    @Override
    public void run(RunNotifier notifier) {
        RestAssured.filters(filter);
        filter.createLoadRunnerAction();
        super.run(notifier);

        try {
            new File(LOADRUNNER_OUTPUT_DIR).mkdirs();
            String fileName = featureName + ".vugen";
            File file = new File(LOADRUNNER_OUTPUT_DIR +File.separator+fileName);
            OutputStream out = new FileOutputStream(file);
            filter.dump(out);
            out.close();

            logger.info(file.getAbsolutePath());

            ArrayList<Filter> filtersCopy = new ArrayList<Filter>(RestAssured.filters());
            filtersCopy.remove(filter);
            RestAssured.replaceFiltersWith(filtersCopy);
        } catch (Exception e) {
            throw new CukesInternalException(e);
        }
    }


}
