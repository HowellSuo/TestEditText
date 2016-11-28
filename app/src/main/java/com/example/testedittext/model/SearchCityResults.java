package com.example.testedittext.model;


import java.util.List;

public class SearchCityResults {

    private List<SearchCityBean> results;

    public List<SearchCityBean> getResults() {
        return results;
    }

    public void setResults(List<SearchCityBean> results) {
        this.results = results;
    }

    public static class SearchCityBean {
        /**
         * country : CN
         * id : W7VD5N6F061Q
         * name : 北海
         * path : 北海,北海,广西,中国
         * timezone : Asia/Shanghai
         * timezone_offset : +08:00
         */

        private String id;
        private String name;
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
