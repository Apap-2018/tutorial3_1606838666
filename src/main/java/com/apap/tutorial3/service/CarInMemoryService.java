package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.CarModel;

@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		// TODO Auto-generated constructor stub
		archiveCar = new ArrayList<>();
	}
	
	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		archiveCar.add(car);
	}

	@Override
	public List<CarModel> getCarList() {
		// TODO Auto-generated method stub
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		// TODO Auto-generated method stub
		for (CarModel car : archiveCar) {
			if (car.getId().equals(id)) {
				return car;
			}
		}
		return null;
	}

	@Override
	public boolean deleteCar(String id) {
		// TODO Auto-generated method stub
		for (CarModel car : archiveCar) {
			if (car.getId().equals(id)) {
				archiveCar.remove(car);
				return true;
			}
		}
		return false;
	}

}
