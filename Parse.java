import java.util.ArrayList;

public class Parse {
    private final String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?";
    String year, month, day;

    //Used to convert YYYYMonthDD to YYYY/MM/DD
    public String PublishDate(String pubDate) {
        /*
         * The format of pubDate will look like YYYYMonthDD
         * We are splitting the year, month and day using .substring
         * Which will then be concatenated to a date format of YYYY/MM/DD
         * This format will allow us to have a string "YYYY/MM/DD" that we can concatonate in our URL
         */
        year = pubDate.substring(0, 4);
        month = pubDate.substring(4, 7);
        day = pubDate.substring(7);

        if(day.isEmpty()){ //There are certain entries that do not have a day
            day = "01"; // "01" will act as a default value for an empty day
        }

        //Creating a switch case that will conver the month into corresponding month numbers
        switch(month){
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month =  "06";
                break;
            case "Jul":
                month =  "07";
                break;
            case "Aug":
                month =  "08";
                break;
            case "Sep":
                month =  "09";
                break;
            case "Oct":
                month =  "10";
                break;
            case "Nov":
                month =  "11";
                break;
            case "Dec":
                month =  "12";
                break;
            default:
                break;
        };

        //return a string of YYYY/MM/DD
        return year + "/" + month + "/" + day;
    }

    public String CompletionDate(String compDate) {
        year = compDate.substring(0, 4); 
        month = compDate.substring(4, 6);
        day = compDate.substring(6);
        return year + "/" + month + "/" + day;
    }

    public String getAuthorName(String lastName, String initials, boolean check1, boolean check2) {
        
        if (check1 == true && check2 == true){ 
            lastName=lastName.replace(" ","%20");//if the author has a space in their last name replace the space with %20 
            return lastName+"%20"+initials; //If both last name and initials are present then return lastname and initials concatenated with a URL whitespace using %20 in between
        }
        else if(check1 == true && check2 == false){
            lastName=lastName.replace(" ","%20");
            return lastName; //if the last name is only present return only last name
        }
        else if(check1 == false && check2 == true){
            return initials; //if the initials are only present return only initials
        }
        else{
            return null; //if an author is not listed then 
        }
    }

    public String formURL(ArrayList<String> authors, ArrayList<String> compDates, ArrayList<String> pubDates, ArrayList<String> shortTitles, int i) {
        if (authors.get(i)!=null && compDates.get(i)!=null){ //IF ARTICLE HAS AN AUTHOR
            authors.get(i).replace(" ","%20");
            return url+"db=pubmed&term="+authors.get(i)+"[au]"+pubDates.get(i)+"[pdat]"+compDates.get(i)+"[DCOM]";
        }
        else if(authors.get(i)!=null && compDates.get(i)==null){
            authors.get(i).replace(" ","%20");
            return url+"db=pubmed&term="+authors.get(i)+"[au]"+pubDates.get(i)+"[pdat]"+shortTitles.get(i)+"[title]";

        }
        else if(authors.get(i)==null && compDates.get(i)!=null){//IF NO AUTHOR STATED
            return url+"db=pubmed&term="+pubDates.get(i)+"[pdat]"+shortTitles.get(i)+"[title]"+compDates.get(i)+"[DCOM]";
        }
        else{
            return url+"db=pubmed&term="+pubDates.get(i)+"[pdat]"+shortTitles.get(i)+"[title]";
        }
	}

}

