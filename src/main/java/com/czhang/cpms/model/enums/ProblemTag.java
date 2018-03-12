package com.czhang.cpms.model.enums;

public enum ProblemTag {
	Cite("Cite");
	
	private final String name;

	ProblemTag(String name) {
        this.name = name;
    }
    
    public String getTagName() {
        return this.name;
    }
}
