package com.atguigu.upload.factory;

import java.net.URL;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.FactoryBean;

public class StorageClientFactory implements FactoryBean<StorageClient> {
	
	private String configLocation;
	
	//<property name="configLocation" value="classpath:tracker.conf"/>
	public void setConfigLocation(String configLocation) {
		
		if(configLocation == null || "".equals(configLocation)) {
			throw new RuntimeException("tracker.conf file is missing,please specify it like 'classpath:tracker.conf'!");
		}
		
		this.configLocation = configLocation.replace("classpath:", "/");
	}

	@Override
	public StorageClient getObject() throws Exception {

		// 1.声明tracker配置文件在类路径下的路径
		//使用配置文件中指定的路径，不写死
		//String confFilePath = "/tracker.conf";

		// 2.根据类路径下的路径获取tracker配置文件的绝对物理路径
		URL url = StorageClientFactory.class.getResource(configLocation);

		// D:\workstation\Station180725\Pro07_FastDFS\src\main\resources\tracker.conf
		String confFileAbsolutePath = url.getPath();

		// 3.根据tracker配置文件的绝对物理路径进行初始化
		ClientGlobal.init(confFileAbsolutePath);

		// 4.创建StorageClient对象
		// ①创建TrackerClient对象，调用无参构造器即可，无参构造器内部通过静态资源获取初始化信息
		// this.tracker_group = ClientGlobal.g_tracker_group;
		TrackerClient trackerClient = new TrackerClient();

		// ②获取TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();

		// ③StorageServer设置为null即可
		StorageServer storageServer = null;

		// ④创建StorageClient对象
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);

		return storageClient;
	}

	@Override
	public Class<?> getObjectType() {
		return StorageClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
