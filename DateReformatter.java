
public class DateReformatter {
    String dateYear;
    String dateMonth;
    String dateDay;

    public  void setYear(String year) {
        dateYear = year;
    }

    public  void setMonth(String month) {
        dateMonth = month;
    }

    public  void setDay(String day) {
        dateDay = day;
    }

    public  String getDate(){
        return dateYear+"/"+dateMonth+"/"+dateDay;
    }
}
