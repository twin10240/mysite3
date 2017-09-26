package com.bigdata2017.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bigdata2017.mysite.repository.GalleryDao;
import com.bigdata2017.mysite.vo.GalleryVo;
import com.bigdata2017.mysite.vo.GuestbookVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;

	private static String SAVE_PATH = "/bigdata/workspace/mysite3/webapp/assets/gallery";
	private static String PREFIX_URL = "/bigdata/workspace/mysite3/webapp/assets/gallery/images/";
	
	public List<GalleryVo> getImageList(){
		List<GalleryVo> list = galleryDao.getList();
		
		System.out.println(list);
		return list;
	}
	
	public boolean restore(String comments, MultipartFile multipartFile) {
		int count = -1;
		
		try {
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());	
			Long size = multipartFile.getSize();
			String saveFileName = genSaveFileName(extName);
			
			System.out.println("#######" + originalFileName);
			System.out.println("#######" + extName);
			System.out.println("#######" + size);
			System.out.println("#######" + saveFileName);
			
			writeFile(multipartFile, saveFileName);
			
			count = galleryDao.insert(new GalleryVo(comments, PREFIX_URL + saveFileName));
			
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
		return count == 1;
	}
	
	private void writeFile(MultipartFile multipartFile, String savFileName) throws IOException {
		byte[] fileData = multipartFile.getBytes();
		
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + savFileName);
		fos.write(fileData);
		fos.close();
	}

	private String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
}