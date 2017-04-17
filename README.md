# KronosTest
This is a Java test harness for Kronos

##Dependencies:
It is configured to use [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/)

You need to have Google Chrome installed and your PATH variable set to include the chromedriver
executable location

This has also been tested with Firefox via the [GeckoDriver](https://github.com/mozilla/geckodriver/releases)
the same PATH configuration applies

### Gradle
All Java dependencies will be automatically downloaded via Gradle, a working gradle wrapper has
been inclueded with the repository. Running this command will get everything setup:
```
    ./gradlew build
```

##Configuration
The single Cucumber scenario will not run out of the box as passwords have not been committed to the repo.

Rename src/test/resources/config.properties.example to config.properties and set the fields
within the file.

## Test Execution
The scenario can be executed in several ways:
1. Via your IDE (intellij):
    1. Right click on sample.feature and Run the feature file (requires Cucumber plugin)
    2. Right click on the scenario and run just that scenario (requires Cucumber plugin)
    3. Right click "RunCukesTest.java" and Run
2. Via gradle:
    1. ```gradlew test```
    
## Output
Test output will be to the build folder.

A HTML report will be present in build\reports\tests\test\index.html if running via gradle

Or build\cucumber-html-reports\index.html if running via the IDE

##Run against firefox
Change the following lines in KronosStepDefinitions:
```
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("acceptInsecureCerts", true);
        driver = new ChromeDriver(caps);
```

to be:
```
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("acceptInsecureCerts", true);
        driver = new FirefoxDriver(caps);
```

