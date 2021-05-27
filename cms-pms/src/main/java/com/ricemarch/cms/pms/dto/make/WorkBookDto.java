package com.ricemarch.cms.pms.dto.make;

import com.ricemarch.cms.pms.entity.MakeWorkBook;
import com.ricemarch.cms.pms.entity.MakeWorkBookProcess;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/21 14:35
 */
@Data
@Accessors(chain = true)
public class WorkBookDto {
    private String workNo;

    private String workName;

    private String userProtectionTools;

    private String userTools;

    private String partName;

    private String partId;

    private String sequence; //sequence

    private Long workSequenceId;

    private String ban;

    private String other;

    private List<MakeWorkBookProcess> process;
}
