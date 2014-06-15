package service.modules;

import common.modules.Owner;
import dao.modules.OwnerDao;

public class OwnerServiceImpl implements OwnerService{
	
	private OwnerDao ownerDao;
	

	public OwnerServiceImpl(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}


	public OwnerDao getOwnerDao() {
		return ownerDao;
	}


	public void setOwnerDao(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}


	@Override
	public Owner getOwner(String emailAddress) throws Exception {
		return ownerDao.getOwner(emailAddress);
	}

}
