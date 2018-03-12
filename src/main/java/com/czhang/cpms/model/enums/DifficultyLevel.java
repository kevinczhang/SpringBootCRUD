package com.czhang.cpms.model.enums;

public enum DifficultyLevel {
	Hard("Hard"),
	Medium("Medium"),
	Easy("Easy");

    private final String name;

    DifficultyLevel(String name) {
        this.name = name;
    }
    
    public String getDifficultyLevelName() {
        return this.name;
    }
}
