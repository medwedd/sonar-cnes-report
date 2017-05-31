package fr.cnes.sonar.report.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A facet of sonarqube
 * @author garconb
 */
public class Facet {
    /**
     * List of values as list of pairs
     */
    private List<Value> values;
    /**
     * Name of the facet
     */
    private String property;

    /**
     * Default constructor
     */
    public Facet() {
        values = new ArrayList<>();
        property = "";
    }

    /**
     * Return the values of the facet
     * @return  list of values
     */
    public List<Value> getValues() {
        return values;
    }

    /**
     * Set the values of the facet
     * @param values list of values
     */
    public void setValues(List<Value> values) {
        this.values = values;
    }

    /**
     * @return the name of the facet
     */
    public String getProperty() {
        return property;
    }

    /**
     * Set the name of a facet
     * @param property name to set
     */
    public void setProperty(String property) {
        this.property = property;
    }
}