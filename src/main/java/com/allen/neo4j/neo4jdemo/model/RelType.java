package com.allen.neo4j.neo4jdemo.model;

public enum RelType {

    SELF("self"),

    FIRST("first"),

    PREVIOUS("previous"),

    NEXT("next"),

    LAST("last");

    private String type;

    RelType(String type) {
        this.type = type;
    }

}
