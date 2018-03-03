package com.example.test.api;

import junit.framework.TestCase;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import com.example.PropertyStorage;
import com.example.api.CustomFieldValue;
import com.example.api.Issue;
import com.example.api.JiraApi;
import com.example.api.impl.rest.RestJiraApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 18.10.2017.
 */
public abstract class AbstractApiTest extends TestCase {
    private static final Logger log = Logger.getLogger(AbstractApiTest.class);

    private PropertiesConfiguration config;

    protected final JiraApi createRestApi() throws Exception {
        RestJiraApi restApi = new RestJiraApi(getConfig().getString("jira.host"));
        restApi.login(getLogin(), getPassword());
        return restApi;
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
    }

    @Override
    protected void setUp() throws Exception {
        setUpConfig();
    }

    private void setUpConfig() {
        String jiraVersion = System.getProperty("jira.version");
        if (jiraVersion == null) {
            throw new IllegalStateException("Define system property 'jira.version', allowed values: '6' or '7'");
        }

        System.setProperty("mail2jira.properties", "mail2jira-test-v" + jiraVersion + ".properties");
        try {
            PropertyStorage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = PropertyStorage.getConfiguration();
    }

    protected JiraApi getApiForCreateIssue() {
        return null;
    }

    protected String getSoapToken() {
        return null;
    }

    public void testCreateIssue() throws Exception {
        JiraApi api = getApiForCreateIssue();
        if (api == null) {
            log.warn("ignore testCreateIssue");
            return;
        } else {
            if (getSoapToken() == null) {
                throw new IllegalStateException("override getSoapToken() for using testCreateIssue");
            }
        }
        Issue issue = api.instantiateIssue();

        issue.setAssignee(getLogin());
        issue.setDescription("test description " + getClass().getName());
        issue.setPriority("3");
        issue.setProject(getConfig().getString("jira.project"));
        issue.setSummary("test rest summary" + getClass().getName());
        issue.setType(config.getString("jira.issueTypeForCreate"));

        issue.setCustomFieldValues(getCustomFieldValuesForCreateIssue());

        Issue createdIssue = api.createIssue(getSoapToken(), issue);

        assertEqualsIssues(issue, createdIssue, true);
        assertCustomFieldValues(issue, createdIssue);
    }

    protected CustomFieldValue[] getCustomFieldValuesForCreateIssue() {
        return null;
    }

    protected final void assertCustomFieldValues(Issue expected, Issue actual) {
        Map<String, CustomFieldValue> map = new HashMap<String, CustomFieldValue>();
        for (CustomFieldValue v : actual.getCustomFieldValues()) {
            map.put(v.getId(), v);
        }

        for (CustomFieldValue v : expected.getCustomFieldValues()) {
            assertTrue("actualIssue doesnt contain exptectedIssue.customFieldValue with id=" + v.getId(), map.containsKey(v.getId()));
        }
    }

    protected final void assertEqualsIssues(Issue exptected, Issue actual) {
        assertEqualsIssues(exptected, actual, false);
    }

    protected final void assertEqualsIssues(Issue exptected, Issue actual, boolean ignoreKeyComparison) {
        assertNotNull("Soap issue must be not null", exptected);
        assertNotNull("Rest issue must be not null", actual);

        assertEquals(exptected.getAssignee().toLowerCase(), actual.getAssignee().toLowerCase());

        assertEquals(exptected.getType(), actual.getType());
        assertEquals(exptected.getProject(), actual.getProject());
        assertEquals(exptected.getPriority(), actual.getPriority());
        //assertEquals(exptected.getDescription(), actual.getDescription()); //disabled bc different line separators cause comparison failure
        if (!ignoreKeyComparison) {
            assertEquals(exptected.getKey(), actual.getKey());
        }
        assertEquals(exptected.getSummary(), actual.getSummary());
    }

    protected final String getLogin() {
        return config.getString("jira.login");
    }

    protected final String getPassword() {
        return config.getString("jira.password");
    }

    protected final String getIssueKey() {
        return config.getString("jira.issueKey");
    }

    protected final PropertiesConfiguration getConfig() {
        return config;
    }
}
