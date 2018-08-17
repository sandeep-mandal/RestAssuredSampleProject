$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("facebook.feature");
formatter.feature({
  "line": 2,
  "name": "facebook",
  "description": "",
  "id": "facebook",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@SoapUI"
    }
  ]
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Login functionality for a social networking site",
  "description": "",
  "id": "facebook;login-functionality-for-a-social-networking-site",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 6,
  "name": "user navigates to Facebook",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I enter Username as \"\u003cusername\u003e\" and Password as \"\u003cpassword\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "make a call to soapui",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "login should be unsuccessful",
  "keyword": "Then "
});
formatter.examples({
  "line": 13,
  "name": "",
  "description": "",
  "id": "facebook;login-functionality-for-a-social-networking-site;",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 14,
      "id": "facebook;login-functionality-for-a-social-networking-site;;1"
    },
    {
      "cells": [
        "username1",
        "password1"
      ],
      "line": 15,
      "id": "facebook;login-functionality-for-a-social-networking-site;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 8340351437,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Login functionality for a social networking site",
  "description": "",
  "id": "facebook;login-functionality-for-a-social-networking-site;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@SoapUI"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "user navigates to Facebook",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I enter Username as \"username1\" and Password as \"password1\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "make a call to soapui",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "login should be unsuccessful",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinitions.user_navigates_to_Facebook()"
});
formatter.result({
  "duration": 7306260061,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "username1",
      "offset": 21
    },
    {
      "val": "password1",
      "offset": 49
    }
  ],
  "location": "StepDefinitions.I_enter_Username_as_and_Password_as(String,String)"
});
formatter.result({
  "duration": 4491608418,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinitions.make_a_call_to_soapui()"
});
formatter.result({
  "duration": 278189,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinitions.validateRelogin()"
});
formatter.result({
  "duration": 9965840563,
  "error_message": "java.lang.AssertionError: SOAP Test: FAILED: Exception:\r\n\tat org.junit.Assert.fail(Assert.java:88)\r\n\tat cucumberGradle.SoapProjects.runSoapUI(SoapProjects.java:47)\r\n\tat cucumberGradle.StepDefinitions.validateRelogin(StepDefinitions.java:107)\r\n\tat ✽.Then login should be unsuccessful(facebook.feature:11)\r\n",
  "status": "failed"
});
});