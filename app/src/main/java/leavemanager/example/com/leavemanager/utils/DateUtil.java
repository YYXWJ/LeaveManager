package leavemanager.example.com.leavemanager.utils;

public class DateUtil {
    public static String formatDate(int arrive_year, int arrive_month, int arrive_day) {
        return String.valueOf(arrive_year)+String.valueOf(arrive_month)+String.valueOf(arrive_day);
    }

    public static String formatTime(int arrive_hour, int arrive_min) {
        return String.valueOf(arrive_hour)+String.valueOf(arrive_min);
    }
}
