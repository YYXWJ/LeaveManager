package leavemanager.example.com.leavemanager.utils;

public class DateUtil {
    public static String formatDate(int arrive_year, int arrive_month, int arrive_day) {
        return arrive_year+"年"+arrive_month+"月"+arrive_day+"日";
    }

    public static String formatTime(int arrive_hour, int arrive_min) {
        return arrive_hour+"时"+arrive_min+"分";
    }
}
