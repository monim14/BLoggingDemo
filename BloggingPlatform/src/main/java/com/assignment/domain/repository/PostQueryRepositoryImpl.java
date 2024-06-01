package com.assignment.domain.repository;

import com.assignment.domain.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.transform.Transformers;
import org.jooq.Condition;
import org.jooq.DSLContext;
import com.assignment.ui.dto.Filter;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

@Repository
@Slf4j
public class PostQueryRepositoryImpl{
    @Autowired
    private DSLContext dslContext;

    @Autowired
    private EntityManager entityManager;

    public List<Post> listAllPosts(Pageable pageable, Filter filter) {
        Condition condition = trueCondition();
        if (filter.getAuthor() != null)
        condition = condition.and(field("author", String.class).eq(filter.getAuthor()));
        if (filter.getCreationDate() != null)
            condition = condition.and(field("creation_date", LocalDate.class).eq(filter.getCreationDate()));

        org.jooq.Query jooqQuery = dslContext.select(table("posts").asterisk()).from("posts").where(condition).offset((pageable.getPageNumber()) * pageable.getPageSize())
                .limit(pageable.getPageSize()).getQuery();
        javax.persistence.Query jpaQuery = entityManager.createNativeQuery(jooqQuery.getSQL(), Post.class);
        setBindParameterValues(jpaQuery, jooqQuery);

        jpaQuery.unwrap(org.hibernate.query.NativeQuery.class).addEntity("posts", Post.class);

        List<Object[]> res = jpaQuery.getResultList();
        List<Post> result = new ArrayList<>();
        for(Object[] o: res) {
            result.add((Post) o[0]);
        }
return  result;
    }

    private void setBindParameterValues(
            javax.persistence.Query jpaQuery,
            org.jooq.Query jooqQuery
    ) {
        List<Object> values = jooqQuery.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            jpaQuery.setParameter(i + 1, values.get(i));
        }
    }
}
