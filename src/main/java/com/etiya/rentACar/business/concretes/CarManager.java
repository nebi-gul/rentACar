package com.etiya.rentACar.business.concretes;


import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStateRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.Car;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {
    private CarDao carDao;//veritabanındaki işleri yaptırdığımız için ce dao bizim db imiz olduğundan dao yu çağırıyoruz
    private ModelMapperService modelMapperService;//mapleme yapmak için mapper service i çağırıyoruz

    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {//constructorlarını oluşturduk
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    //ekleme operasyonu
    public Result add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);//saveliyor
        return new SuccessResult(BusinessMessages.Car.CAR_ADD);//araç eklendi diye business mesaj fırlatıyor.

    }

    @Override
    //güncelleme
    public Result update(UpdateCarRequest updateCarRequest) {

        Car result = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
        this.carDao.save(result);
        return new SuccessResult(BusinessMessages.Car.CAR_UPDATE);
    }

//silme operasyonu
    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        int carId = deleteCarRequest.getId();
        this.carDao.deleteById(carId);
        return new SuccessResult(BusinessMessages.Car.CAR_REMOVE);
    }

    @Override
    //aracın durumunu güncelliyor.
    public Result updateCarState(UpdateCarStateRequest updateCarStateRequest) {
        Car result = this.carDao.getById(updateCarStateRequest.getCarId());
        result.setCarState(updateCarStateRequest.getCarStateName());
        this.carDao.save(result);
        return new SuccessResult(BusinessMessages.Car.CAR_UPDATE);
    }


    @Override
    public CarDto getById(int carId) {
        Car result = this.carDao.getById(carId);

        CarDto response = this.modelMapperService.forDto().map(result, CarDto.class);

        return response;
    }

    @Override
    public DataResult<List<ListCarDto>> getAll() {
        List<Car> cars = this.carDao.findAll();
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto()
                        .map(car, ListCarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDto>>(response);
    }

    @Override
    //mapleme işlemi
    public DataResult<List<ListCarDto>> getAllByModelYear(double modelYear) {
        List<Car> cars = this.carDao.getByModelYear(modelYear);
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto()
                        .map(car, ListCarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDto>>(response);

    }

    @Override
    public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Car> cars = this.carDao.findAll(pageable).getContent();//content datayı anlatır burada sayfa ile biligilerde olduğu için bunu kullanıyoruz.
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto()
                        .map(car, ListCarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDto>>(response);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
        List<Car> cars = this.carDao.findAll(sort);
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService.forDto()
                        .map(car, ListCarDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDto>>(response);
    }
}
