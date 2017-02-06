package Dynamics;
import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class JDBCConnection {
	static Log logger = LogFactory.getLog(JDBCConnection.class);
	public static String ConntectDB(String EmailId){
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String RedemtionID = null;
	
	try {
		logger.debug("Trying to connect to the dynamics data base.");
		// Establish the connection. 
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("jm");
		ds.setPassword("tonto123");
		//ds.setIntegratedSecurity(true);
		ds.setServerName("10.53.10.144");
		ds.setPortNumber(1433); 
		ds.setDatabaseName("mctorg2_MSCRM");
		con = ds.getConnection();
		 
		logger.debug("Connection to the dynamics database was successful.");
        	// Execute a stored procedure that returns some data.
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
}



