package com.ricemarch.cms.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricemarch.cms.pms.dto.wms.PartCargoDetailDto;
import com.ricemarch.cms.pms.dto.wms.PartCargoDto;
import com.ricemarch.cms.pms.entity.WarehousePart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 仓库零件记录 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehousePartMapper extends BaseMapper<WarehousePart> {

    List<String> getTypeListBySpId(Long spId);

    List<PartCargoDto> getTypeListByOnlySpId(Long spId);

    List<PartCargoDetailDto> getCargoDetailByPartName(String partName);

    int getByCodeListAndNoOut(@Param("cargoCodeList") List<String> cargoCodeList);
}
