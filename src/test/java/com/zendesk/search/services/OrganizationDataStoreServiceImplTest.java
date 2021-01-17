package com.zendesk.search.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.zendesk.search.domain.Organization;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class OrganizationDataStoreServiceImplTest {
    private DataStoreService dataStoreService;

    @BeforeAll
    void setUp(){
        dataStoreService = new OrganizationDataStoreServiceImpl();
    }


    @Test
    void test_parseOrganization_withValidJsonFile_ShouldReturnListOfObjects() {
        //when
        Map<String,Organization> orgDataStore = dataStoreService.createDataStore("organizations.json");

        //then
        assertNotNull(orgDataStore);
        assertTrue(orgDataStore.size()>0);
    }

    @Test
    void test_parseOrganization_withInValidJsonFileName_ShouldReturnException() {
        //when
        assertThrows(IllegalArgumentException.class, () -> {
            dataStoreService.createDataStore("dummy.json");
        });
    }


}
