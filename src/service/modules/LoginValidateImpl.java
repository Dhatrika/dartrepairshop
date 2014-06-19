package service.modules;

import dao.modules.LoginDao;

public class LoginValidateImpl implements LoginValidate{

	private LoginDao loginDao;
	
	public LoginValidateImpl(){
		
	}
	
	public LoginValidateImpl(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public String passwordValidate(String pwd, String emailAddress) throws Exception{
		return(loginDao.passwordValidate(pwd,emailAddress));
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public Integer getEmailAddress(String emailAddress) throws Exception {
		return loginDao.getEmailAddress(emailAddress);
	}

	@Override
	public void saveEmailAddress(String emailAddress, int userId)
			throws Exception {
		loginDao.saveEmailAddress(emailAddress, userId);
		
	}

	@Override
	public void savePassword(String pwd, int userId) throws Exception {
		loginDao.savePassword(pwd, userId);
	}


	@Override
	public String getUserEmailAddress(int userId) throws Exception {
		return loginDao.getUserEmailAddress(userId);
	}



}
