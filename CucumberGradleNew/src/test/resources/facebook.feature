@SoapUI
Feature: facebook

Scenario Outline: Login functionality for a social networking site

Given user navigates to Facebook

When I enter Username as "<username>" and Password as "<password>"
And make a call to soapui

Then login should be unsuccessful

Examples:
| username  | password  | 
| username1 | password1 |