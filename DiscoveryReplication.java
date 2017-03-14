package Discovery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class DiscoveryReplication {
	static Log logger = LogFactory.getLog(DiscoveryReplication.class);
	static Screen screen = new Screen();
	static int RepliationStatus = 0;
	static int TimerExpiry = 0;
	
	static boolean StartRepliation(){
		Helper Config = new Helper();
		TimerExpiry = Integer.parseInt(Config.GetConfigParameter("ReplicationTimeoutExpiry").toString());
		try {
			screen.click(LoginPage.ReplicateDiscoveryDatabase);
			Thread.sleep(60000);
			CheckReplicationStatus();
			
			if (RepliationStatus != 0){
				if (RepliationStatus == 6){
					logger.info("Replication completed sucessfully with the follwoing status code :" + RepliationStatus);
					return true;
				}else if(RepliationStatus == 8){
					logger.info("Replication completed with errors and the status code is:" + RepliationStatus);
					return true;
				}
				else{
					logger.error("Replication failed with the following status code :" + RepliationStatus);
					return false;
				}
			}else{
				logger.error("Replication did not complete and timed out after waiting for : " + Integer.parseInt(Config.GetConfigParameter("ReplicationTimeoutExpiry").toString()) + " minitues");
				return false;
			}
			
			
		} catch (FindFailed | NumberFormatException | InterruptedException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		
		
	}
	
	//This is not being used
	static void FinalReplicationStatus(){
		Helper Config = new Helper();
		String query = "SELECT TOP 1 a.[ReplicationResultID]" +
	      				",b.[ReplicationResult]" +
	      				",a.[StartDateTime]" +
	      				",a.[EndDateTime]" +
	      				",a.[NumTimeoutErrors]" +
	      				"FROM [DiscoverySIT].[dbo].[tblReplicationSession] a " +
	      				"left join tblreplicationresult b " +
	      				"ON b.[ReplicationResultID] = a.[ReplicationResultID]" +
	      				//"Where DBGuid = '55D58317-555D-4011-B85B-EC2D371DCE91'" +
	      				"Where DBGuid ='" + Config.GetConfigParameter("DiscoveryDBGuid").toString() +"'" +
	      				"Order by a.StartDateTime desc";
		
	    Connection conn = null;
        ResultSet rs = null;
        String url = 	"jdbc:jtds:sqlserver://" + 
        				Config.GetConfigParameter("DiscoveryServerName").toString() +";DatabaseName=" + 
        				Config.GetConfigParameter("DiscoveryDataBaseName").toString();
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, Config.GetConfigParameter("DiscoveryServerUserName").toString(), Config.GetConfigParameter("DiscoveryServerPassword").toString());
            Statement connection = conn.createStatement();
            rs = connection.executeQuery(query);
            
            	while (rs.next()) { 
	            	logger.info("Replication Status: " + rs.getString("ReplicationResultID"));
	            	logger.info("Replication Status: " + rs.getString("ReplicationResult"));
	            	logger.info("Replication Starttime: " + rs.getString("StartDateTime"));
	            	logger.info("Replication Endtime: " + rs.getString("EndDateTime"));
	            	logger.info("Replication TimeoutErrors: " + rs.getString("NumTimeoutErrors"));
	            }
            	
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { conn.close(); } catch(Exception e) {}
        }
		
	
	}
	
	public static void CheckReplicationStatus(){
		Helper Config = new Helper();
		String query = "SELECT TOP 1 [ReplicationResultID]" +
	      		",[StartDateTime]" +
	      		",[EndDateTime]" +
	      		",[NumTimeoutErrors]" +
	      		"FROM [DiscoverySIT].[dbo].[tblReplicationSession]" +
	      		"Where DBGuid ='" + Config.GetConfigParameter("DiscoveryDBGuid").toString() +"'" +
	      		"Order by StartDateTime desc";
		
	    Connection conn = null;
        ResultSet rs = null;
        String url = 	"jdbc:jtds:sqlserver://" + 
        				Config.GetConfigParameter("DiscoveryServerName").toString() +";DatabaseName=" + 
        				Config.GetConfigParameter("DiscoveryDataBaseName").toString();
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        try {
	        	Class.forName(driver);
	            conn = DriverManager.getConnection(url, Config.GetConfigParameter("DiscoveryServerUserName").toString(), Config.GetConfigParameter("DiscoveryServerPassword").toString());
	            Statement connection = conn.createStatement();
	            while (RepliationStatus == 0){
	            	rs = connection.executeQuery(query);
	            	if (rs.next()){
	            		RepliationStatus = rs.getInt("ReplicationResultID");
	            		logger.debug("Replication status :" + RepliationStatus);
	            		if (RepliationStatus != 0){
	            			logger.info("Replication Status: " + rs.getString("ReplicationResultID"));
	    	            	logger.info("Replication Starttime: " + rs.getString("StartDateTime"));
	    	            	logger.info("Replication Endtime: " + rs.getString("EndDateTime"));
	    	            	logger.info("Replication TimeoutErrors: " + rs.getString("NumTimeoutErrors"));
		            		break;
		            	}else{
		            		TimerExpiry = TimerExpiry - 1;
		            		logger.info("Replication wait Timer expiry Count: "+TimerExpiry);
		            		Thread.sleep(60000);
		            	}
	            	}	            	
	            }
	            	                        
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        } finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (conn != null) try { conn.close(); } catch(Exception e) {}
        }
		
	
	}
}
