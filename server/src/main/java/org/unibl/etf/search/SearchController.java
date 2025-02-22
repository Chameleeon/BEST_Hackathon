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

    @PostMapping("/search")
    public String searchProducts(@RequestBody ChatDto query) throws Exception {
        return searchEngine.search(query);
    }

    @PostMapping("/searchTrashBin")
    public String searchTrashBin(@RequestBody ChatDto query) throws Exception {
        return searchEngine.searchTrashBin(query);
    }
}
