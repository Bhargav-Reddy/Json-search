package com.zendesk.search.services;

import java.util.List;

public interface SearchService {
    List<Object> searchByOrganization(String searchTerm, String searchValue);

    List<Object> searchByUser(String searchTerm, String searchValue);

    List<Object> searchByTicket(String searchTerm, String searchValue);
}
