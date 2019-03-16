package ksiagarnia.com.internal;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtil {

    public static Timestamp rok(int rok) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, rok);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
