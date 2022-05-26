package io.github.h800572003.hcp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.h800572003.hcp.exception.HcpCodeExcepton;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.delete.HcpDelete;
import io.github.h800572003.hcp.method.get.GetHcpInfo;
import io.github.h800572003.hcp.method.get.HcpGet;
import io.github.h800572003.hcp.method.put.HcpPut;
import io.github.h800572003.hcp.utils.HcpUtil;
import lombok.extern.slf4j.Slf4j;

@Disabled
@Slf4j
class HcpServiceTest {

	IHcpService hcpService;

	@BeforeEach
	public void init() {
		HcpOption option = new HcpOption();
		option.setRest("url");
		option.setUser("user");
		option.setPwd("pwd");

		this.hcpService = new HcpService(option);
	}

	@Test
	void test_execute_then_get_then_size() throws HcpCodeExcepton {

		HcpGet hcpDownloadInfo = new HcpGet();
		String restPath = HcpUtil.toRestPath("x.pdf", "1", "2", "3", "4");
		hcpDownloadInfo.setPath(restPath);

		GetHcpInfo execute = hcpService.execute(hcpDownloadInfo);
		String size = execute.getSize();

	}

	@Test
	void test_execute_then_delete() throws HcpCodeExcepton {
		HcpDelete cmd = new HcpDelete();
		String restPath = HcpUtil.toRestPath("x.pdf", "1", "2", "3", "4");
		cmd.setPath(restPath);

		BaseHcpInfo execute = hcpService.execute(cmd);

	}

	@Test
	void test_execute_then_put() throws IOException, URISyntaxException, HcpCodeExcepton {

		String restPath = HcpUtil.toRestPath("x.pdf", "1", "2", "3", "4");
		URL resource = HcpServiceTest.class.getClassLoader().getResource("test.zip");

		byte[] myByte = FileUtils.readFileToByteArray(new File(resource.toURI()));

		HcpPut cmd = new HcpPut(restPath, myByte);

		BaseHcpInfo execute = hcpService.execute(cmd);

	}

	void close() {
		hcpService.close();
	}

}
