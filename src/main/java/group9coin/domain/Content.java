package group9coin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    private List<PointDistribution> pointDistribution;

    public Content() {
    }

    public Content(List<PointDistribution> pointDistribution) {
        this.pointDistribution = pointDistribution;
    }

    public List<PointDistribution> getPointDistribution() {
        return pointDistribution;
    }

    public void setPointDistribution(List<PointDistribution> pointDistribution) {
        this.pointDistribution = pointDistribution;
    }


}
