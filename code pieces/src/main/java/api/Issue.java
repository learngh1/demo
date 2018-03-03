package com.example.api;

/**
 * Created by user on 11.10.2017.
 */
public interface Issue {
    String getKey();
    String getSummary();
    String getEnvironment();
    String getDescription();
    String getProject();
    String getType();
    String getAssignee();
    String getPriority();
    CustomFieldValue[] getCustomFieldValues();

    void setKey(String val);
    void setSummary(String val);
    void setEnvironment(String val);
    void setDescription(String val);
    void setProject(String val);
    void setType(String val);
    void setAssignee(String val);
    void setPriority(String val);
    void setCustomFieldValues(CustomFieldValue[] val);
}
