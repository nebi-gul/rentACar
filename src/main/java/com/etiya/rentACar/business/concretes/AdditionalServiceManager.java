package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.business.responses.additionalServiceResponses.AdditionalServiceDto;
import com.etiya.rentACar.business.responses.additionalServiceResponses.ListAdditionalServiceDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service// Servis Injection için gereklidir.
public class AdditionalServiceManager implements AdditionalServiceService {

    private AdditionalServiceDao additionalServiceDao;
    private ModelMapperService modelMapperService;

    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        AdditionalService result = this.modelMapperService.forRequest().map(createAdditionalServiceRequest,AdditionalService.class);
        this.additionalServiceDao.save(result);

        return new SuccessResult(BusinessMessages.AdditionalServiceMessage.ADDITIONAL_SERVICES_ADD);
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        int additionalServiceRequestId = deleteAdditionalServiceRequest.getId();
        this.additionalServiceDao.deleteById(additionalServiceRequestId);
        return new SuccessResult(BusinessMessages.AdditionalServiceMessage.ADDITIONAL_SERVICES_DELETED);
    }


    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        AdditionalService result = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(result);
        return new SuccessResult(BusinessMessages.AdditionalServiceMessage.ADDITIONAL_SERVICES_UPDATED);
    }
    @Override
    public DataResult<List<ListAdditionalServiceDto>> getAll() {
        List<AdditionalService> result=this.additionalServiceDao.findAll();
        List<ListAdditionalServiceDto> response=result.stream().map(additionalService -> modelMapperService.forDto()
                .map(additionalService,ListAdditionalServiceDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
    }

    @Override
    public DataResult<AdditionalServiceDto> getById(int id) {
       AdditionalService result =this.additionalServiceDao.getById(id);
       AdditionalServiceDto additionalServiceDto =this.modelMapperService.forDto().map(result,AdditionalServiceDto.class);
       return new SuccessDataResult<AdditionalServiceDto>(additionalServiceDto);
    }


}
