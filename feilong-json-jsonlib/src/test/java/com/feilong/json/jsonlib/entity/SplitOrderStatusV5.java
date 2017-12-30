/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.json.jsonlib.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.feilong.core.Validator;

/**
 * 拆单场景订单状态
 * 
 * @author hailong yu
 *
 */
public class SplitOrderStatusV5 implements Serializable{

    /**
     * 
     */
    private static final long       serialVersionUID = -1739318072484239436L;

    /**
     * 商城订单编码
     */
    private String                  bsOrderCode;

    /**
     * 商城退换货编码，商城发起退换货时非空
     */
    private String                  bsRaCode;

    /**
     * 操作类型
     * 1.订单创建成功
     * 2.订单取消
     * 3.过单到仓库
     * 4.拆单
     * 5.销售出库
     * 7.退货已入库
     * 8.换货已入库
     * 9.换货已出库
     * 10.交易完成(订单)
     * 13:系统刷款成功
     * 6.退换申请创建成功
     * 18.退换申请创建失败(只有前端退换货反馈失败)
     * 21.退换货申请取消
     * 22:退换货申请取消成功
     * 23:退换货申请取消失败
     * 参照：OrderStatusV5Constants
     */
    private Integer                 opType;

    /**
     * 状态变更时间
     */
    private Date                    opTime;

    /**
     * 备注(相关明细信息拼接成的JSON字符串)
     * 
     */
    private String                  remark;

    /**
     * 拆单的子订单list
     * 
     */
    private List<SplitSalesOrderV5> splitOrderList;

    /**
     * <p>
     * 情景 ：一个订单 既包括 普通商品 也 包括 ESD商品 , 在商城端 只会生成一个订单,然而在后端OMS里面被拆分成了两个订单
     * </p>
     * <p>
     * 列如 混合订单 ：商城端的orderCode : 73923422023127040 ; 后端拆分orderCode : 实物 73923422023127040-SJ ; ESD 73923422023127040-XN
     * </p>
     * 
     * @return
     */
    public String getBsOrderCode(){
        if (Validator.isNotNullOrEmpty(this.bsOrderCode)){
            int index = bsOrderCode.indexOf("-");
            bsOrderCode = index == -1 ? bsOrderCode : bsOrderCode.substring(0, index);
        }
        return bsOrderCode;
    }

    public void setBsOrderCode(String bsOrderCode){
        this.bsOrderCode = bsOrderCode;
    }

    public String getBsRaCode(){
        return bsRaCode;
    }

    public void setBsRaCode(String bsRaCode){
        this.bsRaCode = bsRaCode;
    }

    public Integer getOpType(){
        return opType;
    }

    public void setOpType(Integer opType){
        this.opType = opType;
    }

    public Date getOpTime(){
        return opTime;
    }

    public void setOpTime(Date opTime){
        this.opTime = opTime;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public List<SplitSalesOrderV5> getSplitOrderList(){
        return splitOrderList;
    }

    public void setSplitOrderList(List<SplitSalesOrderV5> splitOrderList){
        this.splitOrderList = splitOrderList;
    }
}
