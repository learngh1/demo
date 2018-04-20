## Overview
This is a sample of using [Selenium](https://www.seleniumhq.org/) for integration testing.
First, [test scenario](src/test/resources/test_suite_source.html) was recorded via [Katalon Automation Recorder](https://www.katalon.com/resources-center/blog/katalon-automation-recorder) firefox plugin:
![alt text](https://i.imgur.com/Hi0DTHI.png)

Then scenario was exported to java code and edited for few adjustments due to some difference between firefox and chrome webdriver implementations.

Running [junit test](src/test/java/integration/selenium/shipping_package_dimensions/SPDEditTestSuite.java) with visible chrome window:

[this gif has too big size, click to open](https://image.ibb.co/jX3L7S/sel.gif)
