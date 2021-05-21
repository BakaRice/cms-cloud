package com.ricemarch.cms.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricemarch.cms.pms.entity.WarehouseOutbound;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 仓库出库单 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehouseOutboundMapper extends BaseMapper<WarehouseOutbound> {

    int outByIdList(@Param("cargoCodeList") List<String> cargoCodeList, @Param("oid")Long oid, @Param("userName") String userName, @Param("uid") Long uid);
}
