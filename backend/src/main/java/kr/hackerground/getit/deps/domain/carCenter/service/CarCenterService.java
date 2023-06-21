package kr.hackerground.getit.deps.domain.carCenter.service;

import kr.hackerground.getit.deps.domain.carCenter.dto.CarCenterDto;
import kr.hackerground.getit.deps.domain.carCenter.entity.Address;
import kr.hackerground.getit.deps.domain.carCenter.entity.CarCenter;
import kr.hackerground.getit.deps.domain.carCenter.repository.CarCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service @RequiredArgsConstructor
public class CarCenterService {
    private final CarCenterRepository carCenterRepository;
    public void create(CarCenterDto.Request carCenterDto){
        Address address = new Address(carCenterDto.getLatitude(), carCenterDto.getLongitude());
        CarCenter carCenter = carCenterDto.toEntity(address);
        carCenterRepository.save(carCenter);
    }
    //readOne
    public CarCenterDto.Response read(Long carCenterId){
        CarCenter carCenter = carCenterRepository.findById(carCenterId).orElseThrow();
        return new CarCenterDto.Response(carCenter);
    }
    //readAll
    public List<CarCenterDto.Response> readAll(){
        return carCenterRepository.findAll().stream()
                .map(CarCenterDto.Response::new)
                .toList();
    }
    //update
    public void update(Long carCenterId, CarCenterDto.Request carCenterDto){
        CarCenter carCenter = carCenterRepository.findById(carCenterId).orElseThrow();
        carCenter.update(carCenterDto);
        carCenterRepository.save(carCenter);
    }
    //delete
    public void delete(Long placeId){
        carCenterRepository.deleteById(placeId);
    }

}
