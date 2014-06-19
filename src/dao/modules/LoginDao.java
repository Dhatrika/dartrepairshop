package dao.modules;

public interface LoginDao {
	
	public String passwordValidate(String pwd, String emailAddress) throws Exception;
	public void savePassword(String pwd,int userId) throws Exception;
	public Integer getEmailAddress(String emailAddress) throws Exception;
	public void saveEmailAddress(String emailAddress,int userId) throws Exception;
	public String getUserEmailAddress(int userId) throws Exception;

}
