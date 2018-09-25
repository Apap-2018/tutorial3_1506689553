package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.CarModel;

@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;
	String id;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}
	
	@Override
	public void addCar(CarModel car) {
		archiveCar.add(car);
		
	}

	@Override
	public List<CarModel> getCarList() {
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		// TODO Auto-generated method stub
		for(int i=0; i< archiveCar.size(); i++) {
			CarModel car = archiveCar.get(i);
			if(car.getId().equalsIgnoreCase(id)) {
				return car;
			}
		}
		return null;
	}

	@Override
	public CarModel selectCar(String id) {
		// TODO Auto-generated method stub
		for(int i=0; i < archiveCar.size(); i++) {
			CarModel car = archiveCar.get(i);
			if(car.getId().equals(id)) {
				return car;
			}
		}
		return null;
	}

	@Override
	public boolean deleteCar(String id) {
		// TODO Auto-generated method stub
		for(int i=0; i<archiveCar.size(); i++) {
			CarModel car = archiveCar.get(i);
			if(car.getId().equals(id)) {
				archiveCar.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public CarModel updateCar(String id, Integer amount) {
		// TODO Auto-generated method stub
		for(int i=0; i < archiveCar.size(); i++) {
			CarModel car = archiveCar.get(i);
			if(car.getId().equals(id)) {
				car.setAmount(amount);
				return car;
			}
		}
		return null;
	}

	
}
