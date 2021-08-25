import java.sql.*;
import java.time.LocalDate;

class Payment{
	int payment_id;
	int customer_id;
	LocalDate payment_date;
	int amount;
	static String status="not done";
	String payment_mode;
	
	String url="jdbc:mysql://localhost:3306/railway"; 
	String user="root";//username
	String pass="rishi@5251";//password
	
	
	
	Payment(int p_id,int c_id,int amt, String s, String mode)
	{
		payment_id=p_id;
		customer_id=c_id;
		payment_date=LocalDate.now();
		amount=amt;
		status=s;
		payment_mode=mode;
		
		
	//	Scanner sc= new Scanner(System.in);
		String sql="insert into Payment values("+payment_id+","+customer_id+",'"+payment_date+"',"+amount+",'"+status+"','"+payment_mode+"');";
		//Connection con=null;
		try
		{ 
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m == 1) 
				System.out.println("Payment Successfull"); 
			else
				System.out.println("insertion failed"); 
			k.close(); 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()); 
		} 
		
	}
	protected int getPaymentId()
	{
		return payment_id;
	}
	protected void changeStatus(String s)
	{
		status=s;
		
		try
		{ 
			String sql="update Payment set status='"+s+"' where payment_id="+this.payment_id+"";
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m == 1) 
				System.out.println("Payment Status has been changed"); 
			else
				System.out.println("insertion failed"); 
			k.close(); 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()+"this is the error"); 
		} 
		
	}
	
	protected String getPaymentStatus()
	{
		return status;
	}
	protected String getPaymentMode()
	{
		return payment_mode;
	}
	protected int getCustomerId()
	{
		return customer_id;
	}
	protected void getPaymentDetails()
	{
		System.out.println("Payment Id :"+payment_id);
		System.out.println("Customer Id :"+customer_id);
		System.out.println("Amount :"+amount);
		System.out.println("Payment Date :"+payment_date);
		System.out.println("Payment Status :"+status);
		System.out.println("Payment mode :"+payment_mode);
	}
}
   
