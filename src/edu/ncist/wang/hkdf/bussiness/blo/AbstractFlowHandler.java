package edu.ncist.wang.hkdf.bussiness.blo;

import edu.ncist.wang.hkdf.bussiness.bli.FlowHandlable;

public abstract class AbstractFlowHandler implements FlowHandlable {

	public void ActionTypeError(){
		System.out.println("ActionType not found in Request process...");
	}

}
