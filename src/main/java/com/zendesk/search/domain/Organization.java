package com.zendesk.search.domain;

import java.io.Serializable;
import java.util.Arrays;

public class Organization implements Serializable {
    private String  _id;
    private String url;
    private String external_id;
    private String name;
    private String[] domain_names;
    private String created_at;
    private String details;
    private String shared_tickets;
    private String[] tags;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDomain_names() {
        return domain_names;
    }

    public void setDomain_names(String[] domain_names) {
        this.domain_names = domain_names;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String isShared_tickets() {
        return shared_tickets;
    }

    public void setShared_tickets(String shared_tickets) {
        this.shared_tickets = shared_tickets;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-20s%s%n", "_id", _id))
                .append(String.format("%-20s%s%n", "url", url))
                .append(String.format("%-20s%s%n", "external_id", external_id))
                .append(String.format("%-20s%s%n", "domain_names", Arrays.toString(domain_names)))
                .append(String.format("%-20s%s%n", "created_at", created_at))
                .append(String.format("%-20s%s%n", "details", details))
                .append(String.format("%-20s%s%n", "shared_tickets", shared_tickets))
                .append(String.format("%-20s%s%n", "tags", Arrays.toString(tags)));
       return stringBuilder.toString();
    }
}
