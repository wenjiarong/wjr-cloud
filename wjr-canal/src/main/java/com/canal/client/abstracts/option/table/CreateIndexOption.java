package com.canal.client.abstracts.option.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.canal.client.abstracts.option.AbstractDBOption;

/**
 * 創建索引操作
 *
 */
public abstract class CreateIndexOption extends AbstractDBOption {
	/**
	 * 創建索引操作
	 *
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.CINDEX;
	}
}
