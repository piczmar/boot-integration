package demo.aggregator;

import java.util.HashMap;
import java.util.List;

public class ReleaseStrategy {
    public boolean canRelease(List<HashMap> messages) {
        return messages.size() == 2;
    }
}
