package com.example.test.api;

import com.example.api.CustomFieldValue;
import com.example.api.JiraApi;
import com.example.api.impl.CustomFieldValueImpl;

/**
 * Created by user on 18.10.2017.
 */
public class SoapApiTest extends AbstractSoapApiTest {

    @Override
    protected JiraApi getApiForCreateIssue() {
        return getSoapApi();
    }

    @Override
    protected CustomFieldValue[] getCustomFieldValuesForCreateIssue() {
        return new CustomFieldValue[]{
                new CustomFieldValueImpl("customfield_10091", "", "trunk"), //Planned version
                new CustomFieldValueImpl("customfield_10130", "", "Общее"), //Target object
                new CustomFieldValueImpl("customfield_10120", "", "Glitch") //Severity
        };
    }
}
