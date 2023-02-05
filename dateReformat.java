
public class dateReformat {

    private static String dateYear;
    private static String dateMonth;
    private static String dateDay;

    public static void setYear(String year) {
        dateYear = year;
    }

    public static void setMonth(String month) {
        dateMonth = month;
    }

    public static void setDay(String day) {
        dateDay = day;
    }

    public static String getDate(){
        return dateYear+"/"+dateMonth+"/"+dateDay;
    }

    

    

    

}
