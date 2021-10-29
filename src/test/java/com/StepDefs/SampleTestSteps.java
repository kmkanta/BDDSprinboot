package com.StepDefs;

import com.BDDCucumber.Annotations.LazyAutowired;
import com.BDDCucumber.PageObjects.SampleTestPageObjects;
import com.BDDCucumber.Testbase.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import java.util.List;
import java.util.Map;

public class SampleTestSteps extends TestBase {
    @LazyAutowired
    private SampleTestPageObjects sample;

    @Given("I login to floatcare as {string}")
    public void iLoginToFloatcareAs(String email) {
        staticWait(30);
        sample.loginAsAdmin(email);
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int time) {
        staticWait(time);
        //sample.getTableContent("Manthewpatient M");
        System.out.println("InProgress....");
        sample.getTableRows("Manthewpatient M");
    }

    @Then("I verify {string} has following values")
    public void iVerifyHasFollowingValues(DataTable dataTable) {
        /*List<List<String>> userList = dataTable.asLists(String.class);
        for(List<String> data: userList)
            System.out.println(data);*/
        List<Map<String, String>> userList = dataTable.asMaps(String.class, String.class);
        //System.out.println(userList.get(0).get("PATIENT NAME"));
        for(Map<String,String> map:userList){
            System.out.println(map.get("PATIENT NAME"));
            sample.getTableContent(map.get("PATIENT NAME"));
        }
        System.out.println("InProgress....");
    }
}
