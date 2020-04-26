package com.canal.test;

import com.canal.client.abstracts.option.content.DeleteOption;
import com.canal.client.abstracts.option.content.InsertOption;
import com.canal.client.abstracts.option.content.UpdateOption;
import com.canal.client.core.DealCanalEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 实现接口方式测试
 *
 */
@Component
public class MyEventListenerimpl extends DealCanalEventListener {
	
	@Autowired
	public MyEventListenerimpl(@Qualifier("realInsertOptoin") InsertOption insertOption, @Qualifier("realDeleteOption") DeleteOption deleteOption, @Qualifier("realUpdateOption") UpdateOption updateOption) {
		super(insertOption, deleteOption, updateOption);
	}
	
}
