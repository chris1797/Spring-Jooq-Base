package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.Tables;
import com.base.jooq.jooq.bean.tables.pojos.User;
import com.base.jooq.jooq.bean.tables.records.UserRecord;
import com.base.jooq.jooq.dto.request.user.UserPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao extends BaseDao {

    private final DSLContext query;


    private Condition isIncludes(UserPageReq req) {
        if (Objects.isNull(req.getName())) return DSL.condition(true);

        return Tables.USER.NAME.contains(req.getName())
                .and(Tables.USER.ISACTIVE.isTrue());
    }

    public Result<?> getUsers(UserPageReq req) {
        return query.select()
                .from(Tables.USER)
                .where(this.isIncludes(req))
                .and(Tables.USER.ISACTIVE.isTrue())
                .fetch();
    }

    public Optional<User> getUserByNo(Long userNo) {
        return query.select()
                .from(Tables.USER)
                .where(Tables.USER.USERNO.eq(userNo))
                .fetchOptionalInto(User.class);
    }
}
