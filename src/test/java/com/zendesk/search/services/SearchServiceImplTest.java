package com.zendesk.search.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zendesk.search.domain.Organization;
import com.zendesk.search.domain.Ticket;
import com.zendesk.search.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class SearchServiceImplTest {

    private SearchServiceImpl searchServiceImpl;

    @BeforeAll
    void setUp() {
        searchServiceImpl = new SearchServiceImpl();
    }

    @Test
    void test_searchByOrganization_withTermAvailable_shouldReturnOrganization() {
        //when
        List<Object> searchResult = searchServiceImpl.searchByOrganization("external_id", "197f93c0-1729-4c82-9bb0-143e978f06ce");

        //then
        assertTrue(searchResult.size() == 1);
        assertTrue(searchResult.get(0) instanceof Organization);
    }

    @Test
    void test_searchByOrganization_withId_shouldReturnOrganizationUserAndTicket() {
        //Given
        boolean isUserInstance = false;
        boolean isTicketInstance = false;
        //when
        List<Object> searchResult = searchServiceImpl.searchByOrganization("_id","109");
        for(Object object: searchResult){
            if( object instanceof User){
                isUserInstance = true;
                break;
            }
        }
        for(Object object: searchResult){
            if( object instanceof Ticket){
                isTicketInstance = true;
                break;
            }
        }
        //then
        assertTrue(searchResult.size()>1);
        assertEquals(true,isUserInstance);
        assertEquals(true,isTicketInstance);
    }

    @Test
    void test_searchByUser_withTermAvailable_shouldReturnUser() {
        //when
        List<Object> searchResult = searchServiceImpl.searchByUser("alias", "Mr Georgette");

        //then
        assertTrue(searchResult.size() == 1);
        assertTrue(searchResult.get(0) instanceof User);
    }

    @Test
    void test_searchByUser_withTermAvailable_shouldReturnTicket() {
        //when
        List<Object> searchResult = searchServiceImpl.searchByTicket("tags", "Texas");

        //then
        assertTrue(searchResult.size() > 1);
        assertTrue(searchResult.get(0) instanceof Ticket);
    }

    @Test
    void test_searchByOrganization_withTermNotAvailable_shouldReturnException(){
        assertThrows(IllegalArgumentException.class, () -> searchServiceImpl.searchByOrganization("dummy","xyz"));
    }

    @Test
    void test_isSearchable_withTermExists_shouldReturnTrue() {
        assertTrue(searchServiceImpl.isSearchable("external_id", Organization.class));
    }

    @Test
    void test_isSearchable_withTermNotExisted_shouldReturnFalse() {
        assertFalse(searchServiceImpl.isSearchable("project_id", Organization.class));
    }
}