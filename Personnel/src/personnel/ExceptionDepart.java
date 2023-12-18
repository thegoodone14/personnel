package personnel;

public class ExceptionDepart extends Exception{
     	public ExceptionDepart()
	    {
	        System.out.println("Exception ExceptionDepart has been raised...");
	    }
     	@Override
     	public String toString()
        {
          return "La date de départ ne peut pas etre avant la date d'arrivée ";
        }
	

}