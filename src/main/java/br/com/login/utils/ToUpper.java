package br.com.login.utils;

import br.com.login.v1.models.UserModel;

public class ToUpper {
	
	public static void upperUserModel(UserModel model) {
		model.setName(model.getName().trim().toUpperCase());
		model.setUsername(model.getUsername().trim().toUpperCase());
	}

}
