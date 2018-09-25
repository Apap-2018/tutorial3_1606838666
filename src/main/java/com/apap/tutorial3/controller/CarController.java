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
	public String add(
				@RequestParam(value = "id", required = true) String id,
				@RequestParam(value = "brand", required = true) String brand,
				@RequestParam(value = "type", required = true) String type,
				@RequestParam(value = "price", required = true) Long price,
				@RequestParam(value = "amount", required = true) Integer amount
			) {
		CarModel car = new CarModel(id, brand, type, price, amount);
		mobilService.addCar(car);
		return "add";
	}
	
	@RequestMapping("/car/view")
	public String view(@RequestParam(value = "id", required = false, defaultValue = "false") String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		String errorMessage = "ID " + id + " tidak ditemukan";
		if (archive != null) {
			model.addAttribute("car", archive);
			return "view-car";
		}  
		if (id.equals("false")) {
			errorMessage = "id tidak boleh kosong";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "error-page";
	}
	
	@RequestMapping("/car/view/{id}")
	public String viewPath(@PathVariable String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		String errorMessage = "ID " + id + " tidak ditemukan";
		if (archive != null) {
			model.addAttribute("car", archive);
			return "view-car";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "error-page";
	}
	
	@RequestMapping(value = {
			"/car/update/{id}/amount/{newAmount}",
			"/car/update/amount",
			"/car/update/{id}/amount", 
			"/car/update/amount/{newAmount}"
			}
	)
	public String updateAmount(
				@PathVariable Optional<String> id,
				@PathVariable Optional<String> newAmount,
				Model model
			) {
		String errorMessage;
		if (!id.isPresent() && !newAmount.isPresent()) {
			errorMessage = "Amount dan Id tidak boleh kosong";
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
		if (!id.isPresent()) {
			errorMessage = "Id tidak boleh kosong";
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
		if (!newAmount.isPresent()) {
			errorMessage = "Amount tidak boleh kosong";
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
		CarModel archive = mobilService.getCarDetail(id.get());
		if (archive != null) {
			archive.setAmount(Integer.parseInt(newAmount.get()));
			return "update";
		}
		errorMessage = "ID " + id.get() + " tidak ditemukan";
		model.addAttribute("errorMessage", errorMessage);
		return "error-page";
	}
	@RequestMapping(value = {
			"/car/delete/{id}",
			"/car/delete"
			}
	)
	public String delete(
				@PathVariable Optional<String> id,
				Model model
			) {
		String errorMessage;
		if (!id.isPresent()) {
			errorMessage = "Id tidak boleh kosong";
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
		CarModel archive = mobilService.getCarDetail(id.get());
		if (archive != null) {
			boolean deleted = mobilService.deleteCar(id.get());
			if (deleted) {
				return "delete-car";	
			}
		}
		errorMessage = "ID " + id.get() + " tidak ditemukan";
		model.addAttribute("errorMessage", errorMessage);
		return "error-page";
	}
	
	
	@RequestMapping("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archieve = mobilService.getCarList();
		
		model.addAttribute("listCar", archieve);
		return "viewall-car";
	}
}
