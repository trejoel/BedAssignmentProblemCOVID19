package architecture;

public class IntegerString {
   private int myInteger;
   private String myString;
   
   public IntegerString(int xInt, String xString) {
	   this.myInteger=xInt;
	   this.myString=xString;
   }
   
   public int getMyInteger() {
	   return this.myInteger;
   }
   
   public String getMyString() {
	   return this.myString;
   }
   
   public void setMyInteger(int xValue) {
	   this.myInteger=xValue;
   }
   
   public void setMyString(String xValue) {
	   this.myString=xValue;
   }
 
}
