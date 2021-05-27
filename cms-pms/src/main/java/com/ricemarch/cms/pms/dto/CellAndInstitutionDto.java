package com.ricemarch.cms.pms.dto;

import com.ricemarch.cms.pms.entity.Cells;
import com.ricemarch.cms.pms.entity.Institution;
import com.ricemarch.cms.pms.entity.UserRole;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/25 20:39
 */
@Data
@Accessors(chain = true)
public class CellAndInstitutionDto {
    List<Institution> institutionList;
    List<Cells> cellsList;
    List<UserRole> roleList;
}
