package com.ricemarch.cms.pms.dto.make;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author RiceMarch
 * @date 2021/6/7 16:33
 */
/*
select make_work_book_seq.id as seqId, code as partName, name as seqCode, value,mwb.work_name as workName,mwb.work_no as workNo
from make_work_book_seq left join  make_work_book mwb on make_work_book_seq.value = mwb.work_sequence_id
where code = 'V2403活塞'
order by value
 */
@Data
@Accessors(chain = true)
public class SeqBookDto {

    private Long seqId;
    private String partName;
    private String seqCode;
    private Integer value;
    private String workName;
    private String workNo;
}
