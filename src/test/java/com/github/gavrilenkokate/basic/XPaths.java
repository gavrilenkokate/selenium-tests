package com.github.gavrilenkokate.basic;

/**
 * Created by katsiarynahaurylenka on 1/26/17.
 */
public class XPaths {

    public static final String NAVBAR_CLASS = "navbar-collapse collapse";
    public static final String CHANGE_LANGUAGE_CLASS ="pull-left width_change";
    public static final String MY_ACCOUNT_CLASS = "<!--pull-right--> width_change";
    public static final String MY_ACCOUNT_LINK = String.format("//div[contains(@class, '%s')]//li[contains(@class, '%s')]/a", NAVBAR_CLASS, MY_ACCOUNT_CLASS);
    public static final String CHANGE_LANGUAGE_LINK = String.format("//div[contains(@class, '%s')]//li[contains(@class, '%s')]/a", NAVBAR_CLASS, CHANGE_LANGUAGE_CLASS);
}
