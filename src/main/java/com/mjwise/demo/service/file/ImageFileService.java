package com.mjwise.demo.service.file;

import com.mjwise.demo.entity.file.ImageFile;
import com.mjwise.demo.repository.file.ImageFileRepository;
import com.mjwise.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageFileService extends BaseService<ImageFile,String> {

	@Autowired
	ImageFileRepository imageFileRepository;

	public List<ImageFile> findAllByUsedType(String usedType){
		return imageFileRepository.findAllByUsedType(usedType);
	}

}