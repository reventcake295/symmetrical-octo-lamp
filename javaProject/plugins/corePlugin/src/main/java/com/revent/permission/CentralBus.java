package com.revent.permission;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;

public class CentralBus {//the central storage of the luckperms api

	public static LuckPermsApi LuckpermsApi;
	
	public CentralBus() {
		LuckpermsApi = LuckPerms.getApi();
	}
}
