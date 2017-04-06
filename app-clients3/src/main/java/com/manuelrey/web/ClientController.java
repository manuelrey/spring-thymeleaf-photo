package com.manuelrey.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manuelrey.dao.ClientRepository;
import com.manuelrey.entities.Client;

@Controller
@RequestMapping(value="/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
		
	@RequestMapping(value="/index")	
	public String Index(Model model, @RequestParam(name="page", defaultValue="0")int p){
		Page<Client> pageClients=clientRepository.findAll(new PageRequest(p, 5));
		
		int pagesCount=pageClients.getTotalPages();
		int [] pages=new int [pagesCount];
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageClients", pageClients);
		model.addAttribute("pageCurrent", p);
		return "clients";
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("client", new Client());
		return "add";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@Valid Client cli, BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws Exception{
		if(bindingResult.hasErrors()){
			return "add";
		}
		if (!(file.isEmpty())){ 
			cli.setPhoto(file.getOriginalFilename());
		}
		
		clientRepository.save(cli);
		
		if (!(file.isEmpty())){
			cli.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(System.getProperty("user.home")+
					File.separator+"Documents"+File.separator+"photos"+File.separator+cli.getId()));
		}
	
		return "redirect:index";
	}
	
	@RequestMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception{
		File f= new File(System.getProperty("user.home")+
				File.separator+"Documents"+File.separator+"photos"+File.separator+id);
		
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/delete")
	public String delete(Long id){
		clientRepository.delete(id);
		return "redirect:index";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Long id, Model model){
		Client cli=clientRepository.getOne(id);
		model.addAttribute("client", cli);
		return "edit";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid Client cli, BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws Exception{
		if(bindingResult.hasErrors()){
			return "edit";
		}
		if (!(file.isEmpty())){ 
			cli.setPhoto(file.getOriginalFilename());
		}
		
		clientRepository.save(cli);
		
		if (!(file.isEmpty())){
			cli.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(System.getProperty("user.home")+
					File.separator+"Documents"+File.separator+"photos"+File.separator+cli.getId()));
		}
	
		return "redirect:index";
	}

}
