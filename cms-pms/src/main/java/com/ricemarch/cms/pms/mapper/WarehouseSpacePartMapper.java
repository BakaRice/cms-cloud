package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.entity.WarehouseSpacePart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 仓库备件记录 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehouseSpacePartMapper extends BaseMapper<WarehouseSpacePart> {

    List<String> getTypeListBySpId(@Param("spId") Long spId);

    List<WarehouseSupplier> getSupplierList();

    List<String> getAllType();

    WarehouseSpacePart getByCode(String cargoCode);

    Integer getByCodeListAndNoOut(List<String> cargoCodeList);
}
