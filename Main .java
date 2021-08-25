import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class Main{
		protected static int ticket_id=1000;
		protected static int customer_id=1;
		protected static int train_id=5000;
		protected static int payment_id=3210;
		protected static int p=0;
		protected static int tr=0;
		protected static int ti=0;
		protected static int c=0;
		static int balance;
	protected static void enterTrainDetails(Train[] train)
	{
		Scanner sc=new Scanner(System.in);
		int id;
	    String name;
		String sou;
		String desti;
		int amt;
      
        id=generateTrainId();
        System.out.println("Enter Train name:");
        name=sc.nextLine();
        System.out.println("Enter Train source:");
        sou=sc.next();
        sou.toLowerCase();
        System.out.println("Enter Train destination:");
        desti=sc.next();
        desti=desti.toLowerCase();
        System.out.println("Enter number of seats in train:");
        int s=sc.nextInt();
        System.out.println("Enter date in the format of yyyy-mm-dd:");
        String date=sc.next();
        String[] z=date.split("-");
        try {
        LocalDate d=LocalDate.of(Integer.parseInt(z[0]), Integer.parseInt(z[1]), Integer.parseInt(z[2]));
        System.out.println("Enter the cost of each ticket:");
        amt=sc.nextInt();
        train[tr++]=new Train(id,name,sou,desti,s,d,amt);
        train[tr-1].getTrainDetails();
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        	return;
        }
        		
		
		//sc.close();
		
	}
	protected static int generateTicketId()
	{
		return ++ticket_id;
	}
	protected static int generateCustomerId()
	{
		return ++customer_id;
	}
	protected static int generateTrainId()
	{
		return ++train_id;
	}
	protected static int generatePaymentId()
	{
		return ++payment_id;
	}


	protected static int enterCustomerDetails(Customer[] customer)
	{
		int c_id;
		String name;
		String sex;
		int age;
		String password;
		Scanner sc=new Scanner(System.in);
		
        c_id=generateCustomerId();
        
        System.out.println("Enter Customer name:");
        name=sc.nextLine();
        System.out.println("Enter Customer gender:");
        sex=sc.next();
        System.out.println("Enter Customer age:");
        age=sc.nextInt();
        System.out.println("Enter password:");
        password=sc.next();
        customer[c++]=new Customer(c_id,name,sex,age,password);
        customer[c-1].getCustomerDetails();
       return c_id;
	}
	protected static boolean checkTrainId(int id)
	{
		String url = "jdbc:mysql://localhost:3306/railway"; 
		String user = "root"; 
		String pass = "rishi@5251"; 

		String sql = "select * from Train where train_id="+id+""; 
		Connection con=null; 
		try
		{ 
			
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			con = DriverManager.getConnection(url,user,pass); 

			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) 
			return true;
			else
				return false;
		} 
		catch(Exception ex) 
		{ 
			System.err.println("Error occured"); 
		}
		return false; 
	}
	protected static int getRate(int id)
	{
		String url = "jdbc:mysql://localhost:3306/railway"; 
		String user = "root"; 
		String pass = "rishi@5251"; 

		String sql = "select * from Train where train_id="+id+""; 
		Connection con=null; 
		try
		{ 
	
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			con = DriverManager.getConnection(url,user,pass); 

			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) 
			return rs.getInt(8);
			else
				return 0;
		} 
		catch(Exception ex) 
		{ 
			System.err.println("Error occured"); 
		}
		return 0; 
		
	}
	protected static Date getDate(int id)
	{
		String url = "jdbc:mysql://localhost:3306/railway"; 
		String user = "root"; 
		String pass = "rishi@5251"; 

		String sql = "select * from Train where train_id="+id+""; 
		Connection con=null; 
		try
		{ 

			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			con = DriverManager.getConnection(url,user,pass); 

			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) 
			return rs.getDate(7);
			
		} 
		catch(Exception ex) 
		{ 
			System.err.println("Error occured"); 
		}
		return null;
			
	}
	protected static int getSeat(int id)
	{
		String url = "jdbc:mysql://localhost:3306/railway"; 
		String user = "root"; 
		String pass = "rishi@5251"; 

		String sql = "select * from Train where train_id="+id+""; 
		Connection con=null; 
		int t=0;
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			con = DriverManager.getConnection(url,user,pass); 

			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) 
			t= rs.getInt(6);
			else
				return 0;
		} 
		catch(Exception ex) 
		{ 
			System.err.println("Error occured"); 
		}
		
		try
		{ 
			int k=t-1;
			sql="update Train set remaining_seat="+k+" where train_id="+id+"";
		
			//DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
			Class.forName("com.mysql.jdbc.Driver"); 
			//Reference to connection interface 
			con = DriverManager.getConnection(url,user,pass); 

			Statement st = con.createStatement();
			int m=st.executeUpdate(sql);
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return t; 
		
	}
	
	protected static void enterTicketDetails(Ticket[] ticket,Train[] train,Customer[] customer,Payment[] payment)
	{
		Scanner sc=new Scanner(System.in);
		String conf_status;
		int amt=0;
		int p_id=0;
		String pass;
		System.out.println("Want to enquary about train?y/n");
		String response=sc.next();
		response=response.toLowerCase();
		int flag=0;
		if(response.equals("y"))
		{
			flag=trainEnquary(train);
			if(flag==0)
			{
				return;
			}
		}
		System.out.print("Enter CustomerID:");
		int c_id=sc.nextInt();
		 flag=0;
		 int j;
		 for(j=0;j<c;j++)
	        {
	        	if(c_id==customer[j].getCustomerId())
	        	{
	        		System.out.println("Enter Password");
	        		pass=sc.next();
	        		if(customer[j].getPassword().equals(pass))
                    {	
	        		flag=1;
	        		break;
                    }
                    else
                    {
                    	System.out.println("Wrong Password!");
                    	return;
                    }
	        	}
	        }
		 if(flag==0)
		 {
			System.out.println("Your are not a registered customer");
			System.out.println("Want to register?y/n");
			String option=sc.next();
			option=option.toLowerCase();
			if(option.equals("y"))
			{
				c_id=enterCustomerDetails(customer);
				flag=1;
			}
			else
			{
				return ;
			}
		 }
		 if(flag==1)
		 {
			 
			
			    int t_id= generateTicketId();
				System.out.println("Enter TrainID:");
				int train_id=sc.nextInt();
				int i;
				int f=0;
				
				
				
					if(checkTrainId(train_id))
					{
						f=1;
						amt=getRate(train_id);
						
					
						
					}
					else
					{
						System.out.println("No Train available");
						return ;
					}
				int se=0;
				
				for(i=0;i<tr;i++)
				{
					if(train_id==train[i].getTrainId())
					{
						se=train[i].getSeat();
					}
				}
				if(se>0)
				{
					
					System.out.println("Enter the date for travelling in the format of yyyy-mm-dd:");
				String ticket_date=sc.next();//date of traveling
			    String[] date=ticket_date.split("-");
			    LocalDate t_date=LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
				int seat_no=se;
				Date d=getDate(train_id);
				int day=d.getDate();
				int month=d.getMonth();
				int year=d.getYear();
				LocalDate new_date=LocalDate.of(year,month,day);
					int k=t_date.compareTo(new_date);
					
					if(checkTrainId(train_id) && k==0)
					{
						f=1;
						amt=getRate(train_id);
						
					}
				
				if(f==0)
				{
					System.out.println("This train is not available for this date");
					return ;
				}
				p_id=enterPaymentDetails(payment,customer,c_id,amt);
					if(p_id>1000)
					{
						 conf_status="done";
					}
					else
					{
						conf_status="not done";
					}
					
					
						
						try {
					ticket[ti++]=new Ticket(t_id,c_id,train_id,p_id,t_date,seat_no,conf_status);
					train[ti-1].decreseSeats();
					getSeat(train_id);
					ticket[ti-1].getTicketDetails();
					
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage()+" got an error");
						return;
					}
				}
				else
				{
					System.out.println("No seat Available!");
				}
		 }
		
		
	}
	protected static int enterPaymentDetails(Payment[] payment,Customer[] customer,int c_id,int amt)	
	{
		
		int p_id;
		
		String status;
		String mode;
		Scanner sc=new Scanner(System.in);
		
        		
        		p_id=generatePaymentId();
        		
        		System.out.println("Enter Payment mode:");
				mode=sc.next();
				status=getPayment(amt);
				payment[p++]=new Payment(p_id,c_id,amt,status,mode);
				payment[p-1].changeStatus(status);
				payment[p-1].getPaymentDetails();
				
				return p_id;
				
        	
        
		
		
		
	}
	protected static String getPayment(int m)
	{
		balance=balance+m;
		return "done";
	}
	
	protected static int trainEnquary(Train[] train)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the source:");
		String so=sc.next();
		String sou=so.toLowerCase();
		System.out.println("Enter Destination:");
		String de=sc.next();
		String dest=de.toLowerCase();
		int flag=0;
		
		System.out.println("Enter date in the format of yyyy-mm-dd:");
	    String date=sc.next();
	    String[] s=date.split("-");
	    LocalDate d=LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
	    int k;
		//int flag=0;
		try {
		for(int i=0;i<tr;i++)
		{
			k=d.compareTo(train[i].getDate());
			if((train[i].getSource().equals(sou)) && (train[i].getDestination().equals(dest)) && (k==0))//this if block is running despite of flase conditions
			{
				
				flag=1;
				train[i].getTrainDetails();
			}
		}
		if(flag==0)
		{
			System.out.println("No Train Available!");
		}
		}
		
		catch(NullPointerException e)
		{
			System.out.println("No train Available for this time slot");
		}
		return flag;
		
		
	}
	protected static void enterSomeTrainDetails(Train[] train)
	{
		train[tr++]=new Train(1001,"exp1","bhilwara","jaipur",500,LocalDate.of(2020,11,11),800);
		train[tr++]=new Train(1002,"exp2","jaipur","delhi",600,LocalDate.of(2020,9,11),500);
        train[tr++]=new Train(1003,"exp3","ajmer","alwar",500,LocalDate.of(2020,10,23),600);
        train[tr++]=new Train(1004,"exp4","bhagalpur","jabalpur",600,LocalDate.of(2020,8,17),900);
        train[tr++]=new Train(1005,"exp5","hyderabad","mumbai",900,LocalDate.of(2020,06,20),700);
        train[tr++]=new Train(1006,"exp6","delhi","mumbai",1000,LocalDate.of(2020,8,8),800);
        train[tr++]=new Train(1007,"exp7","delhi","jaipur",600,LocalDate.of(2020,10,10),1100);
        train[tr++]=new Train(1008,"exp8","indore","hyderabad",400,LocalDate.of(2020,12,23),500);
        train[tr++]=new Train(1009,"exp9","kota","indore",500,LocalDate.of(2020,11,23),600);
        train[tr++]=new Train(1010,"exp10","bhopal","kota",400,LocalDate.of(2021,10,20),900);
        train[tr++]=new Train(1011,"exp11","jabalpur","bhilwara",800,LocalDate.of(2021,1,12),800);
        train[tr++]=new Train(1012,"exp12","indore","mumbai",400,LocalDate.of(2020,12,13),500);
        train[tr++]=new Train(1013,"exp13","bhilwara","hyderabad",600,LocalDate.of(2020,11,27),1100);
        train[tr++]=new Train(1014,"exp14","bangalore","delhi",800,LocalDate.of(2021,2,10),1600);
        train[tr++]=new Train(1015,"exp15","kota","bangalore",700,LocalDate.of(2020,12,19),1500);
		
	}
 	public static void main(String args[])
 	{
 		int n;
 		
 		Train[] train = new Train[2000];
 		Customer[] customer =new Customer[1000];
 		Ticket[] ticket = new Ticket[1500];
 		Payment[] payment = new Payment[1500];
 		Scanner sc=new Scanner(System.in);
 		enterSomeTrainDetails(train);
 				
 		while(true)
 		{
 		System.out.println("********Choose from the following********");
 		System.out.println("1.Administration");
 		System.out.println("2.Enter Customer Details");
 		System.out.println("3.Book Ticket");
 		
 		System.out.println("4.Enquary train availability");
 		System.out.println("5.Quit"); 		
 		System.out.print("Enter your choice:");
 		n=sc.nextInt();
 		
 		switch(n)
 		{
 		case 1:
 		String s1,s2,s3;
		int flag=0;
		
		
		
			
		  System.out.println("Enter username : ");
		  s2 = sc.next();
		  System.out.println("enter password : ");
		  s3 = sc.next();
		  

			String url = "jdbc:mysql://localhost:3306/railway"; 
			String user = "root"; 
			String pass = "rishi@5251"; 
			//Inserting data using SQL query 
			String sql = "select * from administrator where pass_word='"+s3+"'"; 
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
				{
				if(s2.equals(rs.getString(1)) && s3.equals(rs.getString(2)) )
					{
						System.out.println("Hello Sir!");
						flag=1;
						break;
					}
				}
				if(flag==0)
					System.err.println("Data not Found!"); 
				
				con.close(); 
			} 
			catch(Exception ex) 
			{ 
				System.err.println("Error occured!"); 
			} 
			
		  

		  if(flag==1)
		    {
		      while(true)	
		      { 
		    	 
		    	  System.out.println("Press 1 to Add a new train ");
		    	  System.out.println("Press 2 to Enquary the total balance");
		    	  System.out.println("Enter any other numeric key to exit ");
		          int s4 = sc.nextInt();
		         
		          if(s4==1)
		          {
			        enterTrainDetails(train);
		          }
		          else if(s4==2)
		          {
		        	  System.out.println("Balance="+balance);
		          }
		          else
		          {
		        	  
		        	 break; 
		          }
		       }
		    }
		
		break;
 			
 		case 2:enterCustomerDetails(customer);
 		break;
 		case 3:enterTicketDetails(ticket,train,customer,payment);
 		break;
 		case 4:trainEnquary(train);
 		break;
 		case 5:System.out.println("You logged out successfully!");
 			System.exit(0);
 		break;
 		default:System.out.println("Wrong input!");
 		break;
 		
 		
 		
 	     //sc.close();
 		}
 		
 		
 		
 		
 		
 		
 		}
 		//sc.close();





 	}


}
