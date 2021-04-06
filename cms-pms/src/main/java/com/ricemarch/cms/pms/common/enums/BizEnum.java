package com.ricemarch.cms.pms.common.enums;

import java.io.Serializable;

/**
 * @author RiceMarch
 * @date 2021/3/7 15:55
 */
public interface BizEnum extends Serializable {

    public int getCode();

    public String getName();

    public String getDesc();

}
