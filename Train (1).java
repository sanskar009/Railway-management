import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
public class Train {
	int train_id;
	String train_name;
	String source;
	String destination;
	int Total_seat;
	int remaining_seat;
	LocalDate date;
	int rate;
	
	String url="jdbc:mysql://localhost:3306/railway"; 
	String user="root";//username
	String pass="rishi@5251";//password
	
	
	 Train(int id,String name,String sou, String desti,int s,LocalDate d,int amt)
	{
		train_id=id;
		train_name=name;
		source=sou;
		destination=desti;
		Total_seat=s;
		remaining_seat=s;
		date=d;
		rate=amt;
		
		
		
	//	Scanner sc= new Scanner(System.in);
		String sql="insert into Train values("+train_id+",'"+train_name+"','"+source+"','"+destination+"',"+Total_seat+","+remaining_seat+",'"+date+"',"+rate+");";
		//Connection con=null;
		try
		{ 
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m != 1)
				System.out.println("insertion failed"); 
			 st.close();
		       k.close();
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()); 
		} 
      
       

	}
	 public void decreseSeats()
	 {
		 
		 
		 
		 remaining_seat=remaining_seat-1;
	 }
	 public int getRate()
	 {
		 String sql = "select rate from Train where "; 
			Connection con=null; 
			try
			{ 
				//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
				Class.forName("com.mysql.jdbc.Driver"); 
				//Reference to connection interface 
				con = DriverManager.getConnection(url,user,pass); 

				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				System.out.println(rs.getString(1)+" "+rs.getString(2));

				//int m = st.executeQuery(sql); 
				//if (m == 1) 
					//System.out.println(st.executeQuery(sql)); 
				/*else
					System.out.println("insertion failed"); */
				con.close(); 
			} 
			catch(Exception ex) 
			{ 
				System.err.println("Data not Found!"); 
			} 
		 return rate;
	 }

	public int getTrainId()
	{
		return train_id;
	}
	public String getSource()
	{
		return source;
	}
	public String getDestination()
	{
		return destination;
	}
	public LocalDate getDate()
	{
		return date;
	}
	public int getSeat()
	{
		return remaining_seat;
	}
	public void getTrainDetails()
	{
		System.out.println("Here are Train details");
		System.out.println("TrainId is:"+train_id);
		System.out.println("Train name:"+train_name);
		System.out.println("Train will start from:"+source);
		System.out.println("Train destination:"+destination);
		System.out.println("Date:"+date);
		System.out.println("Rate of each seat:"+rate);
		System.out.println("");
	}
}

