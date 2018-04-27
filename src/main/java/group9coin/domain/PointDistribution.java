package group9coin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointDistribution {
    private String target;
    private Integer points;

    public PointDistribution(){}

    public PointDistribution(String target, Integer points) {
        this.target = target;
        this.points = points;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
