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
import com.zendesk.search.domain.User;

public class UserDataStoreServiceImpl implements DataStoreService<User> {

    @Override
    public Map<String, User> createDataStore(String fileResource ) {
        List<User> userList = new ArrayList<>();
        Map<String, User> userDataStore = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource(fileResource);
        if (resource == null) {
            throw new IllegalArgumentException(fileResource + " file not found!");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(new File(resource.toURI()));
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, User.class);
            userList = objectMapper.readValue(new File(resource.toURI()), collectionType);
            for (User user : userList) {
                if (!userDataStore.containsKey(user.get_id())) {
                    userDataStore.put(user.get_id(), user);
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return userDataStore;
    }
}
