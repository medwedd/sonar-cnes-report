package fr.cnes.sonar.report.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a violation of a rule
 * @author begarco
 */
public class Issue {

    /**
     * Severity of the corresponding rule
     */
    private String severity;
    /**
     * Key in SonarQube
     */
    private String key;
    /**
     * Name of the corresponding rule
     */
    private String rule;
    /**
     * Name of the affected file
     */
    private String component;
    /**
     * Name of the affected project
     */
    private String project;
    /**
     * Line of the issue
     */
    private String line;
    /**
     * Issue's status
     */
    private String status;
    /**
     * Issue's type
     */
    private String type;
    /**
     * Issue's message
     */
    private String message;

    /**
     * Default constructor
     */
    public Issue() {
        this.key = "";
        this.line = "";
        this.message = "";
        this.component = "";
        this.rule = "";
        this.severity = "";
        this.project = "";
        this.status = "";
        this.type = "";
    }

    /**
     * Overridden toString
     * @return all data separated with tabulation
     */
    @Override
    public String toString() {
        return key + "\t" + project + "\t" + component + "\t" + type + "\t" + severity + "\t" + message + "\t" + line + "\t" + status + "\t" + "\t";
    }

    /**
     * Get a list of String containing details of each issue
     * @return list of strings
     */
    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        list.add(getKey());
        list.add(getProject());
        list.add(getComponent());
        list.add(getType());
        list.add(getSeverity());
        list.add(getMessage());
        list.add(getLine());
        list.add(getStatus());

        return list;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}