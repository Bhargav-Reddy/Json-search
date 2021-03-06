package com.zendesk.search.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import com.zendesk.search.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class UserDataStoreServiceImplTest {
    private DataStoreService dataStoreService;

    @BeforeAll
    public void setUp(){
        dataStoreService = new UserDataStoreServiceImpl();
    }


    @Test
    public void test_parseOrganization_withValidJsonFile_ShouldReturnListOfObjects() {
        //when
        Map<String,User> userDataStore = dataStoreService.createDataStore("users.json");

        //then
        assertNotNull(userDataStore);
        assertTrue(userDataStore.size()>0);
    }

    @Test
    public void test_parseOrganization_withInValidJsonFileName_ShouldReturnException() {
        //when
        assertThrows(IllegalArgumentException.class, () -> {
            dataStoreService.createDataStore("dummy.json");
        });
    }

}