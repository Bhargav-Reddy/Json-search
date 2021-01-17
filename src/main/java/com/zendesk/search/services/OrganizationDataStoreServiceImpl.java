package com.zendesk.search.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zendesk.search.domain.Organization;

public class OrganizationDataStoreServiceImpl implements DataStoreService<Organization>{

    @Override
    public Map<String, Organization> createDataStore(String fileResource ) {
        List<Organization> organizationList = new ArrayList<>();
        Map<String, Organization> orgDataStore = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource(fileResource);
        if (resource == null) {
            throw new IllegalArgumentException(fileResource + " file not found!");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(new File(resource.toURI()));
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, Organization.class);
            organizationList = objectMapper.readValue(new File(resource.toURI()), collectionType);
            for (Organization organization : organizationList) {
                if (!orgDataStore.containsKey(organization.get_id())) {
                    orgDataStore.put(organization.get_id(), organization);
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return orgDataStore;
    }

}
