package by.larion.TGBot.demo.impl;

import by.larion.TGBot.demo.entity.District;

import java.util.List;

public interface DistrictButtonModeService {

    District getDistrict(long chatId);

    void setDistrict(long chatId, District district);

    List<District> findAll();
}
