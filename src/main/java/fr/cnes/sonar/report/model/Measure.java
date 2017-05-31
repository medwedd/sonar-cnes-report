package fr.cnes.sonar.report.model;

/**
 * Represents a measure
 * @author begarco
 */
public class Measure {
    /**
     * Name of the metric
     */
    private String metric;
    /**
     * Value of the metric
     */
    private String value;

    /**
     * Default constructor
     */
    public Measure() {
        this.metric = "";
        this.value = "";
    }

    /**
     * Complete constructor
     * @param metric value for metric
     * @param value value for value
     */
    public Measure(String metric, String value) {
        setMetric(metric);
        setValue(value);
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Overridden method toString
     * @return "metric":"value"
     */
    @Override
    public String toString() {
        return getMetric()+":"+getValue();
    }
}