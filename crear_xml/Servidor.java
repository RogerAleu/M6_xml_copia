
import com.jcraft.jsch.*;
import com.jcraft.jsch.Session;

public class Servidor {
	
	private static String user = "roger";
	private static String password = "dam2122";
	private static String Host = "192.168.138.139";
	private static int port = 22;
	private static ChannelSftp sftpChannel = null;
	
	public Servidor() {
		
		try {
	        JSch jsch = new JSch();
	        Session session = jsch.getSession(user, Host, port);
	        session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.setConfig("kex", "diffie-hellman-group1-sha1"); 
	        session.connect();
	        sftpChannel = (ChannelSftp) session.openChannel("sftp");
	        sftpChannel.connect();
	           
	    } catch (JSchException e) {
	        e.printStackTrace();
	    }	
	}
	
	public static ChannelSftp conectarse() {
		
		if (sftpChannel==null) {
			new Servidor();
		}
		return sftpChannel;
	}
}

