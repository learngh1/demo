## Overview
This is a sample of using [Selenium](https://www.seleniumhq.org/) for integration testing.
First, [test scenario](src/test/resources/test_suite_source.html) was recorded via [Katalon Automation Recorder](https://www.katalon.com/resources-center/blog/katalon-automation-recorder) firefox plugin:
![alt text](https://i.imgur.com/Hi0DTHI.png)

Then scenario was exported to java code and edited for few adjustments due to some difference between firefox and chrome webdriver implementations.
Running junit test with visible chrome window:
![alt text](https://image.ibb.co/jX3L7S/sel.gif)
