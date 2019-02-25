package electrycity;

import org.junit.Assert;
import org.junit.Test;

public class ElectricityMeterTest {

    @Test
    public void addKwh_newmeter_properAddition() {
        ElectricityMeter electryMeter = new ElectricityMeter();
        electryMeter.addKwh(1);
        Assert.assertEquals(1, electryMeter.getKwh(), 0f);
    }
    @Test
    public void addKwh_newmeter2_properAddition() {
        ElectricityMeter electryMeter = new ElectricityMeter();
        electryMeter.addKwh(1);
        electryMeter.addKwh(4);
        Assert.assertEquals(1, electryMeter.getKwh(), 5f);
    }
    @Test
    public void addKwh_newmeter5_properAddition() {
        ElectricityMeter electryMeter = new ElectricityMeter();
        electryMeter.addKwh(1);
        electryMeter.addKwh(4);
        electryMeter.addKwh(4);
        electryMeter.addKwh(4);
        electryMeter.addKwh(4);
        Assert.assertEquals(1, electryMeter.getKwh(), 17f);
    }
    @Test
    public void addKwh_newmeter6_properAddition() {
        ElectricityMeter electryMeter = new ElectricityMeter();
        electryMeter.addKwh(1);
        electryMeter.addKwh(4);
        electryMeter.addKwh(4);
        electryMeter.addKwh(4);
        electryMeter.addKwh(4);
        Assert.assertEquals(10, electryMeter.getKwh(), 17f);
    }
}
