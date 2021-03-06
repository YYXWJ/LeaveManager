package leavemanager.example.com.leavemanager.utils;

public class DateUtil {
    public static String formatDate(int arrive_year, int arrive_month, int arrive_day) {
        //return String.valueOf(arrive_year)+"-"+String.valueOf(arrive_month)+"-"+String.valueOf(arrive_day);
        return String.format("%04d",arrive_year)+"年"+String.format("%02d",arrive_month)+"月"+String.format("%02d",arrive_day)+"日";
    }

    public static String formatTime(int arrive_hour, int arrive_min) {
        return String.format("%02d",arrive_hour)+"时"+String.format("%02d",arrive_min)+"分";
    }

    public static String formatShowDate(String fromdate) {
        String year = fromdate.substring(0,4);
        String month = fromdate.substring(4,6);
        String day = fromdate.substring(6,8);
        String hour = fromdate.substring(8,10);
        String min = fromdate.substring(10,fromdate.length());
        return year+"年"+month+"月"+day+"日"+hour+"时"+min+"分";
    }
}
