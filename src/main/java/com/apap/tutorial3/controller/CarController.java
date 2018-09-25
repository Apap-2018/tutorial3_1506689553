package com.apap.tutorial3.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService;
	
	@RequestMapping("/car/add")
	public String add (@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "brand", required = true) String brand,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "price", required = true) Long price,
			@RequestParam(value = "amount", required = true) Integer amount) {
		CarModel car = new CarModel(id, brand, type, price, amount);
		mobilService.addCar(car);
		return "add";
	}
	
	@RequestMapping("/car/view")
	public String view (@RequestParam("id") String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		
		model.addAttribute("car", archive);
		return "view-car";
	}
	
	@RequestMapping("/car/viewall")
	public String viewall (Model model) {
		List<CarModel> archive = mobilService.getCarList();
		
		model.addAttribute("listCar", archive);
		return "viewall-car";
	}
	
	@RequestMapping(value ="/car/view/{id}")
	public String viewPath(@PathVariable Optional <String> id, Model model) {
		CarModel car = mobilService.selectCar(id.get());
		model.addAttribute("car", car);
		if(car == null) {
			return "IdWrong";
		}else {
			return "view-car";
		}
	}
	
	@RequestMapping(value ="/car/delete/{id}")
	public String deletePath(@PathVariable Optional <String> id, Model model) {
		boolean isDelete = mobilService.deleteCar(id.get());
		if(!isDelete) {
			return "Undelete";
		}else {
			return "delete";
		}
		
	}
	
	@RequestMapping(value ="car/update/{id}/amount/{amount}")
	public String updatePath(@PathVariable Optional <String> id, @PathVariable Optional <Integer> amount, Model model) {
		CarModel car = mobilService.updateCar(id.get(), amount.get());
		
		model.addAttribute("car", car);
		if(car == null) {
			return "IdWrong";
		}else {
			return "updateCar";
		}
	}
	
}
