package demo.aggregator;

import java.util.HashMap;
import java.util.List;

public class MessageAggregator {

    public HashMap add(List<HashMap> results) {
        String s = "";
        for (HashMap msg : results) {
            s += msg.get("message");
        }
        HashMap result = new HashMap();
        result.put("message", s);

        return result;
    }
}
