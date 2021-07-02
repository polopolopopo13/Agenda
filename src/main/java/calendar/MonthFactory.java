package calendar;

public final class MonthFactory {
	
    public static Month getMonth(int user_id, int month_number, int year_number) {  
          if(user_id != 0) {  
                return new MonthUser(month_number, year_number, user_id);
          }  
          else {  
                return new MonthBasic(month_number, year_number);  
          }  
    }  
}
