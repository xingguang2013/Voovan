package org.voovan.test.network.ssl;

import org.voovan.network.SSLManager;
import org.voovan.network.aio.AioServerSocket;
import org.voovan.network.filter.StringFilter;
import org.voovan.network.messagesplitter.LineMessageSplitter;
import org.voovan.test.network.ServerHandlerTest;

public class AioSSLServer  {

	public static void main(String[] args) throws Exception {
//		SSLManager sslManager = new SSLManager("SSL",false); //单向认证
		SSLManager sslManager = new SSLManager("SSL");
		String certFile = System.getProperty("user.dir")+"/Network/src/test/java/org/voovan/test/network/ssl/ssl_ks";
		sslManager.loadCertificate(certFile, "passStr","123123");

		AioServerSocket serverSocket = new AioServerSocket("127.0.0.1",2031,1000000);
		serverSocket.setSSLManager(sslManager);
		serverSocket.handler(new ServerHandlerTest());
		serverSocket.filterChain().add(new StringFilter());
		serverSocket.messageSplitter(new LineMessageSplitter());
		serverSocket.start();
	}
}
