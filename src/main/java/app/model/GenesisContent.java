package app.model;

import java.io.Serial;
import java.io.Serializable;

public class GenesisContent implements Serializable {
    @Serial
    private static final long serialVersionUID = 4301150732952726574L;

    private Result result;
    private String jobName;

    public GenesisContent() {}

    public GenesisContent(Result result, String jobName) {
        this.result = result;
        this.jobName = jobName;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
