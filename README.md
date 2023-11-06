
## API RestAssured-TestNG-Allure-Maven framework and tests

To run local tests it needs to add following text to
**Run/Debug Configurations -> Edit configuration templates... -> TestNG -> VM options**
```
-Dserver="https://pixellu.com/"
-DseleniumGridRun=false
-Dselenide.fastSetValue=true
-DgridBrowserName=chrome
-Dselenide.timeout=20000
-Dtestng.dtd.http=true
-Ddebug=true
```

Allure reports can be checked in 
**Maven>testslotegrator>plugins>allure>allure:serve** after tests were finished.