package dao.modules;

import common.modules.Owner;

public interface OwnerDao {
	public Owner getOwner(String emailAddress) throws Exception;
}
