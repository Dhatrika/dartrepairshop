package dao.modules;

public interface LoginDao {
	
	public boolean passwordValidate(String pwd, String emailAddress) throws Exception;
	public String getPassword() throws Exception;
	public void savePassword(String newPassword) throws Exception;
	public String getEmailAddress() throws Exception;
	public void saveEmailAddress(String emailAddr) throws Exception;

}
