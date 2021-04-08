package com.ricemarch.cms.pms.bo.response;

import com.ricemarch.cms.pms.entity.Cells;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author RiceMarch
 * @since 2021/4/8 22:32
 */
@Data
@Slf4j
public class CellListResponse {

    private List<Cells> cellsList;
}
