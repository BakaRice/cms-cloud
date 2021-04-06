package com.ricemarch.cms.pms.bo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RiceMarch
 * @since 2021/3/26 23:30
 */
@Data
@Slf4j
@AllArgsConstructor
public class UserLoginResponse {

    String role;
}
