package com.example.api.impl.soap;

import org.apache.log4j.Logger;
import com.example.api.CustomFieldValue;
import com.example.api.Issue;
import com.example.api.JiraApi;
import com.example.api.impl.IssueImpl;
import com.example.soap.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 11.10.2017.
 */
public class SoapJiraApi implements JiraApi {

    private static final Logger log = Logger.getLogger(SoapJiraApi.class);
    private JiraSoapService service;
    private String host;

    public SoapJiraApi(String host) {
        this.host = host;
    }

    @Override
    public Issue fillIssueFromSource(Issue issue, Object source_) {
        RemoteIssue source = (RemoteIssue) source_;
        issue.setKey(source.getKey());
        issue.setSummary(source.getSummary());
        issue.setEnvironment(source.getEnvironment());
        issue.setDescription(source.getDescription());
        issue.setProject(source.getProject());
        issue.setType(source.getType());
        issue.setAssignee(source.getAssignee());
        issue.setPriority(source.getPriority());

        List<CustomFieldValue> list = new ArrayList<CustomFieldValue>();
        for (RemoteCustomFieldValue rcfv : source.getCustomFieldValues()) {
            list.add(new SoapCustomFieldValue(rcfv));
        }
        issue.setCustomFieldValues(list.toArray(new CustomFieldValue[list.size()]));
        return issue;
    }

    public CustomFieldValue instantiateCustomFieldValue(String id, String key, Object value) {
        return new SoapCustomFieldValue(id, key, new String[]{value.toString()});
    }

    public Issue instantiateIssue() {
        return new IssueImpl();
    }

    public Issue getIssue(String token, String issueKey) throws RemoteException {
        RemoteIssue ri = getService().getIssue(token, issueKey);
        return fillIssueFromSource(new IssueImpl(), ri);
    }

    public String getIssueStatus(String token, String issueKey) throws java.rmi.RemoteException {
        RemoteIssue ri = getService().getIssue(token, issueKey);
        return ri.getStatus();
    }

    public void addComment(String token, String issueKey, String commentBody) throws java.rmi.RemoteException {
        RemoteComment comment = new RemoteComment();
        comment.setBody(commentBody);
        getService().addComment(token, issueKey, comment);
    }

    public long findSecurityLevel(String token, String project, String secLevel) throws RemoteException {
        RemoteSecurityLevel[] levels = getService().getSecurityLevels(token, project);
        long found = -1;
        for( RemoteSecurityLevel level : levels )
            if( secLevel.equalsIgnoreCase(level.getName()) )
                found = Long.parseLong(level.getId());
        return found;
    }

    public void logout(String token) throws RemoteException {
        getService().logout(token);
    }

    public void getServerInfo(String token) throws Exception {
        getService().getServerInfo(token);
    }

    public String login(String login, String password) throws RemoteException {
        return getService().login(login, password);
    }

    public void addBase64EncodedAttachmentsToIssue(String token, String key, String[] fileNames, String[] base64data) throws RemoteException {
        getService().addBase64EncodedAttachmentsToIssue(token, key, fileNames, base64data);
    }

    public Issue createIssueWithSecurityLevel(String token, Issue issue, long securityLevel) throws Exception {
        RemoteIssue createdIssue = getService().createIssueWithSecurityLevel(token, convertIssueToRemote(issue), securityLevel);
        return fillIssueFromSource(instantiateIssue(), createdIssue);
    }

    public Issue createIssue(String token, Issue issue) throws Exception {
        RemoteIssue createdIssue = getService().createIssue(token, convertIssueToRemote(issue));
        return fillIssueFromSource(instantiateIssue(), createdIssue);
    }

    private RemoteIssue convertIssueToRemote(Issue issue) {
        RemoteIssue ri = new RemoteIssue();
        ri.setProject(issue.getProject());
        ri.setType(issue.getType());
        ri.setAssignee(issue.getAssignee());

        ri.setSummary(issue.getSummary());
        ri.setPriority(issue.getPriority());
        ri.setEnvironment(issue.getEnvironment());
        ri.setDescription(issue.getDescription());

        List<RemoteCustomFieldValue> remoteCustomFieldValues = new ArrayList<RemoteCustomFieldValue>();
        if (issue.getCustomFieldValues() != null) {
            for (CustomFieldValue f : issue.getCustomFieldValues()) {
                Object value = f.getValue();
                if (value != null && value instanceof String) {
                    value = new String[]{(String) f.getValue()};
                }
                System.out.println("f.id=" + f.getId() + ", key=" + f.getKey() + ", val=" + Arrays.toString((String[]) value));
                remoteCustomFieldValues.add(new RemoteCustomFieldValue(f.getId(), f.getKey(), (String[]) value));
            }

            ri.setCustomFieldValues(remoteCustomFieldValues.toArray(new RemoteCustomFieldValue[remoteCustomFieldValues.size()]));
        }
        return ri;
    }

    private JiraSoapService getService() {
        if( service == null ) {
            try {
                JiraSoapServiceServiceLocator locator = new JiraSoapServiceServiceLocator();
                if (this.host != null) {
                    StringBuilder host = new StringBuilder(this.host);
                    if (host.length() > 0) {
                        if (host.charAt(host.length() - 1) != '/')
                            host.append('/');
                        host.append("rpc/soap/jirasoapservice-v2");
                        locator.setJirasoapserviceV2EndpointAddress(host.toString());
                    }
                }
                service = locator.getJirasoapserviceV2();
            } catch( Exception e ) {
                log.error("Failed to login into JIRA", e);
            }
        }
        return service;
    }
}