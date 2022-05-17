package com.imdbListApp.entities;

public class DataResult<T>{
    private long page;
    private T results;
    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

}
