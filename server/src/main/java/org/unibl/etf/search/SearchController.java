package org.unibl.etf.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    private final SearchProductEngine searchEngine;

    @Autowired
    public SearchController(SearchProductEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String query) {
        return searchEngine.search(query);
    }
}
