package com.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.entity.system.TradeLog;

public interface ITradeLogService extends IService<TradeLog> {
    void packageAndSend(TradeLog tradeLog);
}