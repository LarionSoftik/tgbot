package by.larion.TGBot.demo.impl;

import by.larion.TGBot.demo.entity.District;
import org.jvnet.hk2.annotations.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HashMapDistrictButtonModeService implements DistrictButtonModeService {
    @Override
    public District getDistrict(long chatId) {
        return null;
    }

    @Override
    public void setDistrict(long chatId, District district) {

    }

    @Override
    public List<District> findAll() {
        List<District> districts = new ArrayList<>();
        districts.add(District.Ленинский);
        districts.add(District.Гродненский);
        districts.add(District.Октябрьский);
        return districts;
    }
}
