import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class Customer{
	int customer_id;
	String customer_name;
	String gender;
	int age;  
	String password;
	
	String url="jdbc:mysql://localhost:3306/railway"; 
	String user="root";//username
	String pass="rishi@5251";//password
	
	
	public Customer(int id1,String c_name,String sex, int n,String password)
	{
		customer_id=id1;
		customer_name=c_name;
		gender=sex;
		age=n;
		this.password=password;
		String sql="insert into Customer values("+customer_id+",'"+customer_name+"','"+gender+"',"+age+",'"+password+"');";
		try
		{ 
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m == 1) 
				System.out.println("Data inserted Successfully"); 
			else
				System.out.println("insertion failed"); 
			k.close(); 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()); 
		} 

	}
	/*public void CustomerTicket(int id)
	{
		ticket_id=id;
		
		try
		{ 
			String sql="update Payment set ticket_id='"+id+"' where customer_id="+this.customer_id+"";
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m == 1) 
				System.out.println("insertion Successfully"); 
			else
				System.out.println("insertion failed"); 
			k.close(); 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()); 
		} 
	}*/
	public String getPassword()
	{
		return password;
	}
	/*public void CustomerPayment(int id)
	{
		payment_id=id;
		try
		{ 
			String sql="update Payment set payment_id='"+id+"' where customer_id="+this.customer_id+"";
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			Connection k = DriverManager.getConnection(url,user,pass); 

			Statement st = k.createStatement(); 
			int m = st.executeUpdate(sql); 
			if (m == 1) 
				System.out.println(st.executeQuery(sql)); 
			else
				System.out.println("insertion failed"); 
			k.close(); 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex.getMessage()); 
		} 
	}
	protected  int getCustomerTicketId()
	{
		return ticket_id;
	}*/
	protected  int getCustomerId()
	{
		return customer_id;
	}
	protected void getCustomerDetails()
	{
		System.out.println("CustomerId="+customer_id);
		System.out.println("Customer Name="+customer_name);
		System.out.println("Gender="+gender);
		System.out.println("Age="+age);
		/*if(ticket_id==0)
			System.out.println("Ticket not taken");
		else
			System.out.println("Ticket ID="+ticket_id);
		if(payment_id==0)
			System.out.println("Payment not done");
		else
			System.out.println("Payment ID="+payment_id);
		System.out.println("");
		*/
	}
	
	
}
