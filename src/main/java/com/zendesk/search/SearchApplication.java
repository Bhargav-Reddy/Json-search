package com.zendesk.search;

import java.util.Scanner;

import com.zendesk.search.domain.Organization;
import com.zendesk.search.domain.Ticket;
import com.zendesk.search.domain.User;
import com.zendesk.search.services.SearchServiceImpl;
import com.zendesk.search.util.CLIUtil;

public class SearchApplication {

    public static void main(String[] args) {
        SearchServiceImpl searchServiceImpl = new SearchServiceImpl();

        CLIUtil.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());
        String selectedOption = scanner.nextLine();
        if(selectedOption.equalsIgnoreCase("quit")){
            CLIUtil.printQuitMessage();
            System.exit(0);
        }
        while (true) {
            CLIUtil.printMainOptions();
            selectedOption = scanner.next();
            switch (selectedOption) {
                case CLIUtil.SEARCH:
                    do {
                        CLIUtil.printSearchOptions();
                        selectedOption = scanner.next();
                        switch (selectedOption) {
                            case CLIUtil.SEARCH_USERS:
                            case CLIUtil.SEARCH_TICKETS:
                            case CLIUtil.SEARCH_ORGANIZATIONS:
                                System.out.println("Enter search term");
                                String term = scanner.next();
                                System.out.println("Enter search value");
                                String value = scanner.next();
                                if(value.equals("\"\"")){
                                    value="";
                                }
                                try {
                                    if (selectedOption.equals(CLIUtil.SEARCH_USERS)) {
                                        CLIUtil.printResults(searchServiceImpl.searchByUser(term, value));
                                    } else if (selectedOption.equals(CLIUtil.SEARCH_TICKETS)) {
                                        CLIUtil.printResults(searchServiceImpl.searchByTicket(term, value));
                                    } else if (selectedOption.equals(CLIUtil.SEARCH_ORGANIZATIONS)) {
                                        CLIUtil.printResults(searchServiceImpl.searchByOrganization(term, value));
                                    }
                                } catch (IllegalArgumentException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case CLIUtil.EXIT:
                                break;
                            case CLIUtil.QUIT:
                                CLIUtil.printQuitMessage();
                                System.exit(0);
                            default:
                                System.out.println("Please enter a valid option.\n");
                                break;
                        }
                    } while (!selectedOption.equals(CLIUtil.EXIT));
                    break;
                case CLIUtil.LIST_FIELDS:
                    CLIUtil.printSearchableFields("Users", searchServiceImpl.listSearchableFields(User.class));
                    CLIUtil.printSearchableFields("Tickets", searchServiceImpl.listSearchableFields(Ticket.class));
                    CLIUtil.printSearchableFields("Organizations", searchServiceImpl.listSearchableFields(Organization.class));
                    break;
                case CLIUtil.QUIT:
                    CLIUtil.printQuitMessage();
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid option.\n");
                    break;
            }
        }
    }
}
