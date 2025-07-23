package com.hatm_tracker.util;

public enum HatmStatements {
    TOTAL_PAGES(604);

    private final int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    HatmStatements(int totalPages){
        this.totalPages = totalPages;
    }
}
