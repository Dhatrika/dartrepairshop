package service.modules;

import common.modules.Owner;

public interface OwnerService {
	public Owner getOwner(String emailAddress) throws Exception;
}
