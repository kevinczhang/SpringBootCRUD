package com.czhang.cpms.model.enums;

public enum Language {
	Java("Java"),
	Go("Go"),
	Javascript("Javascript"),
	Python("Python"),
	CSharp("CSharp"),
	CPlus("CPlus"),
	Ruby("Ruby");	

    private final String name;

    Language(String name) {
        this.name = name;
    }
    
    public String getLanguageName() {
        return this.name;
    }
}
