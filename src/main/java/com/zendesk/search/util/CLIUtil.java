package com.zendesk.search.util;

import java.util.List;

public class CLIUtil {

    public static final String SEARCH = "1";
    public static final String LIST_FIELDS = "2";

    public static final String SEARCH_USERS = "1";
    public static final String SEARCH_TICKETS = "2";
    public static final String SEARCH_ORGANIZATIONS = "3";

    public static final String EXIT = "exit";
    public static final String QUIT = "quit";

    public static void printWelcomeMessage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Welcome to Zendesk Search! \uD83D\uDE00 \n")
                .append("Type 'quit' to exit at any time,Press 'Enter' to continue");
        System.out.println(stringBuilder);
    }

    public static void printMainOptions(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t Select an options\n")
                .append("\t\t* Press " + SEARCH + " to search Zendesk\n")
                .append("\t\t* Press " + LIST_FIELDS + " to view a list of searchable fields\n")
                .append("\t\t* Type '" + QUIT + "' to exit\n\n");
        System.out.println(stringBuilder);
    }

    public static void printSearchOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select an option\n")
                .append("\t\t* Press " + SEARCH_USERS + " to search Users\n")
                .append("\t\t* Press " + SEARCH_TICKETS + " to search Tickets\n")
                .append("\t\t* Press " + SEARCH_ORGANIZATIONS + " to search Organizations\n")
                .append("\t\t* Type '" + EXIT + "' to go back to the main menu or '" + QUIT + "' to quit\n\n");
        System.out.println(stringBuilder);
    }

    public static void printSearchableFields(String name, List<String> fields) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n----------------------------------------\n");
        stringBuilder.append("Search " + name + " with \n");
        fields.stream().forEach(field -> stringBuilder.append(field).append("\n"));
        System.out.println(stringBuilder);
    }

    public static void printResults(List<Object> searchResults) {
        if (searchResults.size() > 0) {
            searchResults.stream().forEach(resultEntity ->
                    System.out.println(resultEntity + "\n-----------------------------------------\n"));
            System.out.println("Total records: " + searchResults.size() + "\n");
        } else {
            System.out.println("No results found");
        }
    }

    public static void printQuitMessage() {
        System.out.println("Thank you for using Zendesk Search! \uD83D\uDE00");
    }



}
