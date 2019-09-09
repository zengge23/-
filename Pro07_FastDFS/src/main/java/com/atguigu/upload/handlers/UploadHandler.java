package com.atguigu.upload.handlers;

import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.upload.utils.StringUtils;

@Controller
public class UploadHandler {
	
	@Autowired
	private StorageClient storageClient;
	
	@RequestMapping("/upload")
	public String doUpload(@RequestParam("picture") MultipartFile picture, Model model) throws Exception {
		
		//1.获取当前上传文件的原始文件名
		String originalFilename = picture.getOriginalFilename();
		System.out.println("originalFilename="+originalFilename);
		
		//2.获取当前上传文件的字节数组
		byte[] file_buff = picture.getBytes();
		
		//3.获取扩展名
		String file_ext_name = StringUtils.getExtName(originalFilename);
		
		//4.执行上传
//		StorageClient  storageClient = new StorageClient(new TrackerClient().getConnection(), null);
		String[] resultArray = storageClient.upload_file(file_buff, file_ext_name, null);
		
		String groupName = resultArray[0];
		String remoteFileName = resultArray[1];
		
		System.out.println("groupName="+groupName);
		System.out.println("remoteFileName="+remoteFileName);
		
		model.addAttribute("groupName", groupName);
		model.addAttribute("remoteFileName", remoteFileName);
		
		return "target";
	}

}
