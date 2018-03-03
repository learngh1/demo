package com.example.test.api;

import com.example.api.JiraApi;
import com.example.api.impl.soap.SoapJiraApi;

/**
 * Created by user on 23.10.2017.
 */
public abstract class AbstractSoapApiTest extends AbstractApiTest {
    private JiraApi soapApi;
    private String soapToken;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        String login = getLogin();
        String pass = getPassword();

        soapApi = new SoapJiraApi(null);
        soapToken = soapApi.login(login, pass);

    }

    public JiraApi getSoapApi() {
        return soapApi;
    }

    public String getSoapToken() {
        return soapToken;
    }
}
