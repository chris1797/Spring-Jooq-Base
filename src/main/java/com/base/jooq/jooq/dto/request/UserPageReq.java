package com.base.jooq.jooq.dto.request;

import com.base.jooq.jooq.bean.tables.pojos.Tbluser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageReq extends Tbluser {
}