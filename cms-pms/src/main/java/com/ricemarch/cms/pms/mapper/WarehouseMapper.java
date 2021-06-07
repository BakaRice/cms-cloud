package com.ricemarch.cms.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricemarch.cms.pms.entity.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 仓库 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    List<Warehouse> getAll();

}
