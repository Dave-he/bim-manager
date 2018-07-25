package com.mjwise.demo.controller.data.file;

import com.alibaba.fastjson.JSONObject;
import com.mjwise.demo.entity.file.ImageFile;
import com.mjwise.demo.service.file.ImageFileService;
import com.mjwise.demo.system.response.ResponseUtil;
import com.mjwise.demo.system.utils.SystemLogger;
import com.mjwise.demo.system.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images/")
public class ImageFileController {

	@Autowired
	ImageFileService imageFileService;

	/**
	 * 文件根路径
	 */
	@Value("${web.file.path}")
	public String fileRootPath;

	@PostMapping("/upload")
	public JSONObject upload(@RequestParam("file") MultipartFile file){
		String filePath = "/images";
		String fileName = file.getOriginalFilename();
		File file1 = new File(fileRootPath + filePath);
		if(!file1.exists()){
			file1.mkdirs();
		}
		try{
			String suffix = fileName.substring(fileName.lastIndexOf(".",fileName.length()));
			fileName = UUIDUtil.getUUID() + suffix;
			InputStream in = file.getInputStream();
			FileOutputStream out = new FileOutputStream(fileRootPath + filePath + "/"+fileName);
			byte[] buffer = new byte[1024];
			int pos = 0;
			while ((pos = in.read(buffer)) != -1) {
				out.write(buffer, 0, pos);
				out.flush();
			}
			in.close();
			out.close();
			ImageFile imageFile = new ImageFile();
			imageFile.setOriginalName(file.getOriginalFilename());
			imageFile.setPresentName(fileName);
			imageFile.setSuffix(suffix);
			imageFile.setRootPath(fileRootPath+filePath+"/"+fileName);
			imageFile.setUploadDate(new Date());
			imageFile.setValidState(true);
			imageFile.setUsedType("index");
			imageFileService.save(imageFile);
			try{
				return ResponseUtil.getSuccessResponse(imageFile);
			}catch (Exception e){
				return ResponseUtil.getFailResponse(500,"文件上传失败");
			}
		}catch (IOException e){
			return ResponseUtil.getFailResponse(404,"文件不存在");
		}
	}

	/**
	 * 预览图片
	 * @param id
	 */
	@GetMapping("/{id}")
	public void viewImage(@PathVariable("id") String id, HttpServletResponse response){
		Optional<ImageFile> imageFile = imageFileService.findById(id);
		if(imageFile.isPresent()){
			File file = new File(imageFile.get().getRootPath());
			if(file.exists() && file.isFile()){
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					long size = file.length();
					byte[] temp = new byte[(int)size];
					fileInputStream.read(temp,0,(int)size);
					fileInputStream.close();
					response.getOutputStream().write(temp);
				} catch (FileNotFoundException e) {
					SystemLogger.logger.error("图片文件未找到");
				} catch (IOException e) {
					SystemLogger.logger.error("ID流读写错误");
				}
			}
		}
	}

	@GetMapping("/list")
	public JSONObject list(Model model){
		List<ImageFile> imageFileList = imageFileService.findAllByUsedType("index");
		//放在请求域中
		model.addAttribute("imgs",imageFileList);
		return ResponseUtil.getSuccessResponse(imageFileList);
	}

}
