package io.github.h800572003.hcp;

import java.io.Serializable;

import io.github.h800572003.hcp.exception.HcpCodeExcepton;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.IHcpMethod;
import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.GetHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiMethod;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.put.IHcpPut;

public interface IHcpService {

	<O extends Serializable, I extends IHcpMethod> O execute(HcpApiMethod<I, O> api, I cmd) throws HcpCodeExcepton;

	GetHcpInfo execute(IHcpGet hcpGet) throws HcpCodeExcepton;

	BaseHcpInfo execute(IHcpDelete hpDelete) throws HcpCodeExcepton;

	BaseHcpInfo execute(IHcpPut hcpPut) throws HcpCodeExcepton;

	void close();

}