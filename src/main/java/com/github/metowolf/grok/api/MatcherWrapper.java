package com.github.metowolf.grok.api;

import com.google.re2j.Matcher;

public class MatcherWrapper {

    private Matcher matcher;
    private java.util.regex.Matcher javaMatcher;
    private boolean isJavaMatcher = false;

    public MatcherWrapper() {}

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public void setJavaMatcher(java.util.regex.Matcher javaMatcher) {
        this.javaMatcher = javaMatcher;
    }

    public void setJavaMatcher(boolean javaMatcher) {
        isJavaMatcher = javaMatcher;
    }

    public boolean find() {
        if (isJavaMatcher) {
            return javaMatcher.find();
        } else {
            return matcher.find();
        }
    }

    public String group(String name) {
        if (isJavaMatcher) {
            return javaMatcher.group(name);
        } else {
            return matcher.group(name);
        }
    }

    public int groupCount() {
        if (isJavaMatcher) {
            return javaMatcher.groupCount();
        } else {
            return matcher.groupCount();
        }
    }

    public int start(int group) {
        if (isJavaMatcher) {
            return javaMatcher.start(group);
        } else {
            return matcher.start(group);
        }
    }

    public int end(int group) {
        if (isJavaMatcher) {
            return javaMatcher.end(group);
        } else {
            return matcher.end(group);
        }
    }

}
