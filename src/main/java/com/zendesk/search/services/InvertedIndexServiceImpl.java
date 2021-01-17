package com.zendesk.search.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class InvertedIndexServiceImpl implements InvertedIndexService{

    private Map<String, List<String>> invertedIndex = new HashMap<>();

    @Override
    public Map<String, List<String>> createInvertedIndex(String fileResource) {
        URL resource = InvertedIndexServiceImpl.class.getClassLoader().getResource(fileResource);
        if (resource == null) {
            throw new IllegalArgumentException(fileResource + " file not found!");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(new File(resource.toURI()));
            traverseJsonDoc(root);
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        return invertedIndex;
    }

    private void traverseJsonDoc(JsonNode root) {
        if (root.isObject()) {
            Iterator<String> fieldNames = root.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                String key = fieldValue.asText();
                if (fieldValue.isArray()) {
                    ArrayNode arrayNode = (ArrayNode) fieldValue;
                    for (int i = 0; i < arrayNode.size(); i++) {
                        JsonNode arrayElement = arrayNode.get(i);
                        index(root, fieldName, arrayElement.asText());
                    }
                } else {
                    index(root, fieldName, key);
                    traverseJsonDoc(fieldValue);
                }
            }
        } else if (root.isArray()) {
            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                traverseJsonDoc(arrayElement);
            }
        }
    }

    private void index(JsonNode root, String fieldName, String key) {
        if (!invertedIndex.containsKey(key) && !fieldName.equals("_id")) {
            invertedIndex.put(key, new ArrayList<String>());
            invertedIndex.get(key).add(root.get("_id").asText());
        } else if (!fieldName.equals("_id")) {
            invertedIndex.get(key).add(root.get("_id").asText());
        }
    }
}
