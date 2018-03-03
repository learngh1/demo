package com.example.test.api;

import org.codehaus.jettison.json.JSONException;
import com.example.api.JiraApi;

/**
 * Created by user on 23.10.2017.
 */
public class SoapRestComparisonTest extends AbstractSoapApiTest {

    private com.example.api.Issue soapIssue;
    private com.example.api.Issue restIssue;

    private JiraApi restApi;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        soapIssue = getSoapApi().getIssue(getSoapToken(), getIssueKey());

        restApi = createRestApi();
        restIssue = restApi.getIssue(null, getIssueKey());
    }

    /**
     * this test ignores customFieldValues and environment fields comparation
     */
    public void testRestIssueEqualsToSoapIssue() {
        assertEqualsIssues(soapIssue, restIssue);
    }

    public void testRestIssueContainsAllCustomFieldValuesFromSoapIssue() throws JSONException {
        assertCustomFieldValues(soapIssue, restIssue);
    }

    public void testGetIssueStatus() throws Exception {
        assertEquals(getSoapApi().getIssueStatus(getSoapToken(), getIssueKey()), restApi.getIssueStatus(null, getIssueKey()));
    }
}
