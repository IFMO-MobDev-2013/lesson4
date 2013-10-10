import mazinva.Calculator.*;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;


public class CalculatorTest extends Assert {
    private final Map<String, Double> map = new HashMap<String, Double>();

    @Before
    public void setUpCalculatorTest() {
        map.put("5.0+2.5", 7.5);
        map.put("5.0-2.5", 2.5);
        map.put("5.0/2.5", 2.0);
        map.put("5.0*2.5", 12.5);

        map.put("5", 5.0);
        map.put("(5.0)", 5.0);
        map.put("5.0E2", 5.0E2);

        map.put("5.0*2.5+2.5", 15.0);
        map.put("5.0*(2.5+2.5)",25.0);

        map.put("-5.0", -5.0);
        map.put("+5.0", 5.0);
    }

    @org.junit.Test
    public void testCalculateString() throws Exception {
        for(Map.Entry<String, Double> entry : map.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            assertEquals(new Parser().parse(key).calculate(), value);
        }
    }

    @After
    public void tearDownCalculatorTest() {
        map.clear();
    }


}