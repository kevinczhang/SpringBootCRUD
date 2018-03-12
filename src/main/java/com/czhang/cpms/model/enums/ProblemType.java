package com.czhang.cpms.model.enums;

public enum ProblemType {
	Algorithm("Algorithm"),
	SystemDesign("SystemDesign"),
	OODesign("OODesign"),
	Database("Database");

    private final String name;

    ProblemType(String name) {
        this.name = name;
    }
    
    public String getTypeName() {
        return this.name;
    }
}
