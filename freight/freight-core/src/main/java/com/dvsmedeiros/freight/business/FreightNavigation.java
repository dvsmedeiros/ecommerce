package com.dvsmedeiros.freight.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.freight.business.impl.CalculateFreight;
import com.dvsmedeiros.freight.business.impl.FreightFindProductByIdActivity;
import com.dvsmedeiros.freight.business.impl.MinimalValuesFreightValidator;
import com.dvsmedeiros.freight.business.impl.PackingBoxDiameterValidator;
import com.dvsmedeiros.freight.business.impl.PackingBoxHeightValidator;
import com.dvsmedeiros.freight.business.impl.PackingBoxLenghtValidator;
import com.dvsmedeiros.freight.business.impl.PackingBoxWeightValidator;
import com.dvsmedeiros.freight.business.impl.PackingBoxWidthValidator;
import com.dvsmedeiros.freight.business.impl.PackingEnvelopeDiameterValidator;
import com.dvsmedeiros.freight.business.impl.PackingEnvelopeHeightValidator;
import com.dvsmedeiros.freight.business.impl.PackingEnvelopeLenghtValidator;
import com.dvsmedeiros.freight.business.impl.PackingEnvelopeWeightValidator;
import com.dvsmedeiros.freight.business.impl.PackingEnvelopeWidthValidator;
import com.dvsmedeiros.freight.business.impl.PackingRollDiameterValidator;
import com.dvsmedeiros.freight.business.impl.PackingRollHeightValidator;
import com.dvsmedeiros.freight.business.impl.PackingRollLenghtValidator;
import com.dvsmedeiros.freight.business.impl.PackingRollWeightValidator;
import com.dvsmedeiros.freight.business.impl.PackingRollWidthValidator;
import com.dvsmedeiros.freight.business.impl.PackingValidator;
import com.dvsmedeiros.freight.business.impl.SummarizeShopCart;
import com.dvsmedeiros.freight.domain.FreightFilter;
import com.dvsmedeiros.product.domain.PackingType;

@Configuration
public class FreightNavigation {

	@Autowired
	private SummarizeShopCart summarizeShopCart;
	@Autowired
	private FreightFindProductByIdActivity freightFindProductByIdActivity;
	@Autowired
	private CalculateFreight calculateFreight;
	@Autowired
	private PackingValidator packingValidator;
	@Autowired
	private MinimalValuesFreightValidator minimalValuesFreightValidator;

	@Autowired
	private PackingBoxWidthValidator packingBoxWidthValidator;
	@Autowired
	private PackingBoxHeightValidator packingBoxHeightValidator;
	@Autowired
	private PackingBoxLenghtValidator packingBoxLenghtValidator;
	@Autowired
	private PackingBoxWeightValidator packingBoxWeightValidator;
	@Autowired
	private PackingBoxDiameterValidator packingBoxDiameterValidator;

	@Autowired
	private PackingRollWidthValidator packingRollWidthValidator;
	@Autowired
	private PackingRollHeightValidator packingRollHeightValidator;
	@Autowired
	private PackingRollLenghtValidator packingRollLenghtValidator;
	@Autowired
	private PackingRollWeightValidator packingRollWeightValidator;
	@Autowired
	private PackingRollDiameterValidator packingRollDiameterValidator;

	@Autowired
	private PackingEnvelopeWidthValidator packingEnvelopeWidthValidator;
	@Autowired
	private PackingEnvelopeHeightValidator packingEnvelopeHeightValidator;
	@Autowired
	private PackingEnvelopeLenghtValidator packingEnvelopeLenghtValidator;
	@Autowired
	private PackingEnvelopeWeightValidator packingEnvelopeWeightValidator;
	@Autowired
	private PackingEnvelopeDiameterValidator packingEnvelopeDiameterValidator;

	@Bean(name = "MIN_VALUES_PACKING")
	public Map<PackingType, String> getMinValuesPacking() {

		Map<PackingType, String> minValuesPacking = new HashMap<>();

		minValuesPacking.put(PackingType.BOX, "VALIDATE_PACKING_BOX");
		minValuesPacking.put(PackingType.ENVELOPE, "VALIDATE_PACKING_ENVELOPE");
		minValuesPacking.put(PackingType.ROLL, "VALIDATE_PACKING_ROLL");

		return minValuesPacking;
	}

	@Bean(name = "CALCULATE_FREIGHT")
	public Navigation<FreightFilter> calculateFreightForProduct() {

		return new NavigationBuilder<FreightFilter>()

				.next(freightFindProductByIdActivity)
				.next(summarizeShopCart)
				.next(minimalValuesFreightValidator)
				.next(calculateFreight)
				.build();

	}

	@Bean(name = "VALIDATE_PACKING_BOX")
	public Navigation<FreightFilter> validadePackingBox() {

		return new NavigationBuilder<FreightFilter>()

				.next(packingValidator)
				.next(packingBoxWidthValidator)
				.next(packingBoxHeightValidator)
				.next(packingBoxLenghtValidator)
				.next(packingBoxWeightValidator)
				.next(packingBoxDiameterValidator)
				.build();

	}

	@Bean(name = "VALIDATE_PACKING_ROLL")
	public Navigation<FreightFilter> validadePackingRoll() {

		return new NavigationBuilder<FreightFilter>()

				.next(packingValidator)
				.next(packingRollWidthValidator)
				.next(packingRollHeightValidator)
				.next(packingRollLenghtValidator)
				.next(packingRollWeightValidator)
				.next(packingRollDiameterValidator)
				.build();

	}

	@Bean(name = "VALIDATE_PACKING_ENVELOPE")
	public Navigation<FreightFilter> validadePackingEnvelope() {

		return new NavigationBuilder<FreightFilter>()

				.next(packingValidator)
				.next(packingEnvelopeWidthValidator)
				.next(packingEnvelopeHeightValidator)
				.next(packingEnvelopeLenghtValidator)
				.next(packingEnvelopeWeightValidator)
				.next(packingEnvelopeDiameterValidator)
				.build();

	}
}
