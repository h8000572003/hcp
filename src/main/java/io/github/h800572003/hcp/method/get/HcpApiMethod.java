package io.github.h800572003.hcp.method.get;

import java.io.Serializable;

import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpCodeExcepton;
import io.github.h800572003.hcp.method.IHcpMethod;

public interface HcpApiMethod<I extends IHcpMethod, O extends Serializable> {
	O execute(I hcpMethod, IHcpContext hcpService) throws HcpCodeExcepton;

}
