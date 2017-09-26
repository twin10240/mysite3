package com.bigdata2017.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bigdata2017.mysite.service.GalleryService;
import com.bigdata2017.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImageList();
		model.addAttribute("list", list);
		
		return "gallery/index";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "comment", required = true, defaultValue = "") String comment, 
						 @RequestParam("file") MultipartFile file, Model model){
		
		galleryService.restore(comment, file);

		return "redirect:/gallery"; 
	}
}
