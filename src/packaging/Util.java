package packaging;

import models.CarPart;
import models.Package;
import shared.CarPartDTO;
import shared.PackageDTO;

public class Util {
    public static Package convertToModel(PackageDTO packageDTO){
        if(packageDTO == null)
            return null;
        return new Package(packageDTO.getPackageNo(), packageDTO.getCarModel(), packageDTO.getPartType());
    }

    public static CarPart convertToModel(CarPartDTO carPartDTO){
        if(carPartDTO == null)
            return null;
        return new CarPart(carPartDTO.getWeight(), carPartDTO.getChassisNo(),carPartDTO.getModel(), carPartDTO.getType());
    }
}
