package edu.ncist.wang.hkdf.bussiness.blo;

import edu.ncist.wang.hkdf.bussiness.bli.ServiceHandlable;

public abstract class AbstractService implements ServiceHandlable {

	public void ActionTypeError(){
		System.out.println("ActionType not found in Request process...");
	}

}
