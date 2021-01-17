package com.zendesk.search.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zendesk.search.domain.Organization;
import com.zendesk.search.domain.Ticket;
import com.zendesk.search.domain.User;

public class SearchServiceImpl implements SearchService{

    private static Map<String, List<String>> orgInvertedIndex;
    private static Map<String, Organization> orgDataStore;

    private static Map<String, List<String>> ticketsInvertedIndex;
    private static Map<String, Ticket> ticketDataStore;

    private static Map<String, List<String>> userInvertedIndex;
    private static Map<String, User> userDataStore;

    static {
        orgInvertedIndex = new InvertedIndexServiceImpl().createInvertedIndex("organizations.json");
        orgDataStore = new OrganizationDataStoreServiceImpl().createDataStore("organizations.json");

        ticketsInvertedIndex = new InvertedIndexServiceImpl().createInvertedIndex("tickets.json");
        ticketDataStore = new TicketDataStoreServiceImpl().createDataStore("tickets.json");

        userInvertedIndex = new InvertedIndexServiceImpl().createInvertedIndex("users.json");
        userDataStore = new UserDataStoreServiceImpl().createDataStore("users.json");
    }

    @Override
    public List<Object> searchByOrganization(String searchTerm, String searchValue){

        List<Object> searchResult = new ArrayList<>();
        if(!isSearchable(searchTerm, Organization.class)){
            throw new IllegalArgumentException("Term: "+searchTerm+" not available in Organization");
        }

        if(searchTerm.equals("_id")){
            searchResult.add(orgDataStore.get(searchValue));
            List<String>  ticketIdList = ticketsInvertedIndex.get(searchValue);
            if(ticketIdList != null){
                ticketIdList.stream().forEach(ticketId -> searchResult.add(ticketDataStore.get(ticketId)));
            }
            List<String> userIdList = userInvertedIndex.get(searchValue);
            if(userIdList != null){
                userIdList.stream().forEach(userId -> searchResult.add(userDataStore.get(userId)));
            }
        } else {
            List<String> orgIdList = orgInvertedIndex.get(searchValue);
            if (orgIdList != null) {
                orgIdList.stream().forEach(orgId -> searchResult.add(orgDataStore.get(orgId)));
            }
        }
        return searchResult;
    }

    @Override
    public List<Object> searchByUser(String searchTerm, String searchValue) {

        List<Object> searchResult = new ArrayList<>();
        if(!isSearchable(searchTerm, User.class)){
            throw new IllegalArgumentException("Term: "+searchTerm+" not available in User");
        }

        if(searchTerm.equals("_id")){
            searchResult.add(userDataStore.get(searchValue));
            List<String>  ticketIdList = ticketsInvertedIndex.get(searchValue);
            if(ticketIdList != null){
                ticketIdList.stream().forEach(ticketId -> searchResult.add(ticketDataStore.get(ticketId)));
            }

        } else {
            List<String> userIdList = userInvertedIndex.get(searchValue);
            if (userIdList != null) {
                userIdList.stream().forEach(userId -> searchResult.add(userDataStore.get(userId)));
            }
        }
        return searchResult;
    }

    @Override
    public List<Object> searchByTicket(String searchTerm, String searchValue) {
        List<Object> searchResult = new ArrayList<>();
        if(!isSearchable(searchTerm, Ticket.class)){
            throw new IllegalArgumentException("Term: "+searchTerm+" not available in User");
        }

        if(searchTerm.equals("_id")){
            searchResult.add(ticketDataStore.get(searchValue));
        } else {
            List<String> ticketIdList = ticketsInvertedIndex.get(searchValue);
            if (ticketIdList != null) {
                ticketIdList.stream().forEach(ticketId -> searchResult.add(ticketDataStore.get(ticketId)));
            }
        }
        return searchResult;
    }

    public static boolean isSearchable(String searchTerm, Class searchType) {
        boolean isTermExists = false;
        Field[] searchableFields = searchType.getDeclaredFields();
        for(Field field : searchableFields){
            if(field.getName().equals(searchTerm)){
                isTermExists = true;
            }
        }
        return isTermExists;
    }

    public static List<String> listSearchableFields(Class searchType){
        List<String> fieldList = new ArrayList<>();
        Field[] searchableFields = searchType.getDeclaredFields();
        for(Field field : searchableFields){
            fieldList.add(field.getName());
        }
        return fieldList;
    }
}
