package com.oreilly.restclient.json;

public record Assignment(String name, String craft) {

    @Override
    public String toString() {
        return "Assignment{name='" + name + "', craft='" + craft + '}';
    }
}
