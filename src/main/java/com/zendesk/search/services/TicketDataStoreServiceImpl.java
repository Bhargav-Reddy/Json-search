package com.zendesk.search.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import com.zendesk.search.domain.Ticket;

public class TicketDataStoreServiceImpl implements DataStoreService<Ticket> {

    @Override
    public Map<String, Ticket> createDataStore(String fileResource ) {
        List<Ticket> ticketList = new ArrayList<>();
        Map<String, Ticket> ticketDataStore = new HashMap<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileResource);
        if (inputStream == null) {
            throw new IllegalArgumentException(fileResource + " file not found!");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, Ticket.class);
            ticketList = objectMapper.readValue(inputStream, collectionType);
            for (Ticket ticket : ticketList) {
                if (!ticketDataStore.containsKey(ticket.get_id())) {
                    ticketDataStore.put(ticket.get_id(), ticket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketDataStore;
    }
}
