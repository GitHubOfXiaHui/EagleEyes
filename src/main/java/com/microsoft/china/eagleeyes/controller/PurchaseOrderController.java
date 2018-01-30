package com.microsoft.china.eagleeyes.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.microsoft.china.eagleeyes.entity.PurchaseOrder;
import com.microsoft.china.eagleeyes.service.PurchaseOrderService;

@Controller
@RequestMapping("po")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<PurchaseOrder> pos = purchaseOrderService.findAll();
		model.addAttribute("pos", pos);
		return PO;
	}
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile poFile, RedirectAttributes model) throws IOException {
		purchaseOrderService.upload(poFile);
		model.addFlashAttribute("uploaded", true);
		return REDIRECT_LIST;
	}
	
	@RequestMapping(path = "/calculate", method = RequestMethod.GET)
	public String share(RedirectAttributes model) {
		purchaseOrderService.calculate();
		model.addFlashAttribute("calculated", true);
		return REDIRECT_LIST;
	}
	
	@RequestMapping(path = "/progress", method = RequestMethod.GET)
	public @ResponseBody int progress() {
		return purchaseOrderService.getProgress();
	}
	
	private static final String PO = "po";
	private static final String REDIRECT_LIST = "redirect:list";
}
