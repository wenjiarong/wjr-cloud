package com.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *  事务日志类
 */
@Data
@TableName("t_transaction_log")
public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 1268216478456291093L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @TableField("TRANSACTION_ID")
    private String transactionId;
    @TableField("REMARK")
    private String remark;
}