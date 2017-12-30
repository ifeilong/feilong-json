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
import java.util.List;

import com.feilong.core.Validator;

/**
 * 拆单场景订单状态
 * 
 * @author hailong yu
 *
 */

public class SplitSalesOrderV5 implements Serializable{

    /**
     * 
     */
    private static final long           serialVersionUID = -1739318072484239437L;

    /**
     * 商城订单编码
     */
    private String                      bsOrderCode;

    /**
     * 商城退换货编码，商城发起退换货时非空
     */
    private String                      bsRaCode;

    /**
     * 子单号，PAC订单编码（拆单后的子订单code）
     */
    private String                      scmOrderCode;

    /**
     * 物流单号
     * 快递单号：当出库时会提供
     */
    private String                      transCode;

    /**
     * 物流商编码
     */
    private String                      logisticsProviderCode;

    /**
     * 物流商名称
     */
    private String                      logisticsProviderName;

    /**
     * 备注(相关明细信息拼接成的JSON字符串)
     * 
     */
    private String                      remark;

    /**
     * pac拆单后的订单行
     * 
     */
    private List<SplitSalesOrderLineV5> splitOrderLine;

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

    public String getScmOrderCode(){
        return scmOrderCode;
    }

    public void setScmOrderCode(String scmOrderCode){
        this.scmOrderCode = scmOrderCode;
    }

    public List<SplitSalesOrderLineV5> getSplitOrderLine(){
        return splitOrderLine;
    }

    public void setSplitOrderLine(List<SplitSalesOrderLineV5> splitOrderLine){
        this.splitOrderLine = splitOrderLine;
    }

    public String getTransCode(){
        return transCode;
    }

    public void setTransCode(String transCode){
        this.transCode = transCode;
    }

    public String getLogisticsProviderCode(){
        return logisticsProviderCode;
    }

    public void setLogisticsProviderCode(String logisticsProviderCode){
        this.logisticsProviderCode = logisticsProviderCode;
    }

    public String getLogisticsProviderName(){
        return logisticsProviderName;
    }

    public void setLogisticsProviderName(String logisticsProviderName){
        this.logisticsProviderName = logisticsProviderName;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }
}
