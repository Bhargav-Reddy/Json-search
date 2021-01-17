package com.zendesk.search.services;

import java.util.List;
import java.util.Map;

public interface InvertedIndexService {
    Map<String, List<String>> createInvertedIndex(String fileResource);
}
