package com.zendesk.search.services;

import java.util.List;
import java.util.Map;

import com.zendesk.search.domain.Organization;

public interface DataStoreService<T> {

    public Map<String,T> createDataStore(String fileResource);

}
