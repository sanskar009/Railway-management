import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.*;

class Ticket{

	int ticket_id;
	int customer_id;
	int train_id;
	int payment_id;
	LocalDate ticket_date;
	LocalDate booking_date;
	int seat_no;
	String conformation_status;
	
	String url="jdbc:mysql://localhost:3306/railway"; 
	String user="root";//username
	String pass="rishi@5251";//password
	

	Ticket(int id1,int id2, int id3,int id4, LocalDate t_date,int s_no,String status)
	{
		ticket_id=id1;
		customer_id=id2;
		train_id=id3;
		ticket_date=t_date;
		booking_date=LocalDate.now();
		seat_no=s_no;
		conformation_status=status;
		payment_id=id4;
		String sql="insert into Ticket values("+ticket_id+","+customer_id+","+train_id+","+payment_id+",'"+ticket_date+"','"+booking_date+"',"+seat_no+",'"+conformation_status+"');";
		try
		{ 
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m == 1) 
				System.out.println("insertion successfull!"); 
			else
				System.out.println("insertion failed"); 
			k.close(); 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()+" type of error found!"); 
		} 
	}

	protected int getTicketId()
	{
		return ticket_id;
	}
	protected int getCustomerId()
	{
		return customer_id;
	}
	protected int getTrainID()
	{
		return train_id;
	}
	protected String getConformationStatus()
	{
		return conformation_status;
	}
	protected void getTicketDetails()
	{
		System.out.println("Ticket Details are-");
		System.out.println("Ticket ID :"+ticket_id);
		System.out.println("Train Id :"+train_id);
		System.out.println("Customer ID :"+customer_id);
		System.out.println("Payment ID :"+payment_id);
		System.out.println("Ticket Date:"+ticket_date);
		System.out.println("Booking Date :"+booking_date);
		System.out.println("Seat number:"+seat_no);
		System.out.println("Conformation Status :"+conformation_status);
	
	}
}