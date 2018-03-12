package com.czhang.cpms.model.enums;

public enum Company {
	Google("Google"),
	Facebook("Facebook"),
	Yelp("Yelp"),
	Microsoft("Microsoft"),
	Bloomberg("Bloomberg"),
	Amazon("Amazon");

    private final String name;

    Company(String name) {
        this.name = name;
    }
    
    public String getCompanyName() {
        return this.name;
    }
}
