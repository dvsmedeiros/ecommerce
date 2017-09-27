package com.dvsmedeiros.stock.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.stock.domain.RecordType;
import com.dvsmedeiros.stock.domain.StockRecord;

@Component
public class StockRecordFindRecordTypeByCode extends ApplicationEntity implements IStrategy<StockRecord> {

	@Autowired
	@Qualifier("genericDAO")
	private IDAO<RecordType> dao;

	@Override
	public void process(StockRecord aEntity, INavigationCase<StockRecord> aCase) {

		RecordType recordType;
			
		if (aEntity.getRecordType() == null) {
			recordType = (RecordType) dao.find(RecordType.class, RecordType.IN);
		} else {
			recordType = (RecordType) dao.find(aEntity.getRecordType().getId(), RecordType.class);
		}

		if (recordType != null) {
			aEntity.setRecordType(recordType);
			return;
		}

		getLogger(this.getClass()).info("Code: " + RecordType.IN + " não foi encontrado!");

	}

}
