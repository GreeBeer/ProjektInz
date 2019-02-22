package electrycity;
//liczył kwh
//liczył taryfy
//ustawianie taryf i stawek
//o ile droższy jest prad poza stawką

//narzędzia ustawiające taryfy i stawki

import java.util.Calendar;

public class ElectricityMeter {

    private float kwh = 0;
    private int centsForkwh = 0;

    private boolean tarriffon = false;
    private float KwhTariff = 0;
    private int centsForKwhTariff = 0;

    private int electrycityTariffStartHour = 0;
    private int electrycityTariffEndHour = 0;

    public void addKwh(float kwhToAdd){
        if(isTarriffNow()) {
            KwhTariff += kwhToAdd;
        }else{
            kwh += kwhToAdd;
        }
    }
    private boolean isTarriffNow(){
        Calendar  rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        return hour > electrycityTariffStartHour && hour < electrycityTariffEndHour;
    }
    public int getHowMoreExpensiveNormalls() {
        return (centsForkwh * 100 /centsForKwhTariff) - 100;
    }

    public float getKwh() {
        return kwh;
    }

    void setCentsForkwh(int centsForkwh) {
        this.centsForkwh = centsForkwh;
    }

     void setTarriffon(boolean tarriffon) {
        this.tarriffon = tarriffon;
    }

     void setCentsForKwhTariff(int centsForKwhTariff) {
        this.centsForKwhTariff = centsForKwhTariff;
    }

     void setElectrycityTariffStartHour(int electrycityTariffStartHour) {
        this.electrycityTariffStartHour = electrycityTariffStartHour;
    }

     void setElectrycityTariffEndHour(int electrycityTariffEndHour) {
        this.electrycityTariffEndHour = electrycityTariffEndHour;
    }
}
