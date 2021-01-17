package com.zendesk.search.domain;

import java.util.Arrays;

public class Ticket {
    private String _id;
    private String url;
    private String external_id;
    private String created_at;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private String submitter_id;
    private String assignee_id;
    private String organization_id;
    private String[] tags;
    private String has_incidents;
    private String due_at;
    private String via;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmitter_id() {
        return submitter_id;
    }

    public void setSubmitter_id(String submitter_id) {
        this.submitter_id = submitter_id;
    }

    public String getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(String assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String isHas_incidents() {
        return has_incidents;
    }

    public void setHas_incidents(String has_incidents) {
        this.has_incidents = has_incidents;
    }

    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-20s%s%n", "_id", _id))
                .append(String.format("%-20s%s%n", "url", url))
                .append(String.format("%-20s%s%n", "external_id", external_id))
                .append(String.format("%-20s%s%n", "created_at", created_at))
                .append(String.format("%-20s%s%n", "type", type))
                .append(String.format("%-20s%s%n", "subject", subject))
                .append(String.format("%-20s%s%n", "description", description))
                .append(String.format("%-20s%s%n", "priority", priority))
                .append(String.format("%-20s%s%n", "status", status))
                .append(String.format("%-20s%s%n", "submitter_id", submitter_id))
                .append(String.format("%-20s%s%n", "assignee_id", assignee_id))
                .append(String.format("%-20s%s%n", "organization_id", organization_id))
                .append(String.format("%-20s%s%n", "tags", Arrays.toString(tags)))
                .append(String.format("%-20s%s%n", "has_incidents", has_incidents))
                .append(String.format("%-20s%s%n", "due_at", due_at))
                .append(String.format("%-20s%s%n", "via", via));
        return stringBuilder.toString();
    }
}
