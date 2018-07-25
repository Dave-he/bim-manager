package com.mjwise.demo.repository.file;

import com.mjwise.demo.entity.file.ImageFile;
import com.mjwise.demo.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageFileRepository extends BaseRepository<ImageFile,String> {

	List<ImageFile> findAllByUsedType(String usedType);

}