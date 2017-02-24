package Dynamics;
import com.microsoft.sqlserver.jdbc.*;

import Discovery.Helper;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JDBCConnection {
	
	static Log logger = LogFactory.getLog(JDBCConnection.class);
	
	
	public static String GetRedemtionID(String EmailId){
			
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String RedemtionID = null;
			Helper Config = new Helper();
			
			try {
				logger.debug("Trying to connect to the dynamics data base.");
				// Establish the connection. 
				SQLServerDataSource ds = new SQLServerDataSource();
				if(Config.GetConfigParameter("IntegratedSecurity").toString().equals("Yes")){
					ds.setIntegratedSecurity(true);
				}else{
					ds.setUser(Config.GetConfigParameter("UserName").toString());
					ds.setPassword(Config.GetConfigParameter("Password").toString());
				}
				ds.setServerName(Config.GetConfigParameter("ServerName").toString());
				ds.setPortNumber(Integer.parseInt(Config.GetConfigParameter("Serverport").toString()));
				ds.setDatabaseName(Config.GetConfigParameter("DataBaseName").toString());
				con = ds.getConnection();
				 
				logger.debug("Connection to the dynamics database was successful.");
		        	
        		String query = "select TOP 1 [adx_invitationCode],CreatedOn from [mctorg2_MSCRM].[dbo].[adx_invitationBase] "
        				+ "where [adx_inviteContact] in ("
        				+ "select ContactId from contact "
        				+ "where emailaddress1 = '" + EmailId + "') "
        				+ "order by CreatedOn desc";
        		stmt = con.createStatement();
        		logger.debug((query));
        		rs = stmt.executeQuery(query);
	        	logger.debug("Executed the SQL query successfully on the dynamics data base.");
	        	// Iterate through the data in the result set and display it.
	        	while (rs.next()) {
	        		RedemtionID = rs.getString("adx_invitationCode");
	        		logger.info("redemtion Id from dynamics database: " + RedemtionID);
	        		logger.info("Date when redemtion Id created in dynamics database: " + rs.getString("CreatedOn"));
	            	return RedemtionID;      		
	        	}
		        	logger.info("redemtion Id from dynamics database: " + RedemtionID);
		        	return RedemtionID;
		        }
		        
				// Handle any errors that may have occurred.
		    	catch (Exception e) {
		    		e.printStackTrace();
		    		logger.error(e.toString());
		    		return RedemtionID;
		    	}
		
		   	finally {
		    		if (rs != null) try { rs.close(); } catch(Exception e) {}
		    		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
		    		if (con != null) try { con.close(); } catch(Exception e) {}
		    	}
	}

		
	
	public static ResultSet ExecuteCommandInMicrosoftsqlServer(String Query, String Servername, int ServerPortNumber,String DataBaseName, String UserName, String Password){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Helper Config = new Helper();
		
		try {
			logger.debug("Trying to connect to the dynamics data base.");
			// Establish the connection. 
			SQLServerDataSource ds = new SQLServerDataSource();
			if(Config.GetConfigParameter("IntegratedSecurity").toString().equals("Yes")){
				ds.setIntegratedSecurity(true);
			}else{
				ds.setUser(UserName);
				ds.setPassword(Password);
			}
			
			ds.setServerName(Servername);
			ds.setPortNumber(ServerPortNumber);
			ds.setDatabaseName(DataBaseName);
			con = ds.getConnection();
			logger.debug("Connection to the dynamics database was successful.");
			stmt = con.createStatement();
    		logger.debug((Query));
    		rs = stmt.executeQuery(Query);
        	logger.debug("Executed the SQL query successfully on the dynamics data base.");
        	return rs;
			}
			// Handle any errors that may have occurred.
	    	catch (Exception e) {
	    		e.printStackTrace();
	    		logger.error(e.toString());
	    		return rs;
	    	}
	
	   	finally {
	    		if (rs != null) try { rs.close(); } catch(Exception e) {}
	    		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	    		if (con != null) try { con.close(); } catch(Exception e) {}
	    	}
	}
	
	public static ResultSet ExecuteCommandInMicrosoftsqlServer(String Query, String Servername, int ServerPortNumber,String DataBaseName){
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			logger.debug("Trying to connect to the dynamics data base.");
			// Establish the connection. 
			SQLServerDataSource ds = new SQLServerDataSource();
			ds.setIntegratedSecurity(true);
			ds.setServerName(Servername);
			ds.setPortNumber(ServerPortNumber);
			ds.setDatabaseName(DataBaseName);
			con = ds.getConnection();
			logger.debug("Connection to the dynamics database was successful.");
			stmt = con.createStatement();
    		logger.debug((Query));
    		rs = stmt.executeQuery(Query);
        	logger.debug("Executed the SQL query successfully on the dynamics data base.");
        	/*while (rs.next()) {
        		System.out.println(rs.getString("<Column Name>"));
        	}*/
        	
        	return rs;
			}
			// Handle any errors that may have occurred.
	    	catch (Exception e) {
	    		e.printStackTrace();
	    		logger.error(e.toString());
	    		return rs;
	    	}
	
	   	finally {
	    		if (rs != null) try { rs.close(); } catch(Exception e) {}
	    		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	    		if (con != null) try { con.close(); } catch(Exception e) {}
	    	}
		
	}
}



