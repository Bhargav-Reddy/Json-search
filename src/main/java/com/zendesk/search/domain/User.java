package com.zendesk.search.domain;

import java.util.Arrays;

public class User {
    private String _id;
    private String url;
    private String external_id;
    private String name;
    private String alias;
    private String created_at;
    private String active;
    private String verified;
    private String shared;
    private String locale;
    private String timezone;
    private String last_login_at;
    private String email;
    private String phone;
    private String signature;
    private String organization_id;
    private String[] tags;
    private String suspended;
    private String role;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String isActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String isVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String isShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(String last_login_at) {
        this.last_login_at = last_login_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String isSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-20s%s%n", "_id", _id))
                .append(String.format("%-20s%s%n", "url", url))
                .append(String.format("%-20s%s%n", "external_id", external_id))
                .append(String.format("%-20s%s%n", "name", name))
                .append(String.format("%-20s%s%n", "alias", alias))
                .append(String.format("%-20s%s%n", "created_at", created_at))
                .append(String.format("%-20s%s%n", "active", active))
                .append(String.format("%-20s%s%n", "verified", verified))
                .append(String.format("%-20s%s%n", "shared", shared))
                .append(String.format("%-20s%s%n", "locale", locale))
                .append(String.format("%-20s%s%n", "timezone", timezone))
                .append(String.format("%-20s%s%n", "last_login_at", last_login_at))
                .append(String.format("%-20s%s%n", "email", email))
                .append(String.format("%-20s%s%n", "phone", phone))
                .append(String.format("%-20s%s%n", "signature", signature))
                .append(String.format("%-20s%s%n", "organization_id", organization_id))
                .append(String.format("%-20s%s%n", "tags", Arrays.toString(tags)))
                .append(String.format("%-20s%s%n", "suspended", suspended))
                .append(String.format("%-20s%s%n", "role", role));
        return stringBuilder.toString();
    }
}
