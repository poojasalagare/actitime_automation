package com.actitime.automation;

import org.testng.annotations.*;

public class Annotations {
    @BeforeSuite
    public void beforeSuite()
    {System.out.println("before suite method");}

    @BeforeClass
    public void beforeClass()
    {System.out.println("before class method");}

    @BeforeMethod
    public void beforeMethod()
    {System.out.println("before method");}

    @Test
    public void test1()
    {System.out.println("before test1 method");}

    @Test
    public void test2()
    {System.out.println("before test2 method");}

    @AfterClass
    public void afterClass()
    {System.out.println("after class method");}

    @AfterMethod
    public void afterMethod()
    {System.out.println("after method");}


    @AfterSuite
    public void afterSuite()
    {System.out.println("after suite method");}


    @BeforeTest
    public void beforeTest()
    {System.out.println("before test method...");}

    @AfterTest
    public void afterTest()
    {System.out.println("after test method");}






}
