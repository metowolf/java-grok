package com.github.metowolf.grok.api;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;

public class PatternWrapper {

    private Pattern pattern;
    private java.util.regex.Pattern javaPattern;
    private boolean isJavaPattern = false;

    public PatternWrapper(String regex) {
        try {
            this.pattern = Pattern.compile(rewritePattern(regex));
        } catch (Exception e) {
            this.javaPattern = java.util.regex.Pattern.compile(regex);
            this.isJavaPattern = true;
        }
    }

    public MatcherWrapper matcher(CharSequence input) {
        MatcherWrapper matcherWrapper = new MatcherWrapper();
        if (isJavaPattern) {
            matcherWrapper.setJavaMatcher(javaPattern.matcher(input));
            matcherWrapper.setJavaMatcher(true);
        } else {
            matcherWrapper.setMatcher(pattern.matcher(input));
        }
        return matcherWrapper;
    }

    //pattern()
    public String pattern() {
        if (isJavaPattern) {
            return javaPattern.pattern();
        } else {
            return pattern.pattern();
        }
    }

    public static PatternWrapper compile(String regex) {
        return new PatternWrapper(regex);
    }

    private String rewritePattern(String pattern) {
        Pattern p = Pattern.compile("\\(\\?<([a-zA-Z][_a-zA-Z0-9]*)>");
        Matcher m = p.matcher(pattern);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
          m.appendReplacement(sb, "(?P<" + m.group(1) + ">");
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
