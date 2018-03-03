package com.example.api.impl;

import com.example.api.CustomFieldValue;
import com.example.api.Issue;

/**
 * Created by user on 20.10.2017.
 */
public class IssueImpl implements Issue {
    private String key;
    private String summary;
    private String environment;
    private String description;
    private String project;
    private String type;
    private String assignee;
    private String priority;
    private CustomFieldValue[] customFieldValues;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    @Override
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public String getPriority() {
        return priority;
    }

    @Override
    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public CustomFieldValue[] getCustomFieldValues() {
        return customFieldValues;
    }

    @Override
    public void setCustomFieldValues(CustomFieldValue[] customFieldValues) {
        this.customFieldValues = customFieldValues;
    }
}
