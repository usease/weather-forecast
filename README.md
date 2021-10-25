# MX51 Tech exercise 

This project provides solution for the test technical task provided by MX51. The project is written in MVVM structural pattern using Kotlin.  
The project is highly inspired by Clean Architecture approach, guides and samples provided by Google. 

Provided that there is a recommended amount of time which should be spent on the project, not all of the requested specs have been implemented. 

## Missing specs: 

1. Jetpack Compose 

    UI has been created using traditional approach with XML. 
  
    _Reasons: I do not practical experience in working with Jetpack Compose. Time constraints_ 
  
2. Error handling 

    Error handling has not been implemented for both of the providers as both providers have different error response format. 

    _Reason: Time constraints._
  
3. Quickly failover to a different provider in case of failure 

    All the necessary classes and logic have been created both providers. But, data comes from only one provider for now. 
  
    _Reasons: Due to unimplemented error handling (See above: 2. Error handling) and time constraints._
  
  
## Areas to improve

In the project, there are serveral areas that can be improved provided that there were more time for the development.   
  
1. Access keys should not be included within the project. They should be loaded from external resource/file. 
 
    _Reason: Time constraints._ 

2. Another layer for domian model should be created. Currently, models coming from local database is being used as main application domain models which is 
not ideal solution. You can notice in the project that [Weather] object coming from database is being used everywhere. 
    
    _Reason: Time constraints._ 
  
3. Single Activity Architecture should be adopted. 
 
    Reason: Time constraints. 

4. Gradle scripts can be better organized and library imports should be refactored and optimized. 
 
    _Reason: Time constraints._ 

5. Some classes has been declared as open classes due to existing issues in Mockito library. As a temporary and fast solution, classes which are being 
mocked are declared open. The issue needs to be fixed. 
 
    _Reason: Time constraints._ 

6. Testing coverage needs to be increased. Not all methods and classes included in automated tests. 
 
    _Reason: Time constraints._ 

7. Due to certain issues (which needs some investigation), test methods have been commented out in classes:
 
    * MainActivityTest 
 
    * WeatherRepositoryTest
  
    _Reason: Time constraints._ 

 
