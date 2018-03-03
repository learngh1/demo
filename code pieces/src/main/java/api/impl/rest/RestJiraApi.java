package com.example.api.impl.rest;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import org.apache.axis.encoding.Base64;
import com.example.api.CustomFieldValue;
import com.example.api.Issue;
import com.example.api.JiraApi;
import com.example.api.impl.CustomFieldValueImpl;
import com.example.api.impl.IssueImpl;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17.10.2017.
 */
public class RestJiraApi implements JiraApi {
    private final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
    private final URI jiraServerUri;
    private JiraRestClient restClient;

    public RestJiraApi(String url) {
        try {
            jiraServerUri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public CustomFieldValue instantiateCustomFieldValue(String id, String key, Object value) {
        return new CustomFieldValueImpl(id, key, value);
    }

    @Override
    public Issue instantiateIssue() {
        return new IssueImpl();
    }

    @Override
    public Issue fillIssueFromSource(Issue issue, Object source_) {
        com.atlassian.jira.rest.client.domain.Issue source = (com.atlassian.jira.rest.client.domain.Issue) source_;
        issue.setKey(source.getKey());
        issue.setSummary(source.getSummary());
        issue.setEnvironment("not supported in REST jira api");
        issue.setDescription(source.getDescription());
        issue.setProject(source.getProject().getKey());
        issue.setType("" + source.getIssueType().getId());
        issue.setAssignee(source.getAssignee().getName());
        issue.setPriority("" + source.getPriority().getId());


        List<CustomFieldValue> list = new ArrayList<CustomFieldValue>();
        Iterable<Field> fields = source.getFields();
        for (Field f : fields) {
            list.add(new CustomFieldValueImpl(f.getId(), f.getName(), f.getValue()));
        }
        issue.setCustomFieldValues(list.toArray(new CustomFieldValue[list.size()]));
        return issue;
    }

    @Override
    public Issue getIssue(String token, String issueKey) throws Exception {
        checkForLoggedIn();
        return fillIssueFromSource(new IssueImpl(), restClient.getIssueClient().getIssue(issueKey, null));
    }

    @Override
    public String getIssueStatus(String jiraToken, String issueKey) throws Exception {
        com.atlassian.jira.rest.client.domain.Issue issue = restClient.getIssueClient().getIssue(issueKey, null);
        return "" + issue.getStatus().getId();
    }

    @Override
    public void addComment(String jiraToken, String issueKey, String commentBody) throws Exception {
        restClient.getIssueClient().addComment(null, new URI(jiraServerUri.toString() + "/rest/api/2/issue/" + issueKey + "/comment"), Comment.valueOf(commentBody));
    }

    @Override
    public long findSecurityLevel(String token, String project, String secLevel) throws Exception {
        return 0;
    }

    @Override
    public void logout(String token) throws Exception {}

    @Override
    public void getServerInfo(String token) throws Exception {
        restClient.getMetadataClient().getServerInfo(null);
    }

    @Override
    public String login(String login, String password) throws Exception {
        restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, login, password);
        return "token-isnt-used-in-rest-jira";
    }

    @Override
    public void addBase64EncodedAttachmentsToIssue(String token, String issueKey, String[] fileNames, String[] base64datas) throws Exception {
        ByteArrayInputStream is;
        for (int i = 0; i < fileNames.length; i++) {
            is = new ByteArrayInputStream(Base64.decode(base64datas[i]));
            restClient.getIssueClient().addAttachment(null, new URI(jiraServerUri + String.format("/rest/api/2/issue/%s/attachments", issueKey)), is, fileNames[i]);
        }
    }

    @Override
    public Issue createIssueWithSecurityLevel(String token, Issue issue, long securityLevel) throws Exception {
        throw new UnsupportedOperationException("this method should be never invoked in rest api impl");
    }

    @Override
    public Issue createIssue(String token, Issue issue) throws Exception {
        IssueInputBuilder iib = new IssueInputBuilder(issue.getProject(), Long.valueOf(issue.getType()), issue.getSummary())
                .setAssigneeName(issue.getAssignee())
                .setDescription(issue.getDescription())
                .setPriorityId(Long.valueOf(issue.getPriority()));

        if (issue.getCustomFieldValues() != null) {
            for (CustomFieldValue v : issue.getCustomFieldValues()) {
                iib.setFieldValue(v.getId(), v.getValue());
            }
        }

        BasicIssue createdIssue = restClient.getIssueClient().createIssue(iib.build(), null);

        return getIssue(null, createdIssue.getKey());
    }

    private void checkForLoggedIn() throws IllegalStateException {
        if (restClient == null) {
            throw new IllegalStateException("invoke login() first");
        }
    }
}
