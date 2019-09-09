package com.atguigu.fdfs.test;

import java.io.IOException;
import java.net.URL;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class UploadTest {
	
	public static void main(String[] args) throws IOException, MyException {
		
		//1.声明tracker配置文件在类路径下的路径
		String confFilePath = "/tracker.conf";
		
		//2.根据类路径下的路径获取tracker配置文件的绝对物理路径
		URL url = UploadTest.class.getResource(confFilePath);
		
		//D:\workstation\Station180725\Pro07_FastDFS\src\main\resources\tracker.conf
		String confFileAbsolutePath = url.getPath();
		
		//3.根据tracker配置文件的绝对物理路径进行初始化
		ClientGlobal.init(confFileAbsolutePath);
		
		//4.创建StorageClient对象
		//①创建TrackerClient对象，调用无参构造器即可，无参构造器内部通过静态资源获取初始化信息
		//this.tracker_group = ClientGlobal.g_tracker_group;
		TrackerClient trackerClient = new TrackerClient();
		
		//②获取TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		
		//③StorageServer设置为null即可
		StorageServer storageServer = null;
		
		//④创建StorageClient对象
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
		//5.执行上传
		//①要上传的文件的路径
		String local_filename = "aaa.jpg";
		
		//②文件扩展名
		String file_ext_name = "jpg";
		
		//③文件元数据
		NameValuePair[] meta_list = null;
		
		//④执行上传
		String[] resultArray = storageClient.upload_file(local_filename, file_ext_name, meta_list);
		
		//6.获取上传结果
		String groupName = resultArray[0];
		String remoteFileName = resultArray[1];
		
		System.out.println("groupName="+groupName);
		System.out.println("remoteFileName="+remoteFileName);
	}

}
