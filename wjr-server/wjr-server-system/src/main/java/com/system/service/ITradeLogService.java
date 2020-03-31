package com.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.entity.system.TradeLog;

public interface ITradeLogService extends IService<TradeLog> {
    void orderAndPay(TradeLog tradeLog);

    void pay(TradeLog tradeLog,String transactionId);
}