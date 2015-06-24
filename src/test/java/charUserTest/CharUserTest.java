package charUserTest;

import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class CharUserTest {
	
	TimePeriod vaildFor = new TimePeriod();
	/**
	 * 设置默认值
	 */
	@Test
	public void setDefaultValue(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic();
		vaildFor.startDateTime = new Date();
		vaildFor.endDateTime = new Date();
		ProductSpecCharUse charUse = new ProductSpecCharUse(specChar,false,false,vaildFor);
		ProductSpecCharacteristicValue defaultValue = new ProductSpecCharacteristicValue();
		charUse.specifyDefaultCharacteristicValue(defaultValue);
	}
	/**
	 * 添加使用值
	 */
	@Test
	public void addValueUse(){
		vaildFor.startDateTime = new Date();
		vaildFor.endDateTime = new Date();
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic();
		ProductSpecCharUse charUse = new ProductSpecCharUse(specChar,false,false,vaildFor);
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue();
		charUse.addValue(charValue, false, vaildFor);
	}

}
