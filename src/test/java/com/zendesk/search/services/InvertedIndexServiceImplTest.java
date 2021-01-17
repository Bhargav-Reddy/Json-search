package com.zendesk.search.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class InvertedIndexServiceImplTest {

    private InvertedIndexService invertedIndexService;

    @BeforeAll
    void setUp() {
        invertedIndexService = new InvertedIndexServiceImpl();
    }

    @Test
    void test_createInvertedIndex_withOrganizationFile_shouldReturnIndex() {
        //Given
        List<String> orgIdList = new ArrayList<>();
        orgIdList.add("109");
        //when
        Map<String, List<String>> orgInvertedIndex = invertedIndexService.createInvertedIndex("organizations.json");

        //then
        assertEquals(orgIdList, orgInvertedIndex.get("Heath"));
        assertNotEquals(orgIdList, orgInvertedIndex.get("West"));
    }

    @Test
    void test_createInvertedIndex_withNotExistedFile_shouldReturnException() {
        assertThrows(IllegalArgumentException.class, () -> invertedIndexService.createInvertedIndex("dummy.json"));
    }

    @Test
    void test_createInvertedIndex_withUserFile_shouldReturnIndex() {
        //Given
        List<String> userIdList = new ArrayList<>();
        userIdList.add("30");
        //when
        Map<String, List<String>> userInvertedIndex = invertedIndexService.createInvertedIndex("users.json");

        //then
        assertEquals(userIdList, userInvertedIndex.get("2015-01-22T06:48:31 -11:00"));
        assertNotEquals(userIdList, userInvertedIndex.get("carrollraymond@flotonic.com"));
    }

    @Test
    void test_createInvertedIndex_withTicketFile_shouldReturnIndex() {
        //Given
        List<String> ticketIdList = new ArrayList<>();
        ticketIdList.add("2217c7dc-7371-4401-8738-0a8a8aedc08d");
        //when
        Map<String, List<String>> ticketInvertedIndex = invertedIndexService.createInvertedIndex("tickets.json");

        //then
        assertEquals(ticketIdList, ticketInvertedIndex.get("A Catastrophe in Hungary"));
        assertNotEquals(ticketIdList, ticketInvertedIndex.get("A Problem in Morocco"));
    }
}