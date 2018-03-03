package com.example.api;

/**
 * Created by user on 11.10.2017.
 * Contains only methods using in MailToJira app
 */
public interface JiraApi {
    CustomFieldValue instantiateCustomFieldValue(String id, String key, Object value);
    Issue instantiateIssue();
    Issue fillIssueFromSource(Issue issue, Object source);
    Issue getIssue(String token, String issueKey) throws Exception;
    String getIssueStatus(String jiraToken, String issueKey) throws Exception;
    void addComment(String jiraToken, String issueKey, String commentBody) throws Exception;
    long findSecurityLevel(String token, String project, String secLevel) throws Exception;
    void logout(String token) throws Exception;
    void getServerInfo(String token) throws Exception;
    String login(String login, String password) throws Exception;
    void addBase64EncodedAttachmentsToIssue(String token, String issueKey, String[] fileNames, String[] base64data) throws Exception;
    Issue createIssueWithSecurityLevel(String token, Issue issue, long securityLevel) throws Exception;
    Issue createIssue(String token, Issue issue) throws Exception;

}
