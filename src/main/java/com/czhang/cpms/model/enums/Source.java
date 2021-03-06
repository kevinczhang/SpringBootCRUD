package com.czhang.cpms.model.enums;

public enum Source {
	LeetCode("LeetCode"),
	CodeSnippet("CodeSnippet"),
	Facebook("Facebook"),
	LintCode("LintCode");

    private final String name;

    Source(String name) {
        this.name = name;
    }
    
    public String getSourceName() {
        return this.name;
    }
}
