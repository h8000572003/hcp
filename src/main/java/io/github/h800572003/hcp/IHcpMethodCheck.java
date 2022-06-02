package io.github.h800572003.hcp;

import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.put.IHcpPut;

public interface IHcpMethodCheck {
	public void check(IHcpGet api);

	public void check(IHcpDelete api);

	public void check(IHcpPut api);

}